package com.recordapi.client.model.File;

import com.recordapi.client.model.Common.FileData;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateFile
{
    //api_key=557872b508520557872b50855c&

    /*

    curl -X POST https://app2.virtualbrix.net/rapi/update_file/ -d \ "api_key=557872b508520557872b50855c&id=11&
    f_name=first&
    l_name=lasttest&
    notes=test_notes
    &email=test@gmail.com&
    phone=%2B18104763057&
    tags=tagone&
    meta[duration]=00:20&
    meta[note]=metanote&
    meta[url]=google&
    folder_id=0&
    name=first reording&
    remind_days=10&
    remind_date=10-jan-2017 23:23:52
     */


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
