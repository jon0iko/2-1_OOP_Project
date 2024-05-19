package org.example.salon;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class generator {


    public static Date generateCurrentDate() {
        long currentTimeMillis = System.currentTimeMillis();
        java.util.Date currentDateUtil = new java.util.Date(currentTimeMillis);
        return new Date(currentDateUtil.getTime());
    }

    public static Time generateCurrentTime() {
        // Get the current time
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTime = calendar.getTime();

        // Convert the current time to SQL time format
        return new Time(currentTime.getTime());
    }
}
