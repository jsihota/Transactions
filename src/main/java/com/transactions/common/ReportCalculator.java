package com.transactions.common;

import com.transactions.model.Entries;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.UserReport;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@Component
public class ReportCalculator {
    public Report calculate(List<Transaction> transactions){
        HashMap<String,Entries> lineItems = new HashMap<>();
        double spent = 0.0;
        double income = 0.0;
        int incomeCount = 0;
        int spentCount  = 0;
        for(Transaction transaction: transactions){
            String category = transaction.getCategorization().toLowerCase();
            String month = transaction.getMonth();
            double amount = transaction.getAmount();

            switch (category) {
                case "Paycheck":
                    income = income +
                    incomeCount ++;
                    if(lineItems.containsKey(month)){
                        Entries entries = lineItems.get(month);
                        entries.setIncome(entries.getIncome() + amount);
                        lineItems.put(month, entries);
                    }
                    else{
                        Entries entries = new Entries();
                        entries.setIncome(amount);
                        lineItems.put(month, entries);
                    }
                    break;

                default:
                    spent = spent + amount;
                    spentCount++;
                    if(lineItems.containsKey(month)){
                        Entries entries = lineItems.get(month);
                        entries.setSpent(entries.getSpent() + amount);
                        lineItems.put(month, entries);
                    }
                    else{
                        Entries entries = new Entries();
                        entries.setSpent(amount);
                        lineItems.put(month, entries);
                    }
                    break;
            }
        }
        Report report = new Report();
        report.setLineItem(lineItems);
        report.setAverage(GetAverageEntry(spent,income,incomeCount,spentCount));
        return report;
    }

    private HashMap<String,Entries> GetAverageEntry(double spent,
            double income,
            int incomeCount,
            int spentCount){
        HashMap<String,Entries> averageEntry = new HashMap<String,Entries>();
        Entries entry = new Entries();
        entry.setIncome(spent/spentCount);
        entry.setIncome(income/incomeCount);
        averageEntry.put("Average",entry);
        return averageEntry;
    }
}
