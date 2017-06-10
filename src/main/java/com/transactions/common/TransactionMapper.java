package com.transactions.common;

import com.transactions.model.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {
    public List<Transaction> map(String responseBody){
        List<Transaction> transactions = new ArrayList<>();
        JSONObject json = new JSONObject(responseBody);
        JSONArray transaction = json.getJSONArray("transactions");

        for(int i = 0; i < transaction.length(); i++)
        {
            Transaction temptTransaction = new Transaction();
            JSONObject objects = transaction.getJSONObject(i);
            temptTransaction.setAmount(objects.getDouble("amount"));
            temptTransaction.setMonth(objects.getString("transaction-time"));
            temptTransaction.setCategorization(objects.getString("categorization"));
            temptTransaction.setRawMerchant(objects.getString("raw-merchant"));
            transactions.add(temptTransaction);
        }

        return transactions;
    }
}
