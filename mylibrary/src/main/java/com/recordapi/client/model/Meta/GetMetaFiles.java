package com.recordapi.client.model.Meta;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetMetaFiles
{
    public String  parent_id ;

    public  GetMetaFiles(String parent_id)
    {

        this.parent_id = parent_id;
    }

//    public String getApi_key()
//    {
//        return  this.api_key;
//    }

    public String getParent_id()
    {
        return this.parent_id;
    }
}
