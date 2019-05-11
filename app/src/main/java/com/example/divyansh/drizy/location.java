package com.example.divyansh.drizy;

/**
 * Created by HP on 6/28/2017.
 */


public class location {
    private double Lat;
    private double Long;
    private double Alt;
    private double Speed;
    private int direction;
    public double getLat() {
        return Lat;
    }
    public void setLat(double lat) {
        Lat = lat;
    }
    public double getLong() {
        return Long;
    }
    public void setLong(double aLong) {Long = aLong;}
    public double getAlt() {
        return Alt;
    }
    public void setAlt(double alt) {
        Alt = alt;
    }
    public double getSpeed() {
        return Speed;
    }
    public void setSpeed(double speed) {
        Speed = speed;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int dire) {direction = dire;}

}