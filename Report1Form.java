package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBAppointments;
import com.example.c195tasklangridge.model.Appointments;
import com.example.c195tasklangridge.model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines Report1Form class
 */
public class Report1Form implements Initializable {
    public AnchorPane report1Pane;
    public Label report1Label;
    public Button reportsReturnButton1;
    public TableView<Reports> report1TableT;
    public TableColumn<Reports, Integer> apptsNumColT;
    public TableColumn<Reports, String> typeCol;
    public TableView<Reports> report1TableM;
    public TableColumn<Reports,Integer> apptsNumColM;
    public TableColumn<Reports, String> monthCol;

    Stage stage;
    Parent scene;

    /**
     * opens reports form
     */
    public void onReportsReturnButton1(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/ReportsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * initializes Report1Form values
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        apptsNumColT.setCellValueFactory(new PropertyValueFactory<>("Count"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        apptsNumColM.setCellValueFactory(new PropertyValueFactory<>("Count"));

        try {
            report1TableT.setItems(DBAppointments.getAllAppointmentsType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            report1TableM.setItems(DBAppointments.getAllAppointmentsMonth());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
