package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 1/15/2018.
 */

public class Folders {
    private String position, id;

    public Folders(String id, String position)
    {
        this.id = id ;
        this.position = position;
    }

    public  String getPosition()
    {
        return this.position;
    }
    public String getId()
    {
        return this.id;
    }




}

