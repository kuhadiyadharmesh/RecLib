package com.recordapi.client.model.Meta;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetMetaFiles
{
    public String api_key , parent_id ;

    public  GetMetaFiles(String api_key,String parent_id)
    {
        this.api_key = api_key;
        this.parent_id = parent_id;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }

    public String getParent_id()
    {
        return this.parent_id;
    }
}
