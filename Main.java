package com.example.c195tasklangridge;

import com.example.c195tasklangridge.DAO.*;
import com.example.c195tasklangridge.timeZone.TimeZone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main application function
     */
    public static void main(String[] args) throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("language", Locale.getDefault());

        /*
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("es")) {
            System.out.println(rb.getString("Login"));
        }
        //checks user system language and prints "login" in French (or Spanish!)
        */

        System.out.println("entering DB"); // lets user know connection is working
        DBC.openConnection();

        //System.out.println(DBCustomers.getAllCustomers());

        //DBFirstLevelDivisions.selectID("Alabama");

        //DBFirstLevelDivisions.selectName(1);

//        System.out.println(TimeZone.getZoneID());
//        Timestamp converted = TimeZone.convertLocalToUTC(Timestamp.valueOf(LocalDateTime.now()));
//        System.out.println(converted + " UTC");
//        Timestamp local = TimeZone.convertUTCToLocal(converted);
//        System.out.println(local + " local");

        //TimeZone.getAllZoneIDs();

        //TimeZone.getZDT();

        //DBContacts.select(); displays contact info

        //DBAppointments.select(2); selects appointment with a certain id number

        //DBCountries.getAllCountries(); //prints out list of all countries

        //LoginForm.recordData();

        //DBFirstLevelDivisions.selectUK();

        //DBC.checkDateConversion(); checks date/time conversion

        /*
        int rowsAffected = DBCountries.delete(8201);  //confirms rows were deleted and displays a message

        if(rowsAffected > 0) {
            System.out.println("Delete Successful");
        } else {
            System.out.println("Delete Failed");
        }
         */

        launch();

        System.out.println("exiting DB");  // lets user know they are disconnecting
        DBC.closeConnection();
    }
}

