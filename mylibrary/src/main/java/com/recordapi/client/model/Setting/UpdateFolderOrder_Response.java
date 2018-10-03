package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/15/2018.
 */

public class UpdateFolderOrder_Response
{

    //{"status":"ok","msg":"Successfully Updated"}

    private boolean status = false ;
    private String  msg ;

    public UpdateFolderOrder_Response(String msg)
    {
        this.msg = msg ;
    }

    public UpdateFolderOrder_Response( boolean status,String msg)
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
