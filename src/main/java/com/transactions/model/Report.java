package com.transactions.model;

import java.util.Dictionary;
import java.util.HashMap;

public class Report {
    private HashMap<String,Entries> lineItem;
    private HashMap<String,Entries> average;

    public HashMap<String, Entries> getLineItem() {
        return lineItem;
    }

    public void setLineItem(HashMap<String, Entries> lineItem) {
        this.lineItem = lineItem;
    }

    public HashMap<String, Entries> getAverage() {
        return average;
    }

    public void setAverage(HashMap<String, Entries> average) {
        this.average = average;
    }
}

