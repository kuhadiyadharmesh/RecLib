package com.recordapi.client.model;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class BuyCredit
{
    //“api_key=553a431e192d2553a431e1930f&amount=2&reciept=123”
   // For android product_id=123&device_type=android
    private String api_key ,amount , reciept,product_id ,device_type;

    public BuyCredit(String api_key, String amount , String reciept)
    {
        this.api_key = api_key;
        this.amount = amount ;
        this.reciept = reciept;
    }

    public void setForAndroid(String product_id)
    {
        this.device_type = "android";
        this.product_id = product_id;
    }

    public String getApi_key()
    {
        return this.api_key = api_key;
    }
    public String getAmount()
    {
        return  this.amount;
    }
    public String getReciept()
    {
        return  this.reciept;
    }
    public String getProduct_id()
    {
        return this.product_id;
    }
    public String getDevice_type()
    {
        return this.device_type;
    }

}
