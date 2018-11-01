package com.recordapi.client.model.File;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class CreateFile_Response
{
    //{"status":"ok","msg":”File Uploaded Successfully"}
    private  boolean status = false ;
    private String msg = "server not responding", id;


    public CreateFile_Response(String msg)
    {
        this.msg = msg;
    }
    public CreateFile_Response(String msg , String id )
    {
        this.msg = msg ;
        this.status = true;
        this.id = id;
    }

    public boolean getStaus()
    {
        return status;
    }
    public String getMsg()
    {
        return  msg;
    }
    public String getId()
    {
        return this.id;
    }
}
