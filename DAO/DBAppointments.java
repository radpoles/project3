package com.example.c195tasklangridge.DAO;

import com.example.c195tasklangridge.model.Appointments;
import com.example.c195tasklangridge.model.Reports;
import com.example.c195tasklangridge.timeZone.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * defines DBAppointments class
 */
public class DBAppointments {

    /**
     * displays all appointments
     */
    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        ObservableList<Appointments> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                //Timestamp newStart = TimeZone.convertUTCToLocal(start);
                //System.out.println(start + " UTC to " + newStart + " local");
                Timestamp end = rs.getTimestamp("End");
                //Timestamp newEnd = TimeZone.convertUTCToLocal(end);
                //System.out.println(end + " UTC to " + newEnd + " local");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
                list.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * adds new appointment
     */
    public static void insert(int appointmentID, String title, String description, String location, String type, Timestamp start, Timestamp end, int customerID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        //Timestamp newStart = TimeZone.convertLocalToUTC(start);
        ps.setTimestamp(6, start);
        //System.out.println(start + " local to " + newStart + " UTC");
        //Timestamp newEnd = TimeZone.convertLocalToUTC(end);
        ps.setTimestamp(7, end);
        //System.out.println(end + " local to " + newEnd + " UTC");
        ps.setInt(8, customerID);
        ps.setInt(9, userID);
        ps.setInt(10, contactID);
        ps.executeUpdate();

//        System.out.println(start);
//        System.out.println(newStart);
//        System.out.println(end);
//        System.out.println(newEnd);
    }

    /**
     * updates existing appointment
     */
    public static void update(String title, String description, String location, int contactID, String type, Timestamp start, Timestamp end, int customerID, int userID, int appointmentID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Contact_ID = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setInt(4, contactID);
        ps.setString(5, type);
        //Timestamp newStart = TimeZone.convertLocalToUTC(start);
        ps.setTimestamp(6, start);
        //System.out.println(start + " local to " + newStart + " UTC");
        //Timestamp newEnd = TimeZone.convertLocalToUTC(end);
        ps.setTimestamp(7, end);
        //System.out.println(end + " local to " + newEnd + " UTC");
        ps.setInt(8, customerID);
        ps.setInt(9, userID);
        ps.setInt(10, appointmentID);
        ps.executeUpdate();

//        System.out.println(start);
//        System.out.println(newStart);
//        System.out.println(end);
//        System.out.println(newEnd);
    }

    /**
     * deletes appointment
     */
    public static void delete(int appointmentID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ps.executeUpdate();
    }

    /**
     * prints selected appointment info
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            //System.out.println("Appointment for " + type + " begins at " + start);
        }
    }

    /**
     * prints out selected appointment info
     */
    public static void select(int appointmentID) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            // System.out.println("Appointment for " + type + " begins at " + start);
            //System.out.println("Appointment ID: " + appointmentID);
        }
    }

    /**
     * converts appointment ID to corresponding appointment title
     */
    public static String selectName(int appointmentID) throws SQLException {
        String sql = "SELECT Title FROM appointments WHERE Appointment_Id = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String title = rs.getString("Title");
            //System.out.println(title);
            return title;
        }
        return null;
    }

    /**
     * converts appointment title to corresponding appointment ID
     */
    public static int selectID(String title) throws SQLException {
        String sql = "SELECT Appointment_ID FROM appointments WHERE Title = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_Id");
            //System.out.println(appointmentID);
            return appointmentID;
        }
        return 0;
    }

    /**
     * displays all appointments for contact with contact ID 1
     */
    public static ObservableList<Appointments> getAppointments1() throws SQLException {
        ObservableList<Appointments> list1 = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE Contact_ID = '1'";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
                list1.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list1;
    }

    /**
     * displays all appointments for contact with contact ID 2
     */
    public static ObservableList<Appointments> getAppointments2() throws SQLException {
        ObservableList<Appointments> list2 = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE Contact_ID = '2'";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
                list2.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list2;
    }

    /**
     * displays all appointments for contact with contact ID 3
     */
    public static ObservableList<Appointments> getAppointments3() throws SQLException {
        ObservableList<Appointments> list3 = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE Contact_ID = '3'";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
                list3.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list3;
    }

    /**
     * displays all appointments for the current month
     * Lambda #2 sorts appointments by month
     */
    public static ObservableList<Appointments> getAppointmentsMonth() throws SQLException {
        //ObservableList<Appointments> listMonth = FXCollections.observableArrayList();
        List listMonth;

        listMonth = DBAppointments.getAllAppointments().stream().filter(a -> a.getStart().toLocalDateTime().getMonth() == LocalDateTime.now().getMonth()).collect(Collectors.toList());
        return FXCollections.observableArrayList(listMonth);

//        try {
//            String sql = "SELECT * from appointments";
//
//            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int appointmentID = rs.getInt("Appointment_ID");
//                String title = rs.getString("Title");
//                String description = rs.getString("Description");
//                String location = rs.getString("Location");
//                String type = rs.getString("Type");
//                Timestamp start = Timestamp.valueOf(rs.getTimestamp("Start").toLocalDateTime());
//                Timestamp end = Timestamp.valueOf(rs.getTimestamp("End").toLocalDateTime());
//                int customerID = rs.getInt("Customer_ID");
//                int userID = rs.getInt("User_ID");
//                int contactID = rs.getInt("Contact_ID");
//
//                Month current = LocalDateTime.now().getMonth();
//                //System.out.println(current);
//
//                if (current == start.toLocalDateTime().getMonth() && current == end.toLocalDateTime().getMonth()) {
//                    Appointments A = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
//                    listMonth.add(A);
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return listMonth;
    }

    /**
     * displays all appointments for the current week
     */
    public static ObservableList<Appointments> getAppointmentsWeek() throws SQLException {
        ObservableList<Appointments> listWeek = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                int thisWeek = LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfYear());
                //System.out.println(thisWeek);

                LocalDate currentStart = start.toLocalDateTime().toLocalDate();
                //System.out.println(currentStart);
                LocalDate currentEnd = end.toLocalDateTime().toLocalDate();
                //System.out.println(currentEnd);

                int weekOfYearS = currentStart.get(WeekFields.of(Locale.getDefault()).weekOfYear());
                //System.out.println(weekOfYearS);
                int weekOfYearE = currentEnd.get(WeekFields.of(Locale.getDefault()).weekOfYear());
                //System.out.println(weekOfYearE);

                if (weekOfYearS == thisWeek && weekOfYearE == thisWeek) {
                    Appointments A = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
                    listWeek.add(A);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listWeek;
    }

    /**
     * displays # of appts by type
     */
    public static ObservableList<Reports> getAllAppointmentsType() throws SQLException {
        ObservableList<Reports> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Type, COUNT(*) FROM appointments GROUP BY Type";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //System.out.println(rs);
                String typeName = rs.getString("Type");
                int count = rs.getInt("COUNT(*)");

                Reports type = new Reports(typeName, count);
                list.add(type);
                //System.out.println(typeName); //this works
                //System.out.println(count); //this works

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * displays # of appts by month
     */
    public static ObservableList<Reports> getAllAppointmentsMonth() throws SQLException {
        ObservableList<Reports> listMonth = FXCollections.observableArrayList();

        try {
            String sql = "SELECT MONTH(Start), COUNT(*) FROM appointments GROUP BY MONTH(Start);";

            PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("COUNT(*)");
                String monthName = rs.getString("MONTH(Start)");

                Reports type = new Reports(monthName, count);
                listMonth.add(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listMonth;
    }

    /**
    * checks for overlapping appointment times
    */
    public static boolean checkForOverlap(LocalDateTime start, LocalDateTime end, int appointmentID) throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            Timestamp startTS = rs.getTimestamp("Start");
            Timestamp endTS = rs.getTimestamp("End");
            LocalDateTime startLDT = startTS.toLocalDateTime();
            LocalDateTime endLDT = endTS.toLocalDateTime();

            if ((startLDT.isEqual(start) && appointmentID != apptID) ||
                    (endLDT.isEqual(end) && appointmentID != apptID) ||
                    (startLDT.isBefore(start) && endLDT.isAfter(start) && appointmentID != apptID) ||
                    (startLDT.isBefore(end) && endLDT.isAfter(end) && appointmentID != apptID) ||
                    (startLDT.isBefore(start) && endLDT.isAfter(end) && appointmentID != apptID) ||
                    (startLDT.isAfter(start) && endLDT.isBefore(end) && appointmentID != apptID)) {
                //System.out.println("appointment conflict");
                return true;
            }
        }
    return false;
    }

    /**
     * checks for upcoming appointment
     */
    public static int selectUpcomingStart() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Timestamp start = rs.getTimestamp("Start");
            int appointmentID = rs.getInt("Appointment_ID");
            LocalDateTime startLDT = start.toLocalDateTime();
            //System.out.println(startLDT);
            LocalDateTime currentLDT = LocalDateTime.now();
            //System.out.println(currentLDT);
            if ((currentLDT == startLDT) || (currentLDT.plusMinutes(15).isEqual(startLDT)) || (currentLDT.isBefore(startLDT) && currentLDT.plusMinutes(15).isAfter(startLDT))) {
                System.out.println(appointmentID);
                return appointmentID;
            }
        }
        return 0;
    }

    /**
     * converts appointment ID to corresponding appointment start
     */
    public static String selectStart(int appointmentID) throws SQLException {
        String sql = "SELECT Start FROM appointments WHERE Appointment_Id = ?";
        PreparedStatement ps = DBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Timestamp start = rs.getTimestamp("Start");
            String startS = start.toString();
            //System.out.println(startS);
            return startS;
        }
        return null;
    }
}
