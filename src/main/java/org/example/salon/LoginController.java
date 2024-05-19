package org.example.salon;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.salon.Database.DatabaseConnector;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(FXCollections.observableArrayList("Customer","Employee"));
    }


    public void loginButtonfunction(ActionEvent e) {
        String username = nameField.getText();
        String password =  passwordField.getText();

        if (comboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOPS!");
            alert.setContentText("Select a Role!");
            alert.showAndWait();
        }
        else {
            if(DatabaseConnector.validateUser(username, password)) {
                Auth.setUser(nameField.getText());
                if (comboBox.getValue() == "Customer") {
                    Auth.openPage("appointment-view", e);
                }
                else {
                    Auth.openPage("schedule-view", e);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("OOPS!");
                alert.setContentText("Wrong Credentials");
                alert.showAndWait();
            }
        }
    }


    public void signUpTextfunction(ActionEvent e)  {
        Auth.openPage("signup-view", e);
    }

}
