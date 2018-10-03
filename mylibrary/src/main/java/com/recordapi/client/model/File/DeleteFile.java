package com.recordapi.client.model.File;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class DeleteFile
{
    //api_key=557872b508520557872b50855c&ids=13,14

    //comma separated ids limit is 30
    private String api_key ,file_ids ;
    private boolean delete_permanent = false ;
//    private ?


    public DeleteFile(String api_key , String file_ids)
    {
        this.api_key = api_key;
        this.file_ids = file_ids;
    }
    public DeleteFile(String api_key , String file_ids,boolean delete_permanent)
    {
        this.api_key = api_key;
        this.file_ids = file_ids;
        this.delete_permanent = delete_permanent;
    }
    public String getApi_key()
    {
        return this.api_key;
    }
    public String getFile_ids()
    {
        return this.file_ids;
    }

    public boolean getDeletePermanent()
    {
        return this.delete_permanent;
    }


}
