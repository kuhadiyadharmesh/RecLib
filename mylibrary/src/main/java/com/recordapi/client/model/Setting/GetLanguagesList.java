package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetLanguagesList
{
    private String api_key ;

    public GetLanguagesList(String api_key)
    {
        this.api_key = api_key;
    }

    public String getApi_key()
    {
        return this.api_key;
    }
}
