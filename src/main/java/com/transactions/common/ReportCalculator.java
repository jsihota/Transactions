package com.transactions.common;
import com.transactions.model.Entries;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.TransactionAction;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ReportCalculator {

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
            //TODO Move paycheck to config
            if (category.equals("paycheck")) {
                if(transaction.getTransactionAction() == TransactionAction.ADDED) {
                    income = income + amount;
                    incomeCount++;
                    updateIncomeItem(lineItems,month,amount);

                } else{
                    updateIncomeItem(ignoredLineItems,month,amount);
                }
            }else{
                    if(transaction.getTransactionAction() == TransactionAction.ADDED) {
                        spent = spent + amount;
                        spentCount++;
                        updateSpentItem(lineItems,month,amount);
                    }else{
                        updateSpentItem(ignoredLineItems,month,amount);
                    }
            }
        }
        return getReport(lineItems, ignoredLineItems, spent, income, incomeCount, spentCount);
    }

    private Report getReport(HashMap<String, Entries> lineItems, HashMap<String, Entries> ignoredLineItems, double spent, double income, int incomeCount, int spentCount) {
        Report report = new Report();
        report.setLineItem(lineItems);
        report.setIgnoredLineItem(ignoredLineItems);
        report.setAverage(GetAverageEntry(spent,income,incomeCount,spentCount));
        return report;
    }

    private void updateIncomeItem(HashMap<String,Entries> currentItems,String month,double amount ){
        if (currentItems.containsKey(month)) {
            Entries entries = currentItems.get(month);
            entries.setIncome(entries.getIncome() + amount);
            currentItems.put(month, entries);
        } else {
            Entries entries = new Entries();
            entries.setIncome(amount);
            entries.setSpent(0.0);
            currentItems.put(month, entries);
        }
    }
    private void updateSpentItem(HashMap<String,Entries> currentItems,String month,double amount ){
        if (currentItems.containsKey(month)) {
            Entries entries = currentItems.get(month);
            entries.setSpent(entries.getSpent() + amount);
            currentItems.put(month, entries);
        } else {
            Entries entries = new Entries();
            entries.setSpent(amount);
            entries.setIncome(0.0);
            currentItems.put(month, entries);
        }
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
