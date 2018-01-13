package com.recordapi.client.model;

import com.recordapi.client.model.Common.Settings;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class BuyCredit_Response
{

    private boolean status = false ;
    private String  msg ;

    public BuyCredit_Response(String msg)
    {
        this.msg = msg ;
    }

    public BuyCredit_Response( boolean status,String msg)
    {
        this.msg = msg ;
        this.status = status;
    }

    public String getMsg()
    {
        return  this.msg ;
    }


    public boolean getStatus()
    {
        return this.status;
    }

}
