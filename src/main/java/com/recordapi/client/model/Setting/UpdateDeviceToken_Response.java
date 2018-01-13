package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class UpdateDeviceToken_Response
{
    private boolean status = false ;
    private String  msg ;

    public UpdateDeviceToken_Response(String msg)
    {
        this.msg = msg ;
    }

    public UpdateDeviceToken_Response( boolean status,String msg)
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
