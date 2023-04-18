package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBAppointments;
import com.example.c195tasklangridge.model.Appointments;
import com.example.c195tasklangridge.timeZone.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines AppointmentsForm class
 */
public class AppointmentsForm implements Initializable {
    public AnchorPane appointmentsPane;
    public TableColumn<Appointments, Integer> appointmentIDCol;
    public TableColumn<Appointments, String> titleCol;
    public TableColumn<Appointments, String> descriptionCol;
    public TableColumn<Appointments, String> contactCol;
    public TableColumn<Appointments, String> locationCol;
    public TableColumn<Appointments, String> typeCol;
    public TableColumn<Appointments, String> startTimeCol;
    public TableColumn<Appointments, String> endTimeCol;
    public TableColumn<Appointments, String> customerIDCol;
    public TableColumn<Appointments, Integer> userIDCol;
    public Label appointmentsLabel;
    public Button addAppointmentButton;
    public Button deleteAppointmentButton;
    public Button updateAppointmentButton;
    public Button customersButtonA;
    public Button reportsButtonA;
    public RadioButton monthRadioA;
    public Label sortByLabel;
    public RadioButton weekRadioA;
    public ToggleGroup toggleAppointments;
    public TableView<Appointments> appointmentsTable;
    public RadioButton allRadio;

    Stage stage;
    Parent scene;

    /**
     * adds appointments
     */
    public void onAddAppointmentButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AddAppointmentForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * updates appointments
     */
    public void onUpdateAppointmentButton(ActionEvent actionEvent) throws IOException, SQLException {

        Appointments selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Appointment must be selected");
            error.show();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/c195tasklangridge/UpdateAppointmentForm.fxml"));
            loader.load();

            UpdateAppointmentForm UAFController = loader.getController();
            UAFController.sendAppointment(appointmentsTable.getSelectionModel().getSelectedItem());
            UAFController.updateAppointmentIDText.setText(String.valueOf(selectedAppointment.getAppointmentID()));
            UAFController.updateTitleText.setText(selectedAppointment.getTitle());
            UAFController.updateDescriptionText.setText((selectedAppointment.getDescription()));
            UAFController.updateLocationText.setText((selectedAppointment.getLocation()));
            UAFController.updateTypeText.setText((selectedAppointment.getType()));
            UAFController.updateCustomerIDTextA.setText(String.valueOf(selectedAppointment.getCustomerID()));
            UAFController.updateUserIDText.setText(String.valueOf(selectedAppointment.getUserID()));

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * deletes appointments
     */
    public void onDeleteAppointmentButton(ActionEvent actionEvent) throws SQLException, IOException {
        Appointments selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Appointment must be selected");
            error.show();
        } else {
            int selectedID = selectedAppointment.getAppointmentID();
            String selectedType = selectedAppointment.getType();
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            warning.setTitle("Caution");
            warning.setContentText("Are you sure you want to delete this appointment?");
            warning.showAndWait();
            if (warning.getResult() == ButtonType.OK) {
                DBAppointments.delete(selectedID);
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setTitle("Success");
                error.setContentText("Appointment " + selectedID + " for " + selectedType + " has been canceled.");
                error.show();

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AppointmentsForm.fxml")));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }

    /**
     * opens customers form
     */
    public void onCustomersButtonC(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/CustomersForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * opens reports forms
     */
    public void onReportsButtonC(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/ReportsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * shows appointments by month
     */
    public void onMonthRadio(ActionEvent actionEvent) throws SQLException {
        appointmentsTable.setItems(DBAppointments.getAppointmentsMonth());
    }

    /**
     * shows appointments by week
     */
    public void onWeekRadio(ActionEvent actionEvent) throws SQLException {
        appointmentsTable.setItems(DBAppointments.getAppointmentsWeek());
    }

    /**
     * initializes AppointmentsForm
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("UserID"));

        try {
            ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
            appointmentsTable.setItems(allAppointments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

    /**
     * sets table with all appointments
     */
    public void onAllRadio(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
        appointmentsTable.setItems(allAppointments);
    }
}
