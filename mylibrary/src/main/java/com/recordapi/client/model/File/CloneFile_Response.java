package com.recordapi.client.model.File;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class CloneFile_Response
{
    //{"status":"ok","msg":"Successfully saved"}
    private boolean status = false ;
    private String msg = "Service not Responding";
    private String id = "";

    public CloneFile_Response(String msg)
    {
        this.msg = msg;
    }
    public CloneFile_Response( boolean status,String msg,String id )
    {
        this.msg= msg;
        this.status = status;
        this.id = id ;
    }
    public String getMsg()
    {
        return  this.msg;
    }
    public String getId()
    {
        return  this.id;
    }
    public boolean getStatus()
    {
        return this.status;
    }
}
