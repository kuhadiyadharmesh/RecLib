package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/15/2018.
 */

public class NotifyUser_Response
{
    private boolean status = false ;
    private String  msg ;
//{"multicast_id":5097861416520352894,"success":1,"failure":0,"canonical_ids":0,"results":[{"message_id":"0:1516274019224428%1bd80b6ff9fd7ecd"}]}

    public NotifyUser_Response(String msg)
    {
        this.msg = msg ;
    }

    public NotifyUser_Response( boolean status,String msg)
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
