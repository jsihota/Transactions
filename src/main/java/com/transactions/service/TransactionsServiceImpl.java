package com.transactions.service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;

import com.transactions.common.TransactionClient;
import com.transactions.model.Transaction;
import com.transactions.model.UserTransaction;
import org.springframework.stereotype.Service;

@Service("TransactionsService")
public class TransactionsServiceImpl implements TransactionsService {

    private TransactionClient transactionClient;

	public TransactionsServiceImpl(TransactionClient transactionClient)
    {
        this.transactionClient = transactionClient;
    }
    public UserTransaction getAllTransactions(int userId){
        List<Transaction> transactions = transactionClient.getTransactions(userId);
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setAverage(33333.44);
        userTransaction.setId(111111);
        userTransaction.setTransactions(transactions);

        return userTransaction;
    }



}
