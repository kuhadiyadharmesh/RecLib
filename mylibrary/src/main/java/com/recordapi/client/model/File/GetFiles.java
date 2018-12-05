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


    private  String page,folder_id,source,pass,search_text;
    private boolean reminder = false;



    public GetFiles(String folder_id,boolean reminder , String source)
    {
        this.folder_id = folder_id;
        this.reminder = reminder;
        this.source = source;
    }
    public GetFiles(String page , String folder_id, String source, String pass, boolean reminder,String search_text)
    {
            //this.api_key = api_key;
        this.page = page;
        this.folder_id = folder_id;
        this.source = source;
        this.pass = pass;
        this.reminder = reminder;
        this.search_text = search_text;
    }



   /* public String getApi_key()
    {
        return  this.api_key;
    }*/
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

    public String getSearch_text(){return this.search_text;}


}
