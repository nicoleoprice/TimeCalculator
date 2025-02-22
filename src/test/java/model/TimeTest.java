package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    @Test
    public void testTime() {
        Time test = new Time(00, 30, 10);

        assertEquals("{00 : 30 : 10}", test.toString());

        Time testZeroes = new Time(0, 0, 0);

        assertEquals("{00 : 00 : 00}", testZeroes.toString());
    }
}