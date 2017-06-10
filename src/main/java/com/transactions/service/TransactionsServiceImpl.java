package com.transactions.service;

import java.util.List;

import com.transactions.common.ReportCalculator;
import com.transactions.common.TransactionClient;
import com.transactions.common.TransactionMapper;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.UserReport;
import org.springframework.stereotype.Service;

@Service("TransactionsService")
public class TransactionsServiceImpl implements TransactionsService {

    private TransactionClient transactionClient;
    private ReportCalculator reportCalculator;
    private TransactionMapper transactionMapper;

	public TransactionsServiceImpl(TransactionClient transactionClient,
                                   ReportCalculator reportCalculator,
                                   TransactionMapper transactionMapper)
    {
        this.transactionClient = transactionClient;
        this.reportCalculator = reportCalculator;
        this.transactionMapper = transactionMapper;
    }
    public UserReport getAllTransactions(int userId){
        String response =  transactionClient.getTransactions(userId);
        List<Transaction> transactions = transactionMapper.map(response);
        Report report = reportCalculator.calculate(transactions);

        UserReport userReport = new UserReport();
        userReport.setId(userId);
        userReport.setReport(report);

        return userReport;
    }



}
