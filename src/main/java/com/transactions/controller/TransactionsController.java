package com.transactions.controller;

import com.transactions.common.TransactionClient;
import com.transactions.model.PredictedReport;
import com.transactions.model.UserReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.transactions.service.TransactionsService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
	private TransactionsService transactionsService;



    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    UserReport all(@RequestParam(value= "ignore", defaultValue= "",required = false) final String ignore)  {
        return transactionsService.getAllTransactions(1110590645,ignore);
	}
    @RequestMapping(value = "/predictRestOfTheMonth", method = RequestMethod.GET)
    public PredictedReport predictRestOfMonth() {

        return transactionsService.predictRestOfTheMonth(1110590645);

    }

}


