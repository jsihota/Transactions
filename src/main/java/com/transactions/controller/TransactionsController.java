package com.transactions.controller;

import com.transactions.model.UserReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.transactions.service.TransactionsService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
	private TransactionsService transactionsService;



    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public @ResponseBody
    UserReport allTransactions(@RequestParam(value= "ignore", defaultValue= "",required = false) final String ignore)  {
        System.out.println(ignore);
        return transactionsService.getAllTransactions(1110590645,ignore);
	}
    @RequestMapping(value = "/projectTransactionsForMonth", method = RequestMethod.GET)
    public List<String> projectedTransactionsForMonth() {
        return new ArrayList<>();

    }

}


