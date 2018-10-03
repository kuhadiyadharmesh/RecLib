package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class GetSetting
{
    private String api_key ;

    public GetSetting(String api_key)
    {
        this.api_key = api_key;
    }

    public String getApi_key()
    {
        return this.api_key;
    }
}
