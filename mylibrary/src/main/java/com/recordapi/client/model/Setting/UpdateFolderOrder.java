package com.recordapi.client.model.Setting;

import com.recordapi.client.model.Common.Folders;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/15/2018.
 */

public class UpdateFolderOrder
{
//    "api_key=553a431e192d2553a431e1930f&folders[20]=1&folders[34]=2&folders[7]=3&folders[11]=4".

    private String api_key ;
    private ArrayList<Folders> folderdata ;

    public UpdateFolderOrder(String api_key,ArrayList<Folders> folderdata)
    {
        this.api_key = api_key;
        this.folderdata = folderdata;
    }

    public String getApi_key()
    {
        return this.api_key;
    }
    public ArrayList<Folders> getFolderdata()
    {
        return this.folderdata;
    }
}
