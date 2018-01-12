package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateProfilePicture
{
    private String api_key , file ;

    public UpdateProfilePicture(String api_key, String file )
    {
        this.api_key = api_key ;
        this.file = file;
    }

    public String getApi_key()
    {
        return this.api_key;
    }
    public String getFile()
    {
        return this.file;
    }
}
