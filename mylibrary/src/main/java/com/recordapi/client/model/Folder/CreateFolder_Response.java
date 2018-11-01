package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class CreateFolder_Response
{
    //{"status":"ok","id":31,"msg":"Created Successfully"}
    private boolean status = false ;
    private String id , msg ="No response from server";

    public CreateFolder_Response(String msg)
    {
        this.msg = msg ;
        status = false ;
    }

    public CreateFolder_Response(String id , String msg)
    {
        this.status = true ;
        this.id = id ;
        this.msg= msg;
    }

    public String getId()
    {
        return  this.id ;
    }
    public boolean getStatus()
    {
        return  this.status;
    }
    public String getMsg()
    {
        return  this.msg;
    }


}
