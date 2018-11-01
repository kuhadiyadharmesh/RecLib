package com.recordapi.client.model.Meta;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class DeleteMetaFiles_Response
{
    private boolean status = false ;
    private String msg = "Service not Responding";

    public DeleteMetaFiles_Response(String msg)
    {
        this.msg = msg;
    }
    public DeleteMetaFiles_Response( boolean status,String msg )
    {
        this.msg= msg;
        this.status = status;
    }
    public String getMsg()
    {
        return  this.msg;
    }
    public boolean getStatus()
    {
        return this.status;
    }
}
