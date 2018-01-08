package com.recordapi.client.model;

/**
 * Created by Dharmesh-PC on 1/5/2018.
 */

public class RegisterPhone
{
    String phone ,token ;

    public void setPhonenumber(String phone)
    {
        this.phone = phone;
    }

    public String getPhonenumber()
    {
        return  this.phone;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public void setToken()
    {
        this.token = "55942ee3894f51000530894";
    }

    public String getToken()
    {
        return  this.token;
    }


}
