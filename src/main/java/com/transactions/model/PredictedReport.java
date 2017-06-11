package com.transactions.model;

public class PredictedReport {
    private Entries average;
    private int id;
    private int month;
    private int year;

    public Entries getAverage() {
        return average;
    }

    public void setAverage(Entries average) {
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
