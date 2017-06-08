package com.transactions.service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;

import com.transactions.model.UserTransaction;
import org.springframework.stereotype.Service;

@Service("wordService")
public class TransactionsServiceImpl implements TransactionsService {



	public TransactionsServiceImpl()
    {

    }
    public UserTransaction getAllTransactions(int userId){
        return new UserTransaction();
    }



}
