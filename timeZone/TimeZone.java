package com.example.c195tasklangridge.timeZone;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.*;
import java.util.Set;

/**
 * defines TimeZone class
 */
public class TimeZone {
    private static ObservableList<LocalTime> startTimes = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endTimes = FXCollections.observableArrayList();

    private static void loadTimeLists() {
        ZonedDateTime estStart = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));

        ZonedDateTime localStart = estStart.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime localEnd = localStart.plusHours(14);
        while (localStart.isBefore(localEnd)) {
            startTimes.add(localStart.toLocalTime());
            localStart = localStart.plusMinutes(30);
            endTimes.add(localStart.toLocalTime());
        }
    }

    /**
     * gets all appointment start times
     * contains Lambda #1 to print out all possible start times within business hours
     */
    public static ObservableList<LocalTime> getAppointmentStartTimes() {
        if (startTimes.size() < 1) {
            loadTimeLists();
        startTimes.forEach(
                t-> {
                        System.out.println(t + " local time starts");
                });
        }
        return startTimes;
    }

    /**
     * gets all appointment end times
     */
    public static ObservableList<LocalTime> getAppointmentEndTimes() {
        if (endTimes.size() < 1) {
            loadTimeLists();
        }
        return endTimes;
    }

    /**
     * gets local date and time of user
     */
    public static LocalDateTime getLocalDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        return ldt;
    }

    /**
     * gets current zone ID of user
     */
    public static ZoneId getZoneID() {
        ZoneId z = ZoneId.systemDefault();
        //System.out.println(z);
        return z;
    }

    /**
     * gets all available zone IDs
     */
    public static Set<String> getAllZoneIDs() {
        Set<String> azi = ZoneId.getAvailableZoneIds();
        //System.out.println(azi);
        return azi;
    }

    /**
     * gets current zoned date time
     */
    public static ZonedDateTime getZDT() {
        ZonedDateTime zdt = ZonedDateTime.now();
        //System.out.println(zdt);
        return zdt;
    }

    /**
     * converts local to UTC
     *
     * @return the timestamp in UTC time
     */
    public static Timestamp convertLocalToUTC(Timestamp dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        LocalTime localTime = LocalTime.of(localDateTime.getHour(), localDateTime.getMinute());
        ZoneId localZoneId = ZoneId.systemDefault();
        ZonedDateTime localZDT = ZonedDateTime.of(localDate, localTime, localZoneId);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime localToUTCZDT = localZDT.withZoneSameInstant(utcZoneId);
        Timestamp newDateTime = Timestamp.valueOf(localToUTCZDT.toLocalDateTime());

        //System.out.println(dateTime + " at " + localZoneId + " to " + newDateTime + " at " + utcZoneId);

        return newDateTime;
    }

    /**
     * converts UTC to local
     *
     * @return the timestamp in local time
     */
    public static Timestamp convertUTCToLocal(Timestamp dateTime) {
        LocalDateTime utcDateTime = dateTime.toLocalDateTime();
        LocalDate utcDate = LocalDate.of(utcDateTime.getYear(), utcDateTime.getMonth(), utcDateTime.getDayOfMonth());
        LocalTime utcTime = LocalTime.of(utcDateTime.getHour(), utcDateTime.getMinute());
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.of(utcDate, utcTime, utcZoneId);
        ZoneId localZoneId = ZoneId.systemDefault();
        ZonedDateTime utcToLocalZDT = utcZDT.withZoneSameInstant(localZoneId);
        Timestamp newDateTime = Timestamp.valueOf(utcToLocalZDT.toLocalDateTime());

        //System.out.println(dateTime + " at " + utcZoneId + " to " + newDateTime + " at " + localZoneId);

        return newDateTime;
    }

    //Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]

    /**
     * converts time from UTC to EST
     */
    public LocalDateTime UTCtoEST(LocalDateTime time1) {
        LocalDateTime time2 = time1.minusHours(5);
        return time2;
    }

    /**
     * converts time from EST to UTC
     */
    public LocalDateTime ESTtoUTC(LocalDateTime time1) {
        LocalDateTime time2 = time1.plusHours(5);
        return time2;
    }
}
//example: 12:00pm EST + 5 hours = 5:00pm UTC



