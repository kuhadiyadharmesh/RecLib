package com.recordapi.client.model.File;

import com.recordapi.client.model.Common.FileData;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateFile
{
    //api_key=557872b508520557872b50855c&
    private String api_key ;
    private FileData fileInfo = null;

    public UpdateFile(String api_key,FileData fileInfo)
    {
        this.api_key = api_key;
        this.fileInfo = fileInfo;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public FileData getFileInfo()
    {
        return  this.fileInfo;
    }
}
