package com.recordapi.client.model;

/**
 * Created by Dharmesh-PC on 1/8/2018.
 */

public class RegisterPhone_Response
{
    //{"status":"error","msg":"invalid token"}
    //
    boolean status = false ;
    String msg ="no response from server";
    String phone ;
    String body;

    //{"status":"ok","phone":"+16463742122","code":"54004","msg":"Verification Code Sent"}

    public RegisterPhone_Response()
    {

    }

    public RegisterPhone_Response(String msg)
    {
        status = false ;
        this.msg = msg;
    }

    public boolean getStatus()
    {
        return status;
    }
    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void setMsg(String msg)
    {
        this.msg = msg ;
    }

    public String getMsg()
    {
        return  this.msg;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

}
