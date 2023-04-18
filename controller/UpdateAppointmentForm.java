package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.*;
import com.example.c195tasklangridge.model.Appointments;
import com.example.c195tasklangridge.model.Contacts;
import com.example.c195tasklangridge.model.Customers;
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
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines UpdateAppointmentForm class
 */
public class UpdateAppointmentForm implements Initializable {
    public TextField updateAppointmentIDText;
    public Label updateAppointmentLabel;
    public Label updateAppointmentIDLabel;
    public Label updateTitleLabel;
    public Label updateDescriptionLabel;
    public Label updateLocationLabel;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox <String> contactComboBox;
    public ComboBox startComboBox;
    public ComboBox endComboBox;
    public AnchorPane updateAppointmentPane;
    public TextField updateTitleText;
    public TextField updateDescriptionText;
    public TextField updateLocationText;
    public Label updateContactLabel;
    public Button updateSaveButtonA;
    public Button updateCancelButtonA;
    public Label updateTypeLabel;
    public Label updateSDATLabel;
    public Label updateEDATLabel;
    public Label updateCustomerIDLabelA;
    public Label updateUserIDLabel;
    public TextField updateTypeText;
    public TextField updateCustomerIDTextA;
    public TextField updateUserIDText;

    Stage stage;
    Parent scene;

    private static int appointmentID = 0;

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
    public void onUpdateSaveButtonA(ActionEvent actionEvent) throws IOException, SQLException {
        int appointmentID = appointmentData.getAppointmentID();
        String title = updateTitleText.getText();
        String description = updateDescriptionText.getText();
        String location = updateLocationText.getText();
        String type = updateTypeText.getText();
        int customerID = Integer.parseInt(updateCustomerIDTextA.getText());
        int userID = Integer.parseInt(updateUserIDText.getText());
        int contactID = DBContacts.selectID(contactComboBox.getValue());

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
        if (dateTimeStart.getDayOfWeek().getValue() != dateTimeEnd.getDayOfWeek().getValue()) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Appointment cannot span multiple days");
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

        DBAppointments.update(title, description, location, contactID, type, start, end, customerID, userID, appointmentID);

        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle("Success");
        error.setContentText("Appointment " + appointmentID + " " + title + " has been updated.");
        error.show();

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AppointmentsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * cancels new appointment creation
     */
    public void onUpdateCancelButtonA(ActionEvent actionEvent) throws IOException {
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

    Appointments appointmentData = new Appointments(0, "Meeting", "Safety Meeting", "Home", "Boring",Timestamp.valueOf("2023-03-02 12:00:00"),Timestamp.valueOf("2023-03-02 12:00:00"),0, 0,0);

    /**
     * captures appointment data to be passed to another screen
     */
    public void sendAppointment(Appointments appointments) throws SQLException {
        appointmentData = appointments;
        updateAppointmentIDText.setText(String.valueOf(appointments.getAppointmentID()));
        updateTitleText.setText(appointments.getTitle());
        updateDescriptionText.setText((appointments.getDescription()));
        updateLocationText.setText((appointments.getLocation()));
        updateTypeText.setText((appointments.getType()));
        updateCustomerIDTextA.setText(String.valueOf(appointments.getCustomerID()));
        updateUserIDText.setText(String.valueOf(appointments.getUserID()));

        contactComboBox.setValue(DBContacts.selectName(appointments.getContactID()));

        startDatePicker.setValue(appointments.getStart().toLocalDateTime().toLocalDate());
        startComboBox.setValue(appointments.getStart().toLocalDateTime().toLocalTime());

        endDatePicker.setValue(appointments.getEnd().toLocalDateTime().toLocalDate());
        endComboBox.setValue(appointments.getEnd().toLocalDateTime().toLocalTime());
    }

    /**
     * initializes UpdateAppointmentForm values
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
