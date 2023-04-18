package com.example.c195tasklangridge.controller;

import com.example.c195tasklangridge.DAO.DBAppointments;
import com.example.c195tasklangridge.DAO.DBUsers;
import com.example.c195tasklangridge.model.Appointments;
import com.example.c195tasklangridge.model.Users;
import com.example.c195tasklangridge.timeZone.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * defines LoginForm class
 */
public class LoginForm implements Initializable {

    public AnchorPane loginPane;
    public Button enterButton;
    public Label loginLabel;
    public Label passwordLabel;
    public Label usernameLabel;
    public TextField usernameText;
    public PasswordField passwordText;
    public Label timeZoneLabel;
    public Label timeZoneText;

    Stage stage;
    Parent scene;

    /**
     * attempts to login
     */
    public void onEnterButton(ActionEvent actionEvent) throws IOException, SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("language", Locale.getDefault());

        Users au = DBUsers.authenticateUser(usernameText.getText(), passwordText.getText());
        if (au != null) {
            //login successful
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("User " + usernameText.getText() + " login successful at " + Timestamp.valueOf(LocalDateTime.now()));
            pw.close();

            if (DBAppointments.selectUpcomingStart() == 0) {
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setTitle("Notice");
                error.setContentText("No appointments within 15 minutes");
                error.show();
            } else {
                int selectedID = DBAppointments.selectUpcomingStart();
                String selectedStart = DBAppointments.selectStart(selectedID);

                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setTitle("Reminder");
                error.setContentText("Appointment " + selectedID + " starts at " + Timestamp.valueOf(selectedStart));
                error.show();
            }

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c195tasklangridge/AppointmentsForm.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            //login failed
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("User " + usernameText.getText() + " login failed at " + Timestamp.valueOf(LocalDateTime.now()));
            pw.close();

            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setTitle(rb.getString("Error"));
            error.setContentText(rb.getString("Invalid") + " " + rb.getString("account") + " " + rb.getString("information"));
            error.setHeaderText(rb.getString("Message"));
            Button okButton = (Button) error.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setText(rb.getString("Ok"));
            error.show();
        }
    }

    /**
     * initializes LoginForm values
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle rb = ResourceBundle.getBundle("language", Locale.getDefault());
        loginLabel.setText(rb.getString("Login"));
        usernameLabel.setText(rb.getString("Username"));
        passwordLabel.setText(rb.getString("Password"));
        enterButton.setText(rb.getString("Enter"));
        timeZoneLabel.setText(rb.getString("TimeZone"));
        timeZoneText.setText(String.valueOf(ZonedDateTime.now().getZone()));
    }

}
