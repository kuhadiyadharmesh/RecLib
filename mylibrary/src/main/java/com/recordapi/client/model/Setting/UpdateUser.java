package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class UpdateUser
{
    private String   app;

    public UpdateUser( String app)
    {
        //this.api_key = api_key;
        this.app = app ;
    }

//    public String getApi_key()
//    {
//        return this.api_key;
//    }
    public String getApp()
    {
        return this.app;
    }
}
