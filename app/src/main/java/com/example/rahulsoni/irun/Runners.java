package com.example.rahulsoni.irun;

/**
 * Created by Rahul Soni on 15/01/2018.
 */

public class Runners {
    private long date;
    private float distance;
    private double time;

    public Runners(int date, float distance, int time) {
        this.date = date;
        this.distance = distance;
        this.time = time;
    }


    public float getDistance() {

        return distance;
    }

    public long getDate() {
        return date;
    }


    public double getTime() {
       return time;
    }
}

