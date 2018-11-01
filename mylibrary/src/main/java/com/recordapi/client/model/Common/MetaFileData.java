package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class MetaFileData
{
    //":[{"id":"1","name":"0",
    // "file":"https:\/\/app2.virtualbrix.net\/records\/player\/file\/2\/meta_file\/5638b19c5a57f_1446556060_27579644904.png",
    // "parent_id":"19",
    // "user_id":"2",
    // "time":"2015-11-03 08:07:40"

    private String id , name , file , parent_id , user_id , time;// ,msg = "Server not responding" ;
   // private Boolean status = false ;

//    public MetaFileData(String msg)
//    {
//        this.msg = msg;
//        this.status = false ;
//    }

    public MetaFileData(String id , String name , String file , String parent_id , String user_id, String time)
    {
        //this.status = true ;
        //this.msg = msg;
        this.id = id ;
        this.name = name ;
        this.file = file;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.time = time;
    }

    public String getId()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public String getFile()
    {
        return this.file;
    }
//    public String getMsg()
//    {
//        return this.msg;
//    }
    public String getParent_id()
    {
        return this.parent_id;
    }
    public String getUser_id()
    {
        return this.user_id;
    }
    public String getTime()
    {
        return this.time;
    }
//    public boolean getStatus()
//    {}
}
