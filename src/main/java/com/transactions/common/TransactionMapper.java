package com.transactions.common;

import com.transactions.model.Transaction;
import com.transactions.model.TransactionAction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {
    public List<Transaction> map(String responseBody,String ignore){
        boolean ignoreFlag = false;
        if(!ignore.isEmpty()){
            ignoreFlag =true;
            ignore = ignore.toLowerCase();
        }
        List<Transaction> transactions = new ArrayList<>();
        try{
            JSONObject json = new JSONObject(responseBody);
            JSONArray transaction = json.getJSONArray("transactions");
            for(int i = 0; i < transaction.length(); i++) {
                JSONObject objects = transaction.getJSONObject(i);
                String categorization = objects.getString("categorization").toLowerCase();
                String rawMerchant = objects.getString("raw-merchant").toLowerCase();
                Transaction temptTransaction = new Transaction();
                temptTransaction.setAmount(mapCentocentsToDollars(objects.getDouble("amount")));
                temptTransaction.setMonth(getYearMonthOnly(objects.getString("transaction-time")));
                temptTransaction.setCategorization(categorization);
                temptTransaction.setRawMerchant(rawMerchant);
                temptTransaction.setTransactionAction(TransactionAction.ADDED);
                if(ignoreFlag && (categorization.contains(ignore) || rawMerchant.contains(ignore))){
                    //System.out.println("Ignored: " + categorization + " " + rawMerchant);
                    temptTransaction.setTransactionAction(TransactionAction.IGNORED);
                }
                transactions.add(temptTransaction);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return transactions;
    }
    private String getYearMonthOnly(String time){
        String yearMonth = null;
        try{
            String[] temp = time.split("-");
            yearMonth = temp[0] + "-" + temp[1];
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return yearMonth;
    }

    private double mapCentocentsToDollars(double amount){
        return amount/10000.00;
    }
}
