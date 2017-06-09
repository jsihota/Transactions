package com.transactions.service;

import com.transactions.model.UserReport;

public interface TransactionsService {
    UserReport getAllTransactions(int userId);

}
