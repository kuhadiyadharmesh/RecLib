package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class UpdateDeviceToken
{
    //“api_key=553a431e192d2553a431e1930f&device_token=123”

    private String api_key , device_token,device_type;

    public UpdateDeviceToken(String api_key,String device_token, String device_type)
    {
        this.api_key = api_key;
        this.device_token = device_token;
        this.device_type = device_type;
    }

    public String getApi_key()
    {
        return this.api_key;
    }
    public String getDevice_token()
    {
        return this.device_token;
    }
    public String getDevice_type()
    {
        return this.device_type;
    }
}
