package org.example.salon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.salon.Database.DatabaseConnector;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    @FXML
    TableView<Tableview> table;

    @FXML
    private TableColumn<Tableview, Integer> id;

    @FXML
    private TableColumn<Tableview, String> name;

    @FXML
    private TableColumn<Tableview, String> phone;

    @FXML
    private TableColumn<Tableview, String> service;

    @FXML
    private TableColumn<Tableview, Date> date;

    @FXML
    private Label helloLabel;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloLabel.setText("Hello, "+ Auth.getUser().getFullName());
        id.setCellValueFactory(new PropertyValueFactory<Tableview, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Tableview, String>("customer_name"));
        phone.setCellValueFactory(new PropertyValueFactory<Tableview, String>("customer_phone"));
        service.setCellValueFactory(new PropertyValueFactory<Tableview, String>("service_name"));
        date.setCellValueFactory(new PropertyValueFactory<Tableview, Date>("date"));

        loadAppointments();

    }


    private void loadAppointments() {
        int stylistId = Auth.getUser().getUserID();
        List<Tableview> appointments = DatabaseConnector.getAppointmentsByStylistId(stylistId);
        ObservableList<Tableview> observableList = FXCollections.observableArrayList(appointments);
        table.setItems(observableList);
    }


    public void profileButtonfunction(ActionEvent e) {
        Auth.openPage("profile-view2",e);
    }
}
