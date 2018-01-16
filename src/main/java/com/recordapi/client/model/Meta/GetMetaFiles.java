package com.recordapi.client.model.Meta;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetMetaFiles
{
    public String api_key ;

    public void GetMetaFiles(String api_key)
    {
        this.api_key = api_key;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
}
