package com.transactions.utility;


import org.junit.Assert;
import org.junit.Test;

public class CurrencyTest {

    @Test
    public void doubleToCurrencyTest() {
        String expected = "$1,010.00";
        Assert.assertEquals(expected,Currency.mapToCurrency(1010.00));
    }
    @Test
    public void currencyToDobuleTest() {
        double expected = 1010.00;
        Assert.assertEquals(expected, Currency.parseCurrency("$1,010.00"),0.0);

    }

}
