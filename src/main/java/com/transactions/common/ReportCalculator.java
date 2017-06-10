package com.transactions.common;
import com.transactions.model.Entries;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.TransactionAction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
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
                    amount = (-1) * amount;
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

    private Report getReport(HashMap<String, Entries> lineItems, HashMap<String, Entries> ignoredLineItems, double spent
            , double income, int incomeCount, int spentCount) {
        Report report = new Report();
        report.setLineItem(lineItems);
        report.setIgnoredLineItem(ignoredLineItems);
        report.setAverage(GetAverageEntry(spent,income,incomeCount,spentCount));
        return report;
    }

    private void updateIncomeItem(HashMap<String,Entries> currentItems,String month,double amount ){
        if (currentItems.containsKey(month)) {
            Entries entries = currentItems.get(month);
            double current = parseCurrency(entries.getIncome()) + amount;
            entries.setIncome(mapToCurrency(current));
            currentItems.put(month, entries);
        } else {
            Entries entries = new Entries();
            entries.setIncome(mapToCurrency(amount));
            entries.setSpent(mapToCurrency(0.0));
            currentItems.put(month, entries);
        }
    }
    private static double parseCurrency(String amount) {

        amount = amount.replaceAll("[^\\d.]+", "");
        return  Double.parseDouble(amount);
    }
    private void updateSpentItem(HashMap<String,Entries> currentItems,String month,double amount ){
        if (currentItems.containsKey(month)) {
            Entries entries = currentItems.get(month);
            Double current =  parseCurrency(entries.getSpent()) + amount;
            entries.setSpent(mapToCurrency(current));
            currentItems.put(month, entries);
        } else {
            Entries entries = new Entries();
            entries.setSpent(mapToCurrency(amount));
            entries.setIncome(mapToCurrency(0.0));
            currentItems.put(month, entries);
        }
    }
    private String mapToCurrency(double amount){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amount);
    }

    private Entries GetAverageEntry(double spent,
                    double income,
                    int incomeCount,
                    int spentCount){
        Entries entry = new Entries();
        if(spentCount == 0){
            entry.setSpent(mapToCurrency(0.0));
        }else{
            entry.setSpent(mapToCurrency(spent/spentCount));
        }
        if (incomeCount == 0) {
            entry.setIncome(mapToCurrency(0.0));
        }else {
            entry.setIncome(mapToCurrency(income / incomeCount));
        }
        return entry;
    }


}
