package com.transactions.TestReportCalculator;

import com.transactions.common.ReportCalculator;
import com.transactions.model.Entries;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WithOnlySpending {
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

    private  List<Transaction>  bothTransactions(){
        List<Transaction> anyTransactions  = new ArrayList<>();
        Transaction spendingOne = new Transaction();
        spendingOne.setAmount(100.00);
        spendingOne.setCategorization("any");
        spendingOne.setRawMerchant("any");
        spendingOne.setMonth("2015-11-2");
        Transaction spendingTwo = new Transaction();
        spendingTwo.setAmount(300.00);
        spendingTwo.setCategorization("any");
        spendingTwo.setRawMerchant("any");
        spendingTwo.setMonth("2015-11-2");
        anyTransactions.add(spendingOne);
        anyTransactions.add(spendingTwo);
        return anyTransactions;
    }
}
