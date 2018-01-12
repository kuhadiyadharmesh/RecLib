package com.recordapi.client.model.Setting;

import com.recordapi.client.model.Common.Settings;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class GetSetting_Response
{
    //{"status":"ok","settings":{"play_beep":"no","files_permission":"private"},”app”:”free”,"credits":11}
    private Settings settings;
    private boolean status = false ;
    private String app, credit, msg ;

    public GetSetting_Response(String app , String credit, String msg,Settings settings)
    {
        this.app = app ;
        this.credit = credit ;
        this.msg = msg ;
        this.settings = settings;
        this.status = true ;
    }
    public GetSetting_Response(String msg)
    {
        this.msg = msg ;
    }

    public String getMsg()
    {
        return  this.msg ;
    }
    public String getCredit()
    {
        return  this.credit;
    }
    public String getApp()
    {
        return this.app;
    }

    public boolean getStatus()
    {
        return this.status;
    }
    public Settings getSettings()
    {
        return this.settings;
    }
}
