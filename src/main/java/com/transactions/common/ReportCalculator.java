package com.transactions.common;
import com.transactions.model.Entries;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.TransactionAction;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ReportCalculator {
    //TODO Clean this up
    public Report calculate(List<Transaction> transactions){
        HashMap<String,Entries> lineItems = new HashMap<>();
        HashMap<String,Entries> ignoredLineItems = new HashMap<>();
        double spent = 0.0;
        double income = 0.0;
        int incomeCount = 0;
        int spentCount  = 0;
        for(Transaction transaction: transactions){
            String category = transaction.getCategorization().toLowerCase();
            String month = transaction.getMonth();
            double amount = transaction.getAmount();

            if (category.equals("paycheck")) {
                if(transaction.getTransactionAction() == TransactionAction.ADDED) {
                    income = income + amount;
                    incomeCount++;
                    if (lineItems.containsKey(month)) {
                        Entries entries = lineItems.get(month);
                        entries.setIncome(entries.getIncome() + amount);
                        lineItems.put(month, entries);
                    } else {
                        Entries entries = new Entries();
                        entries.setIncome(amount);
                        entries.setSpent(0.0);
                        lineItems.put(month, entries);
                    }
                } else{
                    if (ignoredLineItems.containsKey(month)) {
                        Entries entries = ignoredLineItems.get(month);
                        entries.setIncome(entries.getIncome() + amount);
                        ignoredLineItems.put(month, entries);
                    } else {
                        Entries entries = new Entries();
                        entries.setIncome(amount);
                        entries.setSpent(0.0);
                        ignoredLineItems.put(month, entries);
                    }

                }
            }else{
                    if(transaction.getTransactionAction() == TransactionAction.ADDED) {
                        spent = spent + amount;
                        spentCount++;
                        if (lineItems.containsKey(month)) {
                            Entries entries = lineItems.get(month);
                            entries.setSpent(entries.getSpent() + amount);
                            lineItems.put(month, entries);
                        } else {
                            Entries entries = new Entries();
                            entries.setSpent(amount);
                            entries.setIncome(0.0);
                            lineItems.put(month, entries);
                        }
                    }else{
                        if (ignoredLineItems.containsKey(month)) {
                            Entries entries = ignoredLineItems.get(month);
                            entries.setSpent(entries.getSpent() + amount);
                            ignoredLineItems.put(month, entries);
                        } else {
                            Entries entries = new Entries();
                            entries.setSpent(amount);
                            entries.setIncome(0.0);
                            ignoredLineItems.put(month, entries);
                        }

                    }
            }
        }
        Report report = new Report();
        report.setLineItem(lineItems);
        report.setIgnoredLineItem(ignoredLineItems);
        report.setAverage(GetAverageEntry(spent,income,incomeCount,spentCount));
        return report;
    }

    private Entries GetAverageEntry(double spent,
                    double income,
                    int incomeCount,
                    int spentCount){
        Entries entry = new Entries();
        if(spentCount == 0){
            entry.setSpent(0.0);
        }else{
            entry.setSpent(spent/spentCount);
        }
        if (incomeCount == 0) {
            entry.setIncome(0.0);
        }else {
            entry.setIncome(income / incomeCount);
        }
        return entry;
    }


}
