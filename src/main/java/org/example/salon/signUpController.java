package org.example.salon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.salon.Database.DatabaseConnector;
import org.example.salon.Database.Model.Customer;
import static org.example.salon.FieldValidator.validateSignUpInfo;

public class signUpController {
    @FXML
    private TextField usernameField, nameField, numberField, emailField;
    @FXML
    private PasswordField passField, passCField;

    public void RegiserButtonfunction(ActionEvent event) {
        String username = usernameField.getText();
        String fullName = nameField.getText();
        String number = numberField.getText();
        String email = emailField.getText();
        String pass = passField.getText();
        String passC = passCField.getText();

        try {
            validateSignUpInfo(username,fullName,number, email, pass, passC);
        }
        catch (Exception e) {
            showAlert(e.getMessage());
            return;
        }

        Customer c = new Customer(username, fullName, email, pass, number);
        DatabaseConnector.insertUser(c);
        showDialog("Customer Added");
        Auth.openPage("login-view", event);
    }

    public void loginButton(ActionEvent event) {
        Auth.openPage("login-view", event);
    }


    public void showAlert(String m) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("OOPS!");
        alert.setContentText(m);
        alert.showAndWait();
    }

    public void showDialog(String m) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(m);
        alert.showAndWait();
    }

}
