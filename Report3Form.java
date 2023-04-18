package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBCustomers;
import com.example.c195tasklangridge.model.Appointments;
import com.example.c195tasklangridge.model.Customers;
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
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines Report3Form class
 */
public class Report3Form implements Initializable {
    public AnchorPane report3Pane;
    public Label report3Label;
    public TableView<Customers> report3Table;
    public Button reportsReturnButton3;
    public TableColumn<Customers, Integer> customerIDCol;
    public TableColumn<Customers, String> customerNameCol;
    public TableColumn<Customers, String> addressCol;
    public TableColumn<Customers, String> phoneNumberCol;
    public TableColumn<Customers, String> postalCodeCol;
    public TableColumn<Customers, Integer> divisionIDCol;
    public RadioButton USRadio;
    public RadioButton canadaRadio;
    public RadioButton UKRadio;
    public ToggleGroup countryRadio;

    Stage stage;
    Parent scene;

    /**
     * opens reports form
     */
    public void onReportsReturnButton3(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/ReportsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * displays all customers in the US
     */
    public void onUSRadio(ActionEvent actionEvent) throws SQLException {
        ObservableList<Customers> allCustomers = DBCustomers.getUSCustomers();
        report3Table.setItems(allCustomers);
    }

    /**
     * displays all customers in Canada
     */
    public void onCanadaRadio(ActionEvent actionEvent) throws SQLException {
        ObservableList<Customers> allCustomers = DBCustomers.getCanadaCustomers();
        report3Table.setItems(allCustomers);
    }

    /**
     * displays all customers in the UK
     */
    public void onUKRadio(ActionEvent actionEvent) throws SQLException {
        ObservableList<Customers> allCustomers = DBCustomers.getUKCustomers();
        report3Table.setItems(allCustomers);
    }

    /**
     * initializes Report3Form values
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));

        try {
            ObservableList<Customers> allCustomers = DBCustomers.getUSCustomers();
            report3Table.setItems(allCustomers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
