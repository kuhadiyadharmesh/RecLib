package com.recordapi.client.model.File;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateFile_Response
{
    private boolean status = false ;
    private String msg = "Service not Responding";

    public UpdateFile_Response(String msg)
    {
        this.msg = msg;
    }
    public UpdateFile_Response( boolean status,String msg )
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
