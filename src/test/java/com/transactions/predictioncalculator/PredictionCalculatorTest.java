package com.transactions.predictioncalculator;


import com.transactions.common.PredictionCalculator;
import com.transactions.common.ReportCalculator;
import com.transactions.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class PredictionCalculatorTest {
    PredictionCalculator predictionCalculator = new PredictionCalculator();
    ReportCalculator reportCalculator = new ReportCalculator();
    PredictedReport report;
    int month;
    int year;
    int  id = 1110590645;

    @Before
    public void setup() {
        Report transactionReport = reportCalculator.calculate(transactions());
        Report predictedReport = reportCalculator.calculate(predictTransactions());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        report = predictionCalculator.predict(transactionReport,predictedReport,month,year,id);

    }

    @Test
    public void testPredictedSpending() {
        Entries average = report.getAverage();
        Assert.assertNotNull(average);
        Assert.assertEquals("$233.33", average.getSpending());
    }

    @Test
    public void testPredictedIncome() {
        Entries average = report.getAverage();
        Assert.assertNotNull(average);
        Assert.assertEquals("$340.00", average.getIncome());
    }

    @Test
    public void testMonth() {
        Assert.assertEquals(month, report.getMonth());
    }
    @Test
    public void testYear() {
        Assert.assertEquals(year, report.getYear());
    }
    @Test
    public void testId() {
        Assert.assertEquals(id, report.getId());
    }

    private List<Transaction> transactions() {
        List<Transaction> anyTransactions = new ArrayList<>();
        Transaction spendingOne = new Transaction();
        spendingOne.setAmount(-100.00);
        spendingOne.setCategorization("any");
        spendingOne.setRawMerchant("any");
        spendingOne.setMonth("2015-11");
        spendingOne.setTransactionAction(TransactionAction.ADDED);
        Transaction spendingTwo = new Transaction();
        spendingTwo.setAmount(-300.00);
        spendingTwo.setCategorization("any");
        spendingTwo.setRawMerchant("any");
        spendingTwo.setMonth("2015-11");
        spendingTwo.setTransactionAction(TransactionAction.ADDED);
        Transaction ignoredSpending = new Transaction();
        ignoredSpending.setAmount(-300.00);
        ignoredSpending.setCategorization("any");
        ignoredSpending.setRawMerchant("any");
        ignoredSpending.setMonth("2015-11");
        ignoredSpending.setTransactionAction(TransactionAction.IGNORED);
        Transaction ignoredSpendingTwo = new Transaction();
        ignoredSpendingTwo.setAmount(-3000.00);
        ignoredSpendingTwo.setCategorization("any");
        ignoredSpendingTwo.setRawMerchant("any");
        ignoredSpendingTwo.setMonth("2015-11");
        ignoredSpendingTwo.setTransactionAction(TransactionAction.IGNORED);
        anyTransactions.add(spendingOne);
        anyTransactions.add(spendingTwo);
        anyTransactions.add(ignoredSpending);
        anyTransactions.add(ignoredSpendingTwo);
        return anyTransactions;
    }
    private List<Transaction> predictTransactions() {
        List<Transaction> anyTransactions = new ArrayList<>();
        Transaction spendingOne = new Transaction();
        spendingOne.setAmount(-100.00);
        spendingOne.setCategorization("any");
        spendingOne.setRawMerchant("any");
        spendingOne.setMonth("2016-06");
        spendingOne.setTransactionAction(TransactionAction.ADDED);
        Transaction spendingTwo = new Transaction();
        spendingTwo.setAmount(-300.00);
        spendingTwo.setCategorization("any");
        spendingTwo.setRawMerchant("any");
        spendingTwo.setMonth("2015-06");
        spendingTwo.setTransactionAction(TransactionAction.ADDED);
        Transaction income = new Transaction();
        income.setAmount(1000.00);
        income.setCategorization("paycheck");
        income.setRawMerchant("any");
        income.setMonth("2016-06");
        income.setTransactionAction(TransactionAction.ADDED);
        Transaction secondIncome = new Transaction();
        secondIncome.setAmount(10.00);
        secondIncome.setCategorization("paycheck");
        secondIncome.setRawMerchant("any");
        secondIncome.setMonth("2015-06");
        secondIncome.setTransactionAction(TransactionAction.ADDED);
        anyTransactions.add(spendingTwo);
        anyTransactions.add(secondIncome);
        anyTransactions.add(income);
        anyTransactions.add(secondIncome);

        anyTransactions.add(spendingOne);
        anyTransactions.add(spendingTwo);

        return anyTransactions;
    }
}