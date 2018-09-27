package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class UpdateFolder
{
    //"api_key=557872b508520557872b50855c&id=32&move_to=31"

    private String api_key,folder_id,name,pass;

    public UpdateFolder(String api_key,String id , String name,String pass)
    {
        this.api_key = api_key;
        this.folder_id = id ;
        this.name = name;
        this.pass = pass;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getFolder_id()
    {
        return  this.folder_id;
    }
    public String getName()
    {
        return this.name;
    }
    public String getPass()
    {
        return this.pass;
    }
}
