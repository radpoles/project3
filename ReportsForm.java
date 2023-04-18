package com.example.c195tasklangridge.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

/**
 * defines ReportsForm class
 */
public class ReportsForm {
    public AnchorPane customersPane;
    public Label reportsLabel;
    public Button appointmentsButtonR;
    public Button customersButtonR;
    public TableView reportsTable;
    public AnchorPane reportsPane;
    public RadioButton report1Radio;
    public ToggleGroup reportsToggle;
    public RadioButton report2Radio;
    public RadioButton report3Radio;
    public Button generateReportButton;

    Stage stage;
    Parent scene;

    /**
     * opens appointments form
     */
    public void onAppointmentsButtonC(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AppointmentsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
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
     * selects report to be generated
     */
    public void onReport1Radio(ActionEvent actionEvent) {
    }

    /**
     * selects report to be generated
     */
    public void onReport2Radio(ActionEvent actionEvent) {
    }

    /**
     * selects report to be generated
     */
    public void onReport3Radio(ActionEvent actionEvent) {
    }

    /**
     * generates report
     */
    public void onGenerateReportButton(ActionEvent actionEvent) throws IOException {
        if (report1Radio.isSelected()) {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/Report1Form.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        } else if (report2Radio.isSelected()) {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/Report2Form.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        } else if (report3Radio.isSelected()) {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/Report3Form.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
}
