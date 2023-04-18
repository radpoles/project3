package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBAppointments;
import com.example.c195tasklangridge.DAO.DBContacts;
import com.example.c195tasklangridge.DAO.DBCustomers;
import com.example.c195tasklangridge.DAO.DBUsers;
import com.example.c195tasklangridge.model.Contacts;
import com.example.c195tasklangridge.timeZone.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines AddAppointmentForm class
 */
public class AddAppointmentForm implements Initializable {
    public TextField addAppointmentIDText;
    public Label addAppointmentLabel;
    public TextField addTitleText;
    public TextField addDescriptionText;
    public TextField addLocationText;
    public Label addAppointmentIDLabel;
    public Label addTitleLabel;
    public Label addDescriptionLabel;
    public Label addLocationLabel;
    public Label addContactLabel;
    public Button addSaveButtonA;
    public Button addCancelButtonA;
    public Label addSDATLabel;
    public Label addTypeLabel;
    public Label addEDATLabel;
    public Label addCustomerIDLabelA;
    public Label addUserIDLabel;
    public TextField addTypeText;
    public TextField addCustomerIDTextA;
    public TextField addUserIDText;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public AnchorPane addAppointmentPane;
    public ComboBox contactComboBox;
    public ComboBox startComboBox;
    public ComboBox endComboBox;

    Stage stage;
    Parent scene;

    private static int appointmentID = 2;

    /**
     * returns the part ID
     * @return partID returns the part ID
     */
    public static int getAppointmentID() {
        return ++appointmentID;
    }

    /**
     * saves new appointment info
     */
    public void onAddSaveButtonA(ActionEvent actionEvent) throws IOException, SQLException {
        int appointmentID = AddAppointmentForm.getAppointmentID();
        String title = addTitleText.getText();
        String description = addDescriptionText.getText();
        String location = addLocationText.getText();
        String type = addTypeText.getText();
        int customerID = Integer.parseInt(addCustomerIDTextA.getText());
        int userID = Integer.parseInt(addUserIDText.getText());
        int contactID = DBContacts.selectID(contactComboBox.getValue().toString());

        String startDate = startDatePicker.getValue().toString();
        String startTime = startComboBox.getValue().toString();
        String endDate = endDatePicker.getValue().toString();
        String endTime = endComboBox.getValue().toString();
        String startDT = startDate + " " + startTime;
        DateTimeFormatter formatterS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDT, formatterS);
        Timestamp start = Timestamp.valueOf(dateTimeStart);
        String endDT = endDate + " " + endTime;
        DateTimeFormatter formatterE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDT, formatterE);
        Timestamp end = Timestamp.valueOf(dateTimeEnd);

//        System.out.println(start);
//        System.out.println(TimeZone.convertLocalToUTC(start));
//        System.out.println(TimeZone.convertUTCToLocal(start));

        if (dateTimeStart.isBefore(LocalDateTime.now())) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Start time cannot be in the past");
            error.show();
            return;
        }
        if (dateTimeEnd.isBefore(LocalDateTime.now())) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("End time cannot be in the past");
            error.show();
            return;
        }
        if (DBAppointments.checkForOverlap(dateTimeStart, dateTimeEnd, appointmentID)) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Appointment conflict");
            error.show();
            return;
        }
        if (dateTimeStart.getDayOfWeek().getValue() != dateTimeEnd.getDayOfWeek().getValue()) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Appointment cannot span multiple days");
            error.show();
            return;
        }
        if (dateTimeStart.isAfter(dateTimeEnd)) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Start time cannot be after end time");
            error.show();
            return;
        } else if (dateTimeStart.isEqual(dateTimeEnd)) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Start time cannot be the same as end time");
            error.show();
            return;
        }
        if (DBUsers.verifyUserID(userID) == 0) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("User does not exist");
            error.show();
            return;
        }
        if (DBCustomers.verifyCustomerID(customerID) == 0) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Customer does not exist");
            error.show();
            return;
        }

        DBAppointments.insert(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AppointmentsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * cancels new appointment creation
     */
    public void onAddCancelButtonA(ActionEvent actionEvent) throws IOException {
        Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
        warning.setTitle("Cancel");
        warning.setContentText("Are you sure you want to leave without saving?");
        warning.showAndWait();
        if (warning.getResult() == ButtonType.OK) {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AppointmentsForm.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void onContactComboBox(ActionEvent actionEvent) {
    }


    /**
     * initializes AddAppointmentForm values
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        try {
            for (Contacts C : DBContacts.getAllContacts()) {
                contactNames.add(C.getContactName());
            }
            contactComboBox.setItems(contactNames);
            startComboBox.setItems(TimeZone.getAppointmentStartTimes());
            endComboBox.setItems(TimeZone.getAppointmentEndTimes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onStartDatePicker(ActionEvent actionEvent) {
    }

    public void onEndDatePicker(ActionEvent actionEvent) {
    }

    public void onStartComboBox(ActionEvent actionEvent) {
    }

    public void onEndCombo(ActionEvent actionEvent) {
    }
}
