package org.example.salon;

import javafx.event.ActionEvent;


public class ServicesController {

    public void appointmentButtonfunction(ActionEvent event) {
        Auth.openPage("appointment-view", event);
    }

    public void profileButtonfunction(ActionEvent event) {
        Auth.openPage("profile-view", event);
    }
}
