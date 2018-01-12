package com.recordapi.client.model.Setting;

import com.recordapi.client.ApiClient;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateProfilePicure_Response
{
    //{"status":"ok","msg":"Profile Picture Updated",”file”:”message.jpg”,"code":"profile_pic_updated"}

    //{"status":"ok","msg":"Successfully saved"}
    private boolean status = false ;
    private String msg = "Service not Responding" , file ;

    public UpdateProfilePicure_Response(String msg)
    {
        this.msg = msg;
    }
    public UpdateProfilePicure_Response(String msg,String file  )
    {
        this.msg= msg;
        this.file = file;
        this.status = true ;
    }
    public String getMsg()
    {
        return  this.msg;
    }
    public boolean getStatus()
    {
        return this.status;
    }
    public String getFile()
    {
        return this.file;
    }
}
