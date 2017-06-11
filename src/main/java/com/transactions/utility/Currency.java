package com.transactions.utility;


import java.text.NumberFormat;

public class Currency {
    public static double parseCurrency(String amount) {
        amount = amount.replaceAll("[^\\d.]+", "");
        return  Double.parseDouble(amount);
    }
    public static String mapToCurrency(double amount){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amount);
    }
}
