package org.example.salon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.example.salon.Database.DatabaseConnector;
import org.example.salon.Database.Model.*;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    private ArrayList<User> stylists;
    private ArrayList<Service> a;

    @FXML
    ComboBox<String> CategoryBox, ServiceBox, stylistBox;
    @FXML
    DatePicker datePicker;
    @FXML
    private ComboBox<String> timeComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryBox.setItems(FXCollections.observableArrayList("Hair", "Spa", "Beauty"));
        timeComboBox.setItems(FXCollections.observableArrayList(
                "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
                "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"
        ));
        stylists = new ArrayList<>();
        stylists = DatabaseConnector.getEmployees();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (User stylist : stylists) {
            list.add(stylist.getFullName());
        }
        stylistBox.setItems(list);
    }

    public void CategoryBoxfunction() {
        setServiceBox(CategoryBox.getValue());
    }

    public void setServiceBox(String Category) {
        a = new ArrayList<>();
        a = DatabaseConnector.getServicesByCategory(Category);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Service service : a) {
            String s = service.getName() +" (" + Double.toString(service.getPrice())+ ")";
            observableList.add(s);
        }
        ServiceBox.setItems(observableList);
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        long epochMilli = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return new Date(epochMilli);
    }

    public static String extractServiceName(String s) {
        int index = s.indexOf(" (");
        if (index != -1) {
            return s.substring(0, index);
        }
        return s;
    }

    public static Time setTime(String timeString) {
        if (timeString == null) {
            showDialog("You must select a suitable time");
        }
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        Time sqlTime = Time.valueOf(time);

        return sqlTime;
    }

    public void confirmButtonfunction(ActionEvent event) {

        User stylist = null;
        Service s = null;
        String serviceName = extractServiceName(ServiceBox.getValue());


        for (User user : stylists) {
            if (user.getFullName().equals(stylistBox.getValue())) {
                stylist = user;
                break;
            }
        }

        for (Service service : a) {
            if (service.getName().equals(serviceName)) {
                s = service;
                break;
            }
        }

        Time t = setTime(timeComboBox.getValue());
        double price = s.getPrice();
        Date d = convertLocalDateToDate(datePicker.getValue());
        Appointment apnmnt = new Appointment(Auth.getUser(), stylist, s, d, t);
        DatabaseConnector.addAppointment(apnmnt);
        int points = loyaltyPoints(price);
        showDialog("Appointment Added! You earned "+Integer.toString(points)+" points.");
        Auth.openPage("appointment-view", event);

    }

    public int loyaltyPoints(double price) {
        int points = LoyaltyProgram.calculatePoints(price);
        DatabaseConnector.updateUserPoints(Auth.getUser().getUserName(), points);
        Auth.setUser(Auth.getUser().getUserName());
        return points;
    }

    public void servicesButtonfunction(ActionEvent event) {
        Auth.openPage("services-view", event);
    }

    public void profileButtonfunction(ActionEvent event) {
        Auth.openPage("profile-view", event);
    }

    public static void showDialog(String m) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(m);
        alert.showAndWait();
    }

}
