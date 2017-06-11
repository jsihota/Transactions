package com.transactions.common;

import com.transactions.model.Entries;
import com.transactions.model.PredictedReport;
import com.transactions.model.Report;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PredictionCalculator {
    public PredictedReport predict(Report report, Report predictedReport,int month){
        HashMap<String, Entries> lineItem = report.getLineItem();
        int count = 0;
        double income = 0.0;
        double spending = 0.0;
        for(String key : lineItem.keySet()){
            int reportMonth = Integer.parseInt(key.split("-")[1]);
            if(reportMonth == month){
                count++;
                Entries entries = lineItem.get(key);
                income =  income + CurrencyUtility.parseCurrency(entries.getIncome());
                spending = spending +  CurrencyUtility.parseCurrency(entries.getSpending());
            }
        }
        double averageIncome = income/count;
        double averageSpending = spending/count;
        double predictedIncome = CurrencyUtility.parseCurrency(predictedReport.getAverage().getIncome());
        double predictedSpending = CurrencyUtility.parseCurrency(predictedReport.getAverage().getIncome());
        PredictedReport resultReport = new PredictedReport();
        resultReport.setMonth(month);

        if(predictedIncome > averageIncome) {
            averageIncome = averageIncome + predictedIncome;
        }
        if(predictedSpending > averageSpending){
            averageSpending = predictedSpending + averageSpending;
        }
        Entries entries = new Entries();
        entries.setIncome(CurrencyUtility.mapToCurrency(averageIncome));
        entries.setSpending(CurrencyUtility.mapToCurrency(averageSpending));
        resultReport.setAverage(entries);

        return resultReport;
    }


}
