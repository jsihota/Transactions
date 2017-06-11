package com.transactions.common;

import com.transactions.model.Entries;
import com.transactions.model.PredictedReport;
import com.transactions.model.Report;
import com.transactions.utility.Currency;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PredictionCalculator {
    public PredictedReport predict(Report report, Report predictedReport,int month,int year,int userId){
        HashMap<String, Entries> lineItem = report.getLineItem();
        int count = 0;
        double income = 0.0;
        double spending = 0.0;
        for(String key : lineItem.keySet()){
            int reportMonth = Integer.parseInt(key.split("-")[1]);
            if(reportMonth == month){
                count++;
                Entries entries = lineItem.get(key);
                income =  income + Currency.parseCurrency(entries.getIncome());
                spending = spending +  Currency.parseCurrency(entries.getSpending());
            }
        }
        double averageIncome = 0.0;
        double averageSpending = 0.0;
        if(count != 0){
            averageIncome = income/count;
            averageSpending = spending/count;
        }
        else{
             averageIncome = income;
             averageSpending = spending;
        }

        double predictedIncome = Currency.parseCurrency(predictedReport.getAverage().getIncome());
        double predictedSpending = Currency.parseCurrency(predictedReport.getAverage().getSpending());
        PredictedReport resultReport = new PredictedReport();
        resultReport.setMonth(month);
        if(predictedIncome > averageIncome) {
            averageIncome = averageIncome + predictedIncome;
        }
        if(predictedSpending > averageSpending){
            averageSpending = predictedSpending + averageSpending;
        }
        Entries entries = new Entries();
        entries.setIncome(Currency.mapToCurrency(averageIncome));
        entries.setSpending(Currency.mapToCurrency(averageSpending));
        resultReport.setAverage(entries);
        resultReport.setId(userId);
        resultReport.setYear(year);

        return resultReport;
    }


}
