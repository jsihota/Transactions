package com.transactions.service;

import com.transactions.model.UserTransaction;

public interface TransactionsService {
    UserTransaction getAllTransactions(int userId);

}
