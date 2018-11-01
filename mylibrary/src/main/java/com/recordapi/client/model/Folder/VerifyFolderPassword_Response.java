package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class VerifyFolderPassword_Response
{
    //{"status":"ok","msg":'Password is Correct',"code": 'valid_folder_pass'}
    private boolean status = false ;
    private String msg = "not response from api";

    public VerifyFolderPassword_Response(String msg)
    {
        this.msg = msg;
        this.status = false ;
    }

    public VerifyFolderPassword_Response(boolean status ,String msg )
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
