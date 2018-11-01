package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetPhoneNumber
{
    //"api_key=553a431e192d2553a431e1930f
    private  String api_key;// , language;

    public GetPhoneNumber(String api_key)
    {
        this.api_key = api_key;
        //this.language = language;
    }
    public String getApi_key()
    {
        return this.api_key;
    }
//    public String getLanguage()
//    {
//        return this.language;
//    }
}
