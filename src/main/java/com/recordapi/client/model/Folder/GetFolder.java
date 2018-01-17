package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class GetFolder
{
    //api_key=553a431e192d2553a431e1930f
    public String api_key ;

    public  GetFolder(String api_key)
    {
        this.api_key = api_key;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }

}
