package com.pretenders.monish;

/**
 * Created by danstavy on 03/03/2016.
 */
public class Driver {
    private double lat;
    private double lng;
    boolean full;

    public Driver(double latitude, double longitude) {
        lat = latitude;
        lng = longitude;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean getFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}

