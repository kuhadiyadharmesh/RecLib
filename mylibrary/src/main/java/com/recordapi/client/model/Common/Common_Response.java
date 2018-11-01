package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 5/8/2018.
 */

public class Common_Response
{
    //{"status":"ok","msg":"Successfully Updated"}

    private boolean status = false ;
    private String  msg ;

    public Common_Response(String msg)
    {
        this.msg = msg ;
    }

    public Common_Response( boolean status,String msg)
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
