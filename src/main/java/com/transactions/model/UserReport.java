package com.transactions.model;

import java.io.Serializable;

public class UserReport implements Serializable {
    private int id;
    private Report report;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Report getReport() {
        return report;
    }
    public void setReport(Report report) {
        this.report = report;
    }
}
