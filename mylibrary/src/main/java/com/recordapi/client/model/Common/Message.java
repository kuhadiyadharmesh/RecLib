package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class Message
{
    //{"id":"1","title":"First Title","body":"New Call Package available Now","time":"2015-07-29 07:38:52"}

    private String id , title , body , time ;

    public Message(String id , String title , String body, String time)
    {
        this.id = id ;
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public String getId()
    {
        return  this.id;
    }
    public String getTitle()
    {
        return  this.title;
    }
    public String getBody()
    {
        return this.body;
    }
    public String getTime()
    {
        return this.time;
    }


}
