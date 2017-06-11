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

    private static void updateIncomeItem(HashMap<String, Entries> currentItems, String month, double amount){
        if (currentItems.containsKey(month)) {
            Entries entries = currentItems.get(month);
            double current = CurrencyUtility.parseCurrency(entries.getIncome()) + amount;
            entries.setIncome(CurrencyUtility.mapToCurrency(current));
            currentItems.put(month, entries);
        } else {
            Entries entries = new Entries();
            entries.setIncome(CurrencyUtility.mapToCurrency(amount));
            entries.setSpending(CurrencyUtility.mapToCurrency(0.0));
            currentItems.put(month, entries);
        }
    }

    private static void updateSpentItem(HashMap<String, Entries> currentItems, String month, double amount){
        if (currentItems.containsKey(month)) {
            Entries entries = currentItems.get(month);
            Double current =  CurrencyUtility.parseCurrency(entries.getSpending()) + amount;
            entries.setSpending(CurrencyUtility.mapToCurrency(current));
            currentItems.put(month, entries);
        } else {
            Entries entries = new Entries();
            entries.setSpending(CurrencyUtility.mapToCurrency(amount));
            entries.setIncome(CurrencyUtility.mapToCurrency(0.0));
            currentItems.put(month, entries);
        }
    }


    private static Entries GetAverageEntry(double spent,
                                           double income,
                                           int incomeCount,
                                           int spentCount){
        Entries entry = new Entries();
        if(spentCount == 0){
            entry.setSpending(CurrencyUtility.mapToCurrency(0.0));
        }else{
            entry.setSpending(CurrencyUtility.mapToCurrency(spent/spentCount));
        }
        if (incomeCount == 0) {
            entry.setIncome(CurrencyUtility.mapToCurrency(0.0));
        }else {
            entry.setIncome(CurrencyUtility.mapToCurrency(income / incomeCount));
        }
        return entry;
    }


}
