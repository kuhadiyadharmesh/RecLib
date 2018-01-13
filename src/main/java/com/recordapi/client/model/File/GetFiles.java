package com.recordapi.client.model.File;

import com.recordapi.client.model.Common.FileData;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class GetFiles
{
 //"api_key=557872b508520557872b50855c&page=0&folder_id=4"

//    folder_id=all  //get all files
//        folder_id=trash //get deleted files
//    source=all  // all or app2 or do not set source param
//        pass=1234  //required for private folders
//    reminder=true // for getting file reminders


    private  String api_key ,page,folder_id,source,pass;
    private boolean reminder = false;


    public GetFiles(String api_key)
    {
        this.api_key = api_key;
    }

    public void setPage(String page)
    {
        this.page = page;
    }
    public void setFolder_id(String folder_id)
    {
        this.folder_id = folder_id;
    }
    public void setSource(String source)
    {
        this.source = source;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public void setReminder(boolean reminder)
    {
        this.reminder = reminder;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getPage()
    {
        return  this.page;
    }
    public String getFolder_id()
    {
        return this.folder_id;
    }
    public String getSource()
    {
        return  this.source;
    }
    public boolean getReminder()
    {
        return this.reminder;
    }
    public String getPass()
    {
        return this.pass;
    }


}