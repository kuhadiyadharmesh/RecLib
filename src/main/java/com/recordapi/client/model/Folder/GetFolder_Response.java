package com.recordapi.client.model.Folder;

import com.recordapi.client.model.Common.FolderData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class GetFolder_Response
{
    private boolean status = false ;
    private String msg = "no response from server";
    private ArrayList<FolderData> folderdata = null;

    public GetFolder_Response(String msg)
    {
        this.msg = msg;
        folderdata = new ArrayList<>();
    }
    public GetFolder_Response(boolean status , String msg ,ArrayList<FolderData> folderdata)
    {
        this.msg = msg ;
        this.status = status;
        this.folderdata = folderdata ;
    }

    public String getMsg()
    {
        return  this.msg;
    }
    public boolean getStatus()
    {
        return  this.status;
    }
    public ArrayList<FolderData> getFolderdata()
    {
        return  this.folderdata;
    }

}
