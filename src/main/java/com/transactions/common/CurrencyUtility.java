package com.transactions.common;


import java.text.NumberFormat;

public class CurrencyUtility {
    public static double parseCurrency(String amount) {
        amount = amount.replaceAll("[^\\d.]+", "");
        return  Double.parseDouble(amount);
    }
    public static String mapToCurrency(double amount){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amount);
    }
}
