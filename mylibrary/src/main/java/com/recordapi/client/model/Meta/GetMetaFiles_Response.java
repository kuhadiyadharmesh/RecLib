package com.recordapi.client.model.Meta;

import com.recordapi.client.model.Common.MetaFileData;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetMetaFiles_Response
{
    //":[{"id":"1","name":"0",
    // "file":"https:\/\/app2.virtualbrix.net\/records\/player\/file\/2\/meta_file\/5638b19c5a57f_1446556060_27579644904.png",
    // "parent_id":"19",
    // "user_id":"2",
    // "time":"2015-11-03 08:07:40"

    private ArrayList<MetaFileData> filelist ;
    private boolean status = false ;
    private String msg = "Server not responding";

    public GetMetaFiles_Response(String msg )
    {
        this.status = false ;
        this.msg = msg;
    }
    public GetMetaFiles_Response(String msg , ArrayList<MetaFileData> filelist)
    {
        this.msg = msg;
        this.status = true ;
        this.filelist = filelist;
    }
    public ArrayList<MetaFileData> getFilelist()
    {
        return this.filelist;
    }
    public String getMsg()
    {
        return this.msg;
    }
    public boolean getStatus()
    {
        return this.status;
    }

}
