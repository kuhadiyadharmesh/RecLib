package com.recordapi.client.model.Meta;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class CreateMetaFile
{
    private String api_key,file , parent_id;
    private String name , notes , remind_days,remind_date;

    public CreateMetaFile(String api_key,String file , String name,String parent_id)
    {
        this.api_key = api_key;
        this.file = file;
        this.name = name ;
        this.parent_id = parent_id;
//        if(name.equals("") | notes.equals(""))
//            data = "";
//        else
//            data="{\"name\":\""+name+"\",\"notes\":\""+notes+"\"}";
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getFile()
    {
        return this.file;
    }
    public String getParent_id()
    {
        return this.parent_id;
    }
    public String getName()
    {
        return this.name;
    }
//    public String getData()
//    {
//        return this.data;
//    }
}
