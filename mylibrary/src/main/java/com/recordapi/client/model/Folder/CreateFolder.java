package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class CreateFolder
{
   private String api_key,name , pass;

    public void setApi_key(String api_key)
    {
        this.api_key = api_key;
    }
    public void setName(String name )
    {
        this.name = name ;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getName()
    {
        return  this.name;
    }
    public String getPass()
    {
        return  this.pass;
    }


}
