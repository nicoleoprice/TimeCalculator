package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeListTest {

    @Test
    public void testEmptyList() {
        TimeList<Time> empty = new TimeList<>();

        assertNull(empty.head);
    }


    ///////Append/////////

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

    //////Delete//////

    @Test
    public void testNullPointerExceptionDeleteData() {
        TimeList<Time> empty = new TimeList<>();
        Time toDelete = new Time(2, 50, 45);
        int location = 0;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            empty.deleteData(location);
        });

        assertTrue(exception.getMessage().contains("This list is empty!"));
    }

    @Test
    public void testDeleteOne() {
        TimeList<Time> list = new TimeList<>();
        Time one = new Time(1, 0, 30);
        list.appendData(one);
        int location = 0;

        list.deleteData(location);
        System.out.print(list);
        assertNull(list.deleteData(location));
    }

    @Test
    public void testDeleteLast() {
        TimeList<Time> list = new TimeList<>();
        Time one = new Time(1, 0, 30);
        Time two = new Time(0, 4, 5);
        int location = 1;
        list.appendData(one);
        list.appendData(two);

        list.deleteData(location);
        assertEquals("{01 : 00 : 30} -> NULL", list.toString());
    }

    @Test
    public void testDeleteMiddle() {
        TimeList<Time> list = new TimeList<>();
        Time one = new Time(1, 0, 30);
        Time two = new Time(0, 4, 5);
        Time three = new Time(30, 20, 10);
        int location = 1;

        list.appendData(one);
        list.appendData(two);
        list.appendData(three);

        list.deleteData(location);
        assertEquals("{01 : 00 : 30} -> {30 : 20 : 10} -> NULL", list.toString());
    }

    @Test
    public void testDeleteFirst() {
        TimeList<Time> list = new TimeList<>();
        Time one = new Time(1, 0, 30);
        Time two = new Time(0, 4, 5);
        Time three = new Time(30, 20, 10);
        int location = 0;

        list.appendData(one);
        list.appendData(two);
        list.appendData(three);

        list.deleteData(location);
        assertEquals("{00 : 04 : 05} -> {30 : 20 : 10} -> NULL", list.toString());
    }
}