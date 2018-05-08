package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/15/2018.
 */

public class NotifyUser
{
    //"api_key=553a431e192d2553a431e1930f&title=test&body=test body"
//    For android device_type=android

    private String api_key ;
    private String title , body ;

    public NotifyUser(String api_key, String title , String body)
    {
        this.api_key = api_key;
        this.title = title;
        this.body = body;
    }
//
    public String getApi_key()
    {
        return this.api_key;
    }
    public String getTitle()
    {
        return  this.title;
    }
    public String getBody()
    {
        return this.body;
    }

}
