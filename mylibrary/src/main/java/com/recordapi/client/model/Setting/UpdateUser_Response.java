package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class UpdateUser_Response
{
    private boolean status = false ;
    private String  msg ;

    public UpdateUser_Response(String msg)
    {
        this.msg = msg ;
    }

    public UpdateUser_Response( boolean status,String msg)
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
