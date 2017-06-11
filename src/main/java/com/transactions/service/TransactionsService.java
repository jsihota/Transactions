package com.transactions.service;

import com.transactions.model.PredictedReport;
import com.transactions.model.UserReport;

public interface TransactionsService {
    UserReport getAllTransactions(int userId,String ignore);
    PredictedReport predictRestOfTheMonth(int userId, int month, int year);

}
