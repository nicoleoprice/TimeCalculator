package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculationsTest {

    ////START CONVERT TIME TESTS////

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

    @Test
    public void testConvertUnder60Seconds() {
        Time testOne = new Time(0, 1, 59);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        Time actualTime = tc.convertTime(testOne.getMinutes(), testOne.getSeconds(), true);

        //convert time is only used for toAdd, so total wouldn't equal testOne
        assertEquals("{00 : 00 : 00}", actualTime.toString());
    }

    @Test
    public void testConvertUnder60Minutess() {
        Time testOne = new Time(0, 1, 59);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        Time actualTime = tc.convertTime(testOne.getHours(), testOne.getMinutes(), false);

        //convert time is only used for toAdd, so total wouldn't equal testOne
        assertEquals("{00 : 00 : 00}", actualTime.toString());
    }

    ///END OF CONVERT TIME TESTS///


    ///START OF TO ADD TESTS///

    @Test
    public void testToAddTooLargeSecondsAndTooLargeMinutes() {
        Time testOne = new Time(0, 126, 75);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        tc.toAdd();
        Time actualTime = tc.total;

        assertEquals("{02 : 07 : 15}", actualTime.toString());
    }

    /**
     * This is to test if the convertTime() works correctly if both seconds and minutes are under the 60 threshold
     */
    @Test
    public void testToAddTimeUnder60SecondsAnd60Minutes() {
        Time testOne = new Time(10, 4, 45);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        TimeCalculations tc = new TimeCalculations(list);

        tc.toAdd();
        Time actualTime = tc.total;

        assertEquals("{10 : 04 : 45}", actualTime.toString());
    }

    @Test
    public void testToAddTwoTimes() {
        Time testOne = new Time(10, 4, 45);
        Time testTwo = new Time(0, 5, 15);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        list.appendData(testTwo);
        TimeCalculations tc = new TimeCalculations(list);

        tc.toAdd();
        Time actualTime = tc.total;

        assertEquals("{10 : 10 : 00}", actualTime.toString());
    }

    @Test
    public void testToAddThreeTimes() {
        Time testOne = new Time(10, 4, 45);
        Time testTwo = new Time(0, 5, 15);
        Time testThree = new Time(111, 70, 20);
        TimeList<Time> list = new TimeList<>();
        list.appendData(testOne);
        list.appendData(testTwo);
        list.appendData(testThree);
        TimeCalculations tc = new TimeCalculations(list);

        tc.toAdd();
        Time actualTime = tc.total;

        assertEquals("{122 : 20 : 20}", actualTime.toString());
    }

    @Test
    public void testToAddEmptyList() {
        TimeList<Time> list = new TimeList<>();
        TimeCalculations tc = new TimeCalculations(list);

        tc.toAdd();
        Time actualTime = tc.total;

        assertEquals("{00 : 00 : 00}", actualTime.toString());
    }

    //END OF TO ADD TESTS//
}