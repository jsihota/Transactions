package com.transactions.transactionmapper;

import com.transactions.common.TransactionMapper;
import com.transactions.model.Transaction;
import com.transactions.model.TransactionAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TransactionMapperWithIgnoreTest {
    TransactionMapper transactionMapper;
    Transaction firstTransaction;
    Transaction secondTransaction;

    @Before
    public void setup() {
        transactionMapper = new TransactionMapper();
        String response = "{\"transactions\": [{\"amount\":16911700,\"is-pending\":false,\"payee-name-only-for-testing\":" +
                "\"ZENPAYROLL\",\"aggregation-time\":1496985257003,\"account-id\":" +
                "\"nonce:comfy-checking/hdhehe\",\"clear-date\":1497119640000,\"memo-only-for-testing\":" +
                "\"Example Memo\",\"transaction-id\":\"1497119640000\",\"raw-merchant\":" +
                "\"ZENPAYROLL\",\"categorization\":\"Paycheck\",\"merchant\":\"Zenpayroll\"," +
                "\"transaction-time\":\"2017-06-09T00:00:00.000Z\"},{\"amount\":-120500,\"" +
                "is-pending\":false,\"payee-name-only-for-testing\":\"Krispy Kreme Donuts\"," +
                "\"aggregation-time\":1497093389385,\"account-id\":\"nonce:comfy-cc/hdhehe\",\"" +
                "clear-date\":1497454920000,\"memo-only-for-testing\":\"Example Memo\",\"transaction-id" +
                "\":\"1497454920000\",\"raw-merchant\":\"Krispy Kreme Donuts\",\"categorization\":" +
                "\"Restaurants\",\"merchant\":\"Krispy Kreme Donuts\",\"transaction-time\":\"" +
                "2017-06-10T00:00:00.000Z\"}]}";
        List<Transaction> map = transactionMapper.map(response, "Donuts");
        Assert.assertNotNull(map);
        firstTransaction = map.get(0);
        secondTransaction = map.get(1);
    }

    @Test
    public void monthsTest() {
        Assert.assertEquals(firstTransaction.getMonth(),"2017-06");
        Assert.assertEquals(secondTransaction.getMonth(),"2017-06");
    }
    @Test
    public void transactionTypeTest() {
        Assert.assertEquals(firstTransaction.getTransactionAction(), TransactionAction.ADDED);
        Assert.assertEquals(secondTransaction.getTransactionAction(),TransactionAction.IGNORED);
    }
    @Test
    public void rawmerchantTypeTest() {
        Assert.assertEquals(firstTransaction.getRawMerchant(), "zenpayroll");
        Assert.assertEquals(secondTransaction.getRawMerchant(),"krispy kreme donuts");
    }
    @Test
    public void amountTest() {
        Assert.assertEquals(firstTransaction.getAmount(), 1691.17,0.0);
        Assert.assertEquals(secondTransaction.getAmount(),-12.05,0.0);
    }
    @Test
    public void categorizationTest() {
        Assert.assertEquals(firstTransaction.getCategorization(), "paycheck");
        Assert.assertEquals(secondTransaction.getCategorization(),"restaurants");
    }
}
