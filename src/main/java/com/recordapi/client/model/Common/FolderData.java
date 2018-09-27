package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class FolderData
{
    //{"id":"29","name":"test","created":"1433989312"}
    String id , name , created ;

    public FolderData(String id , String name , String created)
    {
        this.id = id ;
        this.name= name;
        this.created = created;
        //this.password = password;
    }

    public String getId()
    {
        return this.id;
    }
    public String getName()
    {
        return  this.name;
    }
    public String getCreated()
    {
        return  this.created;
    }


}
