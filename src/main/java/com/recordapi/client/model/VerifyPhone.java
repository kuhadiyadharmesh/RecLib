package com.recordapi.client.model;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class VerifyPhone
{
    //phone=%2B16463742122&
    // code=54004&
    // mcc=300&
    // app=free&
    // token=55942ee3894f51000530894&
    // device_token=234&
    // device_type=android&
    // time_zone=10

    String phone , code , macc , app , token , device_token , device_type , time_zone;

    public VerifyPhone(String phone , String code , String macc , String app , String token , String device_token,String device_type,String time_zone)
    {
        this.phone = phone;
        this.code = code ;
        this.macc = macc;
        this.app = app;
        this.token = token;
        this.device_token = device_token;
        this.device_type = device_type;
        this.time_zone = time_zone;
    }

    public String getPhone()
    {
        return phone;
    }
    public String getCode()
    {
        return code;
    }
    public String getMacc()
    {
        return macc;
    }
    public String getApp()
    {
        return app;
    }
    public String getToken()
    {
        return token;
    }
    public String getDevice_token()
    {
        return device_token;
    }
    public String getDevice_type()
    {
        return device_type;
    }
    public String getTime_zone()
    {
        return time_zone;
    }

}
