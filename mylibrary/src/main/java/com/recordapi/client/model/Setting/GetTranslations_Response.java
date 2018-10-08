package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetTranslations_Response
{
    //{"status":"ok","translation":{"Trash":"Trash","All Files":"All Files"}}
    private boolean status = false ;
    private String  msg ,trash, allfiles,extraData;

    public GetTranslations_Response(String msg)
    {
        this.msg = msg ;
    }

    public GetTranslations_Response(String msg,String trash , String  allfile,String extraData)
    {
        this.msg = msg ;
//        this.status = status;
        this.trash= trash;
        this.allfiles = allfile;
        this.extraData = extraData;
        this.status = true ;
    }

    public String getMsg()
    {
        return  this.msg ;
    }


    public boolean getStatus()
    {
        return this.status;
    }

    public String getExtraData()
    {
        return this.extraData;
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
