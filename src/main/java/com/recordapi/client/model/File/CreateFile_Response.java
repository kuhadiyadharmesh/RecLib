package com.recordapi.client.model.File;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class CreateFile_Response
{
    //{"status":"ok","msg":”File Uploaded Successfully"}
    private  boolean status = false ;
    private String msg = "server not responding";

    public CreateFile_Response(String msg)
    {
        this.msg = msg;
    }
    public CreateFile_Response(boolean status,String msg )
    {
        this.msg = msg ;
        this.status = status;
    }

    public boolean getStaus()
    {
        return status;
    }
    public String getMsg()
    {
        return  msg;
    }
}
