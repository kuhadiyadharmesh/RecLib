package com.recordapi.client.model.Setting;

import com.recordapi.client.model.Common.Language;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetLanguageList_Response
{
//{"status":"ok","languages":[{"code":"en_US","name":"EnglishUS"},{"code":"de","name":"German"}]}

    private  String msg = "server not responding";
    private boolean status = false ;
    private ArrayList<Language> languageArrayList;

    public GetLanguageList_Response(String msg)
    {
        this.msg = msg;
        this.status = false ;
    }
    public GetLanguageList_Response(String msg , ArrayList<Language> languageArrayList)
    {
        this.status = true ;
        this.languageArrayList = languageArrayList;
        this.msg = msg ;
    }

    public String getMsg()
    {
        return  this.msg;
    }
    public boolean getStatus()
    {
        return this.status;
    }
    public ArrayList<Language> getLanguageArrayList()
    {
        return  this.languageArrayList;
    }


}
