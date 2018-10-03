package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class DeleteFolder_Response
{
    //{"status":"ok","msg":"Deleted Successfully"}

    private boolean status = false ;
    private String msg = "not response from api";

    public DeleteFolder_Response(String msg)
    {
        this.msg = msg;
        this.status = false ;
    }

    public DeleteFolder_Response(boolean status ,String msg )
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
