package com.transactions.service;

import java.util.List;

import com.transactions.common.ReportCalculator;
import com.transactions.common.TransactionClient;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.UserReport;
import org.springframework.stereotype.Service;

@Service("TransactionsService")
public class TransactionsServiceImpl implements TransactionsService {

    private TransactionClient transactionClient;
    private ReportCalculator reportCalculator;

	public TransactionsServiceImpl(TransactionClient transactionClient, ReportCalculator reportCalculator)
    {
        this.transactionClient = transactionClient;
        this.reportCalculator = reportCalculator;
    }
    public UserReport getAllTransactions(int userId){
        List<Transaction> transactions = transactionClient.getTransactions(userId);
        Report report = reportCalculator.calculate(transactions);

        UserReport userReport = new UserReport();
        userReport.setId(userId);
        userReport.setReport(report);

        return userReport;
    }



}
