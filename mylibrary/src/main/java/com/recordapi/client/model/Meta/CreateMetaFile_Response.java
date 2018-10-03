package com.recordapi.client.model.Meta;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class CreateMetaFile_Response
{
    //{"status":"ok","msg":”File Uploaded Successfully"}
    private  boolean status = false ;
    private String msg = "server not responding", id;


    public CreateMetaFile_Response(String msg)
    {
        this.msg = msg;
    }
    public CreateMetaFile_Response(String msg , String id )
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
