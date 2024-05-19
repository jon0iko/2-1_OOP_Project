package org.example.salon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.salon.Database.DatabaseConnector;
import org.example.salon.Database.Model.User;

import java.io.IOException;

public class Auth {
    private static User user;

    public static void setUser(String username) {
        user = DatabaseConnector.getUserObjectbyUsername(username);
    }

    public static User getUser() {
        if (user==null) {
            System.out.println("User not logged in.");
        }
        return user;
    }

    public static void logOutUser() {
        user = null;
    }

    public static void openPage(String name, ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource(name+".fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException err) {
            err.printStackTrace();
        }

    }
}
