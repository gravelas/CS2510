package uk.ac.nulondon;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time() {
        hour = 0;
        minute = 0;
        second = 0;
    }

    public Boolean sameTime(Time t) {
        return (this.hour == t.getHour() && this.minute == t.getMinute() && this.second == t.getSecond());
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
