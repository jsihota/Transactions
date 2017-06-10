package com.transactions.reportcalculator;

import com.transactions.common.ReportCalculator;
import com.transactions.model.Entries;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.TransactionAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WithOnlySpendingTest {
    ReportCalculator reportCalculator = new ReportCalculator();
    Report report;

    @Before
    public void setup() {
        System.out.println("@Before - runBeforeTestMethod");
        report = reportCalculator.calculate(bothTransactions());
    }
    @Test
    public void testAverageTest() {
        Entries average = report.getAverage();
        Assert.assertNotNull(average);
        Assert.assertEquals(0.0,average.getIncome(),0.0);
        Assert.assertEquals(200.0,average.getSpent(),0.0);
    }
    @Test
    public void testLineItemsTest() {
        HashMap<String, Entries> lineItems = report.getLineItem();
        Assert.assertNotNull(lineItems);
        Entries entries = lineItems.get("2015-11");
        Assert.assertNotNull(entries);
        Assert.assertEquals(400.0,entries.getSpent(),0.0);
        Assert.assertEquals(0.0,entries.getIncome(),0.0);
    }
    @Test
    public void testIgnoredItemsTest() {
        HashMap<String, Entries> ignoredLineItems = report.getIgnoredLineItem();
        Assert.assertNotNull(ignoredLineItems);
        Entries entries = ignoredLineItems.get("2015-11");
        Assert.assertNotNull(entries);
        Assert.assertEquals(3300.0,entries.getSpent(),0.0);
        Assert.assertEquals(0.0,entries.getIncome(),0.0);
    }

    private  List<Transaction>  bothTransactions(){
        List<Transaction> anyTransactions  = new ArrayList<>();
        Transaction spendingOne = new Transaction();
        spendingOne.setAmount(100.00);
        spendingOne.setCategorization("any");
        spendingOne.setRawMerchant("any");
        spendingOne.setMonth("2015-11");
        spendingOne.setTransactionAction(TransactionAction.ADDED);
        Transaction spendingTwo = new Transaction();
        spendingTwo.setAmount(300.00);
        spendingTwo.setCategorization("any");
        spendingTwo.setRawMerchant("any");
        spendingTwo.setMonth("2015-11");
        spendingTwo.setTransactionAction(TransactionAction.ADDED);
        Transaction ignoredSpending = new Transaction();
        ignoredSpending.setAmount(300.00);
        ignoredSpending.setCategorization("any");
        ignoredSpending.setRawMerchant("any");
        ignoredSpending.setMonth("2015-11");
        ignoredSpending.setTransactionAction(TransactionAction.IGNORED);
        Transaction ignoredSpendingTwo = new Transaction();
        ignoredSpendingTwo.setAmount(3000.00);
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
}
