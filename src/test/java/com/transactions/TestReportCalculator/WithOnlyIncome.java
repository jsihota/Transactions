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

public class WithOnlyIncome {
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
        Assert.assertEquals(505.00,average.getIncome(),0.0);
        Assert.assertEquals(0.00,average.getSpent(),0.0);
    }
    @Test
    public void testLineItemsTest() {
        HashMap<String, Entries> lineItems = report.getLineItem();
        Assert.assertNotNull(lineItems);
        Entries entries = lineItems.get("2015-11");
        Assert.assertNotNull(entries);
        Assert.assertEquals(0.0,entries.getSpent(),0.0);
        Assert.assertEquals(1010.0,entries.getIncome(),0.0);
    }

    private  List<Transaction>  bothTransactions(){
        List<Transaction> anyTransactions  = new ArrayList<>();
        Transaction income = new Transaction();
        income.setAmount(1000.00);
        income.setCategorization("paycheck");
        income.setRawMerchant("any");
        income.setMonth("2015-11-2");
        Transaction secondIncome = new Transaction();
        secondIncome.setAmount(10.00);
        secondIncome.setCategorization("paycheck");
        secondIncome.setRawMerchant("any");
        secondIncome.setMonth("2015-11-2");
        anyTransactions.add(income);
        anyTransactions.add(secondIncome);

        return anyTransactions;
    }
}
