package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class UpdateFolder_Response
{
    //{"status":"ok","msg":"Update Successfully"}

    private boolean status = false ;
    private String msg = "not response from api";

    public UpdateFolder_Response(String msg)
    {
        this.msg = msg;
        this.status = false ;
    }

    public UpdateFolder_Response(boolean status ,String msg )
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
