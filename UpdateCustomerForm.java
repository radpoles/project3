package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBCountries;
import com.example.c195tasklangridge.DAO.DBCustomers;
import com.example.c195tasklangridge.DAO.DBFirstLevelDivisions;
import com.example.c195tasklangridge.model.Countries;
import com.example.c195tasklangridge.model.Customers;
import com.example.c195tasklangridge.model.FirstLevelDivisions;
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
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines UpdateCustomersForm class
 */
public class UpdateCustomerForm implements Initializable {
    public AnchorPane updateCustomerPane;
    public Label updateCustomerLabel;
    public TextField updateCustomerNameText;
    public TextField updateAddressText;
    public TextField updatePostalCodeText;
    public TextField updatePhoneText;
    public Label updateCustomerNameLabel;
    public Label updateAddressLabel;
    public Label updatePostalCodeLabel;
    public Label updatePhoneLabel;
    public Button updateSaveButtonC;
    public Button updateCancelButtonC;
    public Label updateCustomerIDLabelC;
    public TextField updateCustomerIDTextC;
    public Label updateFLDLabel;
    public Label updateCountryLabel;
    public ComboBox <String> FLDComboBox;
    public ComboBox <String> countryComboBox;


    Stage stage;
    Parent scene;

    /**
     * saves updated customer info
     */
    public void onUpdateSaveButtonC(ActionEvent actionEvent) throws IOException, SQLException {

        int customerID = customerData.getCustomerID();
        System.out.println(customerID);
        String customerName = updateCustomerNameText.getText();
        String address = updateAddressText.getText();
        String postalCode = updatePostalCodeText.getText();
        String phone = updatePhoneText.getText();
        int divisionID = DBFirstLevelDivisions.selectID(FLDComboBox.getValue());

        DBCustomers.update(customerName, address, postalCode, phone, divisionID, customerID);

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/CustomersForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * cancels customer changes
     */
    public void onUpdateCancelButtonC(ActionEvent actionEvent) throws IOException {
        Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
        warning.setTitle("Cancel");
        warning.setContentText("Are you sure you want to leave without saving?");
        warning.showAndWait();
        if (warning.getResult() == ButtonType.OK) {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/CustomersForm.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void onFLDComboBox(ActionEvent actionEvent) {
    }

    /**
     * sets country combo box
     */
    public void onCountryComboBox() throws SQLException {
        String selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        //System.out.println(selectedCountry);

        if (Objects.equals(selectedCountry, "U.S")) {
            FLDComboBox.setItems(DBFirstLevelDivisions.selectUS());
        } else if (Objects.equals(selectedCountry, "Canada")) {
            FLDComboBox.setItems(DBFirstLevelDivisions.selectCanada());
        } else if (Objects.equals(selectedCountry, "UK")) {
            FLDComboBox.setItems(DBFirstLevelDivisions.selectUK());
        }
    }

    Customers customerData = new Customers(0, "Mister Man", "123 Main St", "94551", "123-456-7890", 36);

    /**
     * sends customer data to Update Form
     */
    public void sendCustomer(Customers customers) throws SQLException {
        customerData = customers;
        updateCustomerIDTextC.setText(String.valueOf(customers.getCustomerID()));
        updateCustomerNameText.setText(customers.getCustomerName());
        updateAddressText.setText(String.valueOf(customers.getAddress()));
        updatePostalCodeText.setText(String.valueOf(customers.getPostalCode()));
        updatePhoneText.setText(String.valueOf(customers.getPhone()));

        int divID = customers.getDivisionID();

        if (divID < 60) {
            countryComboBox.setValue("U.S");
            FLDComboBox.setItems(DBFirstLevelDivisions.selectUS());
        } else if (divID < 100 && divID > 59) {
            countryComboBox.setValue("Canada");
            FLDComboBox.setItems(DBFirstLevelDivisions.selectCanada());
        } else if (divID > 100) {
            countryComboBox.setValue("UK");
            FLDComboBox.setItems(DBFirstLevelDivisions.selectUK());
        }

        FLDComboBox.setValue(DBFirstLevelDivisions.selectName(divID));
    }

    /**
     * initializes UpdateCustomerForm values
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        ObservableList<String> divisionsByCountry = FXCollections.observableArrayList();
        try {
            for (Countries C : DBCountries.getAllCountries()) {
                countryNames.add(C.getCountry());
            }
            countryComboBox.setItems(countryNames);

            for (FirstLevelDivisions D : DBFirstLevelDivisions.getAllFirstLevelDivisions()) {
                divisionsByCountry.add(D.getDivision());
            }
            FLDComboBox.setItems(divisionsByCountry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
