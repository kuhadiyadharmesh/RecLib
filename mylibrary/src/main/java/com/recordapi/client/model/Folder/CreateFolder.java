package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class CreateFolder
{
   private String name , pass;


/*
    public void setName(String name )
    {
        this.name = name ;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }*/

    public CreateFolder(String name)
    {
        this.name = name ;
    }
    public CreateFolder(String name , String pass)
    {
        this.name = name ;
        this.pass = pass;
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
