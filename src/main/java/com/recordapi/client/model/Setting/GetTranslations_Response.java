package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetTranslations_Response
{
    //{"status":"ok","translation":{"Trash":"Trash","All Files":"All Files"}}
    private boolean status = false ;
    private String  msg ,trash, allfiles;

    public GetTranslations_Response(String msg)
    {
        this.msg = msg ;
    }

    public GetTranslations_Response(String msg,String trash ,String  allfile)
    {
        this.msg = msg ;
//        this.status = status;
        this.trash= trash;
        this.allfiles = allfile;
    }

    public String getMsg()
    {
        return  this.msg ;
    }


    public boolean getStatus()
    {
        return this.status;
    }

    public String getTrash()
    {
        return this.trash;
    }
    public String getAllfiles()
    {
        return this.allfiles;
    }
}
