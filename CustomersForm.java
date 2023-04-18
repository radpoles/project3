package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBCountries;
import com.example.c195tasklangridge.DAO.DBCustomers;
import com.example.c195tasklangridge.model.Appointments;
import com.example.c195tasklangridge.model.Countries;
import com.example.c195tasklangridge.model.Customers;
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
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines CustomersForm class
 */
public class CustomersForm implements Initializable {
    public AnchorPane customersPane;
    public TableView<Customers> customersTable;
    public Label customersLabel;
    public Button updateCustomerButton;
    public Button deleteCustomerButton;
    public Button appointmentsButtonC;
    public Button reportsButtonC;
    public TableColumn<Customers, Integer> customerIDCol;
    public TableColumn<Customers, String> customerNameCol;
    public TableColumn<Customers, String> addressCol;
    public TableColumn<Customers, String> phoneNumberCol;
    public TableColumn<Customers, String> postalCodeCol;
    public TableColumn<Customers, Integer> divisionIDCol;
    public Button addCustomerButton;

    Stage stage;
    Parent scene;

    /**
     * adds customer
     */
    public void onAddCustomerButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AddCustomerForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * updates customer
     */
    public void onUpdateCustomerButton(ActionEvent actionEvent) throws SQLException, IOException {
        Customers selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Customer must be selected");
            error.show();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/c195tasklangridge/UpdateCustomerForm.fxml"));
            loader.load();

            UpdateCustomerForm UCFController = loader.getController();
            UCFController.sendCustomer(customersTable.getSelectionModel().getSelectedItem());
            UCFController.updateCustomerNameText.setText(selectedCustomer.getCustomerName());
            UCFController.updateCustomerIDTextC.setText(String.valueOf(selectedCustomer.getCustomerID()));
            UCFController.updatePhoneText.setText(selectedCustomer.getPhone());
            UCFController.updatePostalCodeText.setText(selectedCustomer.getPostalCode());
            UCFController.updateAddressText.setText(selectedCustomer.getAddress());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * deletes customer
     */
    public void onDeleteCustomerButton(ActionEvent actionEvent) throws SQLException, IOException {
        Customers selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle("Error");
            error.setContentText("Customer must be selected");
            error.show();
        } else {
                int selectedID = selectedCustomer.getCustomerID();
                String selectedName = selectedCustomer.getCustomerName();
                Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
                warning.setTitle("Caution");
                warning.setContentText("Are you sure you want to delete this customer?");
                warning.showAndWait();
                if (warning.getResult() == ButtonType.OK) {
                    DBCustomers.delete(selectedID);
                    Alert error = new Alert(Alert.AlertType.INFORMATION);
                    error.setTitle("Success");
                    error.setContentText("Customer " + selectedID + " " + selectedName + " has been deleted.");
                    error.show();
                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/CustomersForm.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
    }


    /**
     * opens appointment form
     */
    public void onAppointmentsButtonC(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AppointmentsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * opens reports form
     */
    public void onReportsButtonC(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/ReportsForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * initializes CustomersForm values
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
            ObservableList<Customers> allCustomers = DBCustomers.getAllCustomers();
            customersTable.setItems(allCustomers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}