package com.recordapi.client.model;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class StartUnStartFolderFile_Response
{
    //{"status":"ok","msg":"Successfully Updated","code":"star_updated"}
    private boolean status = false ;
    private String msg = "not response from api";

    public StartUnStartFolderFile_Response(String msg)
    {
        this.msg = msg;
        this.status = false ;
    }

    public StartUnStartFolderFile_Response(boolean status ,String msg )
    {
        this.msg = msg;
        this.status = status;
    }

    public boolean getStatus()
    {
        return this.status;
    }
    public String getMsg()
    {
        return  this.msg;
    }
}
