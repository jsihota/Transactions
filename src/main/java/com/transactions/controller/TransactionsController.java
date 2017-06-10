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



    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    UserReport all(@RequestParam(value= "ignore", defaultValue= "",required = false) final String ignore)  {
        return transactionsService.getAllTransactions(1110590645,ignore);
	}
    @RequestMapping(value = "/projectForMonth", method = RequestMethod.GET)
    public List<String> projectedForMonth() {
        return new ArrayList<>();

    }

}


