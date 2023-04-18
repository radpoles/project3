package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.*;
import com.example.c195tasklangridge.model.*;
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

import static com.example.c195tasklangridge.DAO.DBCustomers.getAllCustomers;
import static com.example.c195tasklangridge.DAO.DBCustomers.insert;

/**
 * defines AddCustomerForm class
 */
public class AddCustomerForm implements Initializable {
    public ComboBox FLDComboBox;
    public ComboBox countryComboBox;
    public AnchorPane addCustomerPane;
    public TextField addCustomerIDTextC;
    public Label addCustomerLabel;
    public TextField addCustomerNameText;
    public TextField addAddressText;
    public TextField addPostalCodeText;
    public TextField addPhoneText;
    public Label addCustomerIDLabelC;
    public Label addCustomerNameLabel;
    public Label addAddressLabel;
    public Label addPostalCodeLabel;
    public Label addPhoneLabel;
    public Button addSaveButtonC;
    public Button addCancelButtonC;
    public Label addFLDLabel;
    public Label addCountryLabel;


    Stage stage;
    Parent scene;


    private static int customerID = 3;

    /**
     * returns the customer ID
     * @return customerID returns the customer ID
     */
    public static int getCustomerID() {
        return ++customerID;
    }


    /**
     * saves new customer info
     */
    public void onAddSaveButtonC(ActionEvent actionEvent) throws IOException, SQLException {

        int customerID = getCustomerID();
        String customerName = addCustomerNameText.getText();
        String address = addAddressText.getText();
        String postalCode = addPostalCodeText.getText();
        String phone = addPhoneText.getText();
        int divisionID = DBFirstLevelDivisions.selectID(FLDComboBox.getValue().toString());
        System.out.println(divisionID);

        insert(customerID, customerName, address, postalCode, phone, divisionID);

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/CustomersForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * cancels customer addition
     */
    public void onAddCancelButtonC(ActionEvent actionEvent) throws IOException {
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
     * sets FLD combo box with corresponding countries
     */
    public void onCountryComboBox() throws SQLException {
        String selectedCountry = (String) countryComboBox.getSelectionModel().getSelectedItem();
        //System.out.println(selectedCountry);

        if (Objects.equals(selectedCountry, "U.S")) {
            FLDComboBox.setItems(DBFirstLevelDivisions.selectUS());
        } else if (Objects.equals(selectedCountry, "Canada")) {
            FLDComboBox.setItems(DBFirstLevelDivisions.selectCanada());
        } else if (Objects.equals(selectedCountry, "UK")) {
            FLDComboBox.setItems(DBFirstLevelDivisions.selectUK());
        }
    }

    /**
     * initializes AddCustomerForm values
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
