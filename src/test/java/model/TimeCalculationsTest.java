package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculationsTest {

    @Test
    public void testConvertSeconds() {
        Time testOne = new Time(0, 0, 75);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        Time actualTime = tc.convertTime(testOne.getMinutes(), testOne.getSeconds(), true);

        assertEquals("{00 : 01 : 15}", actualTime.toString());
    }

    @Test
    public void testConvertMinutes() {
        Time testOne = new Time(0, 126, 0);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        Time actualTime = tc.convertTime(testOne.getHours(), testOne.getMinutes(), false);

        assertEquals("{02 : 06 : 00}", actualTime.toString());
    }

    @Test
    public void testConvertTooLargeSecondsWithMinutes() {
        //this will test too large of seconds with existing minutes
        Time testOne = new Time(0, 3, 189);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        Time actualTime = tc.convertTime(testOne.getMinutes(), testOne.getSeconds(), true);

        assertEquals("{00 : 06 : 09}", actualTime.toString());
    }

    @Test
    public void testConvertTooLargeMinutesWithHours() {
        //this will test too large of minutes with existing hours
        Time testOne = new Time(25, 126, 0);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        Time actualTime = tc.convertTime(testOne.getHours(), testOne.getMinutes(), false);

        assertEquals("{27 : 06 : 00}", actualTime.toString());
    }
}