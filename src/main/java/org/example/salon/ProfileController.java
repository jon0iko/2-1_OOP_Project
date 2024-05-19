package org.example.salon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.example.salon.Database.DatabaseConnector;
import org.example.salon.Database.Model.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    Label usernameLabel, nameLabel, numberLabel, emailLabel, pointLabel;

    @FXML
    PasswordField currentPass,newPass, confirmPass;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        User u = Auth.getUser();
        usernameLabel.setText(u.getUserName());
        nameLabel.setText(u.getFullName());
        numberLabel.setText(u.getPhone());
        emailLabel.setText(u.getEmail());
        pointLabel.setText(Integer.toString(u.getPoints()));
    }


    public void servicesButtonfunction(ActionEvent e) {
        Auth.openPage("services-view", e);
    }

    public void appointmentbuttonfunction(ActionEvent e) {
        Auth.openPage("appointment-view", e);
    }

    public void logoutButtonfunction(ActionEvent e) {
        Auth.logOutUser();
        Auth.openPage("login-view",e);
    }

    public void submitButtonfunction() {
        String n = newPass.getText();
        String confirm = confirmPass.getText();
        if(!n.equals(confirm)) {
            showAlert("Newpass and Confirmpass don't match");
            return;
        }

        try {
            System.out.println(Auth.getUser().getUserName());

            if(DatabaseConnector.changePassword(Auth.getUser().getUserName(), currentPass.getText(), newPass.getText())) {
                showDialog("Password Updated");
            }
            else {
                showAlert("Wrong current password");
            }
        }
        catch (SQLException e) {
            showAlert(e.getMessage());
        }
    }

    public void showAlert(String m) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("OOPS!");
        alert.setContentText(m);
        alert.showAndWait();
    }

    public void showDialog(String m) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(m);
        alert.showAndWait();
    }
}
