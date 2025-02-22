package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeListTest {

    @Test
    public void testEmptyList() {
        TimeList<Time> empty = new TimeList<>();

        assertNull(empty.head);
    }

    @Test
    public void testAppendDataToEmptyList() {
        TimeList<Time> empty = new TimeList<>();
        Time toAdd = new Time(30, 20, 10);

        empty.appendData(toAdd);
        assertEquals("{30 : 20 : 10} -> NULL", empty.toString());
    }

    @Test
    public void testAppendDataToOneItem() {
        TimeList<Time> list = new TimeList<>();
        Time one = new Time(1, 0, 30);
        list.appendData(one);

        Time toAdd = new Time(30, 20, 10);

        list.appendData(toAdd);
        assertEquals("{01 : 00 : 30} -> {30 : 20 : 10} -> NULL", list.toString());
    }

    @Test
    public void testAppendDataToTwoItems() {
        TimeList<Time> list = new TimeList<>();
        Time one = new Time(1, 0, 30);
        Time two = new Time(0, 4, 5);
        list.appendData(one);
        list.appendData(two);

        Time toAdd = new Time(30, 20, 10);

        list.appendData(toAdd);
        assertEquals("{01 : 00 : 30} -> {00 : 04 : 05} -> {30 : 20 : 10} -> NULL", list.toString());
    }
}