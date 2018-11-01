package com.recordapi.client.model;

import com.recordapi.client.model.Common.Settings;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class BuyCredit_Response
{

    private boolean status = false ;
    //"code":"amount_added","credits_added":300,"credits":8550,"credits_trans":300,"rem_expiry":null
    private String  msg ,  credits_added,credits,credits_trans,rem_expire ;

    public BuyCredit_Response(String msg)
    {
        this.msg = msg ;
    }

    public BuyCredit_Response(String msg,String credits_added,String credits,String credits_trans,String rem_expire)
    {
        this.msg = msg ;
        this.status = true;
        this.credits_added = credits_added;
        this.credits = credits ;
        this.credits_trans = credits_trans;
        this.rem_expire = rem_expire;
    }

    public String getMsg()
    {
        return  this.msg ;
    }


    public boolean getStatus()
    {
        return this.status;
    }

    public String getCredits()
    {
        return this.credits;
    }
    public String getCredits_added()
    {
        return this.credits_added;
    }
    public String getCredits_trans()
    {
        return this.credits_trans;
    }
    public String getRemExpire()
    {
        return this.rem_expire;
    }

}
