package com.transactions.service;

import java.util.List;

import com.transactions.common.PredictionCalculator;
import com.transactions.common.ReportCalculator;
import com.transactions.common.TransactionClient;
import com.transactions.common.TransactionMapper;
import com.transactions.model.PredictedReport;
import com.transactions.model.Report;
import com.transactions.model.Transaction;
import com.transactions.model.UserReport;
import org.springframework.stereotype.Service;

@Service("TransactionsService")
public class TransactionsServiceImpl implements TransactionsService {

    private TransactionClient transactionClient;
    private ReportCalculator reportCalculator;
    private TransactionMapper transactionMapper;
    private PredictionCalculator predictionCalculator;

	public TransactionsServiceImpl(TransactionClient transactionClient,
                                   ReportCalculator reportCalculator,
                                   TransactionMapper transactionMapper,
                                   PredictionCalculator predictionCalculator)
    {
        this.transactionClient = transactionClient;
        this.reportCalculator = reportCalculator;
        this.transactionMapper = transactionMapper;
        this.predictionCalculator = predictionCalculator;
    }
    public UserReport getAllTransactions(int userId,String ignore){
        String response =  transactionClient.getTransactions(userId);
        List<Transaction> transactions = transactionMapper.map(response,ignore);
        Report report = reportCalculator.calculate(transactions);
        UserReport userReport = new UserReport();
        userReport.setId(userId);
        userReport.setReport(report);
        return userReport;
    }
    public PredictedReport predictRestOfTheMonth(int userId,int month,int year){
        String response =  transactionClient.getTransactions(userId);
        List<Transaction> transactions = transactionMapper.map(response,"");
        Report report = reportCalculator.calculate(transactions);
        response =  transactionClient.getProjectedTransactionsForMonth(userId,month,year);
        List<Transaction> predictedTransactions = transactionMapper.map(response,"");
        Report predictedReport = reportCalculator.calculate(predictedTransactions);
        PredictedReport predict = predictionCalculator.predict(report, predictedReport, month);
        predict.setYear(year);
        predict.setId(userId);
        return predict;



    }



}
