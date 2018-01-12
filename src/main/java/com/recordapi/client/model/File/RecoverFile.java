package com.recordapi.client.model.File;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class RecoverFile
{
    //"api_key=553a431e192d2553a431e1930f&id=4&folder_id=34"

    private String api_key , file_id , folder_id ;

    public RecoverFile(String api_key, String file_id , String folder_id)
    {
        this.api_key = api_key;
        this.file_id = file_id;
        this.folder_id = folder_id;

    }

    public String getApi_key()
    {
        return this.api_key;
    }
    public String getFile_id()
    {
        return this.file_id;
    }
    public String getFolder_id()
    {
        return this.folder_id;
    }


}
