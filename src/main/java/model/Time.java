package model;

/**
 * Each time the user enters will be an individual object
 */

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    //default constructor so table does not look empty
    public Time() {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }


    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return String.format("{%02d : %02d : %02d}", hours, minutes, seconds);
    }
}
