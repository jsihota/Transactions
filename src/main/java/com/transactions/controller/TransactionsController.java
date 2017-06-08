package com.transactions.controller;

import com.transactions.model.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.transactions.service.TransactionsService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
	private TransactionsService wordService;



    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public UserTransaction allTransactions() {
        return new UserTransaction();

	}
    @RequestMapping(value = "/projectTransactionsForMonth", method = RequestMethod.GET)
    public List<String> projectedTransactionsForMonth() {
        return new ArrayList<>();

    }

}


