package com.transactions.common;


import com.transactions.model.Transaction;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class TransactionClient {



    public String getTransactions(int userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";
        ResponseEntity<String> response = restTemplate.postForEntity(url, getTransactionsRequest(userId), String.class);
        return response.getBody();
    }
    public String getProjectedTransactionsForMonth(int userId,int year,int month){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://2016.api.levelmoney.com/api/v2/core/projected-transactions-for-month";
        ResponseEntity<String> response = restTemplate.postForEntity(url,
                getProjectedTransactionsForMonthRequest(userId,year,month), String.class);
        System.out.print(response);
        return response.getBody();
    }

    private HttpEntity<?> getTransactionsRequest(int userId){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");
        headers.setAll(map);
        Map req_payload = new HashMap();
        req_payload.put("uid", userId);
        req_payload.put("token", "E4EA0E53CC28E6B22A359E971E87C742");
        req_payload.put("api-token", "AppTokenForInterview");
        req_payload.put("json-strict-mode", false);
        req_payload.put("json-verbose-response", false);

        Map req_payload_two = new HashMap();
        req_payload_two.put("args",req_payload);
        HttpEntity<?> request = new HttpEntity<>(req_payload_two, headers);
        return request;
    }
    private HttpEntity<?> getProjectedTransactionsForMonthRequest(int userId,int year, int month){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");
        headers.setAll(map);
        Map req_payload = new HashMap();
        req_payload.put("uid", userId);
        req_payload.put("token", "FB3AE6B3B3152E8992B2690EC3ECCFC2");
        req_payload.put("api-token", "AppTokenForInterview");
        req_payload.put("json-strict-mode", false);
        req_payload.put("json-verbose-response", false);
        Map req_payload_two = new HashMap();
        req_payload_two.put("args",req_payload);
        req_payload_two.put("year",year);
        req_payload_two.put("month",month);
        HttpEntity<?> request = new HttpEntity<>(req_payload_two, headers);
        return request;
    }


}
