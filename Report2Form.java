package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBAppointments;
import com.example.c195tasklangridge.model.Appointments;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * defines Report2Form class
 */
public class Report2Form implements Initializable {
    public AnchorPane report2Pane;
    public Label report2Label;
    public TableView <Appointments> report2Table;
    public Button reportsReturnButton2;
    public TableColumn <Appointments, Integer> appointmentIDCol;
    public TableColumn <Appointments, String> titleCol;
    public TableColumn <Appointments, String> descriptionCol;
    public TableColumn <Appointments, String> typeCol;
    public TableColumn <Appointments, String> startTimeCol;
    public TableColumn <Appointments, String> endTimeCol;
    public TableColumn <Appointments, String> customerIDCol;
    public RadioButton contact1radio;
    public RadioButton contact2radio;
    public RadioButton contact3radio;
    public ToggleGroup contactRadio;

    Stage stage;
    Parent scene;

    /**
     * opens reports form
     */
    public void onReportsReturnButton2(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/ReportsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * initializes Report2Form values
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        try {
            ObservableList<Appointments> allAppointments1 = DBAppointments.getAppointments1();
            report2Table.setItems(allAppointments1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * displays all appointments for contact 1
     */
    public void onRadio1(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> allAppointments1 = DBAppointments.getAppointments1();
        report2Table.setItems(allAppointments1);
    }

    /**
     * displays all appointments for contact 2
     */
    public void onRadio2(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> allAppointments2 = DBAppointments.getAppointments2();
        report2Table.setItems(allAppointments2);
    }

    /**
     * displays all appointments for contact 3
     */
    public void onRadio3(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> allAppointments3 = DBAppointments.getAppointments3();
        report2Table.setItems(allAppointments3);
    }
}
