package com.recordapi.client.model;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class VerifyPhone_Response
{
    //{"status":"ok",
    // "phone":"+16463742122",
    // "api_key":"557872b508520557872b50855c",
    // "msg":"Phone Verified"}
    String phone , api_key ,msg = "no response from server " ;
    boolean status = false ;
    public VerifyPhone_Response(String msg)
    {
        this.msg = msg;
    }

    public VerifyPhone_Response(boolean status , String phone , String api_key , String msg)
    {
        this.status = status;
        this.phone = phone;
        this.api_key = api_key;
        this.msg = msg ;

    }

    public String getPhone()
    {
        return  this.phone;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getMsg()
    {
        return  this.msg;
    }

    public boolean getStatus()
    {
        return  this.status;
    }

}
