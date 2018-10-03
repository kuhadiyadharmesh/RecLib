package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetProfileSetting_Response
{

    private String pic , f_name , l_name , email , is_public , language, play_beep , max_length,time_zone,app,credits,credits_trans , msg;
    private boolean status = false  ;
//    public GetProfileSetting_Response(String api_key, String f_name, String l_name, String email , String is_public, String language)
//    {
//        this.api_key = api_key;
//        this.f_name = f_name ;
//        this.l_name = l_name;
//        this.email = email;
//        this.is_public = is_public;
//        this.language = language;
//    }

    public GetProfileSetting_Response(String msg)
    {
        this.msg = msg;
        this.status = false ;
    }
    public GetProfileSetting_Response(String pic, String f_name, String l_name, String email , String is_public, String language,String play_beep, String max_length,String time_zone, String app , String credits,String credits_trans)
    {
        this.status = true ;
        this.pic = pic;
        this.f_name = f_name ;
        this.l_name = l_name;
        this.email = email;
        this.is_public = is_public;
        this.language = language;
        this.play_beep = play_beep;
        this.max_length = max_length;
        this.app = app ;
        this.time_zone = time_zone;
        this.credits = credits ;
        this.credits_trans = credits_trans;
        this.msg = "Profile Get successfully";
    }

    public String getPic()
    {
        return this.pic;
    }
    public String getF_name()
    {
        return this.f_name;
    }
    public String getL_name()
    {
        return     this.l_name;
    }
    public String getEmail()
    {
        return  this.email;
    }
    public String getIs_public()
    {
        return this.is_public;
    }
    public String getLanguage()
    {
        return this.language;
    }
    public String getPlay_beep()
    {
        return this.play_beep;
    }
    public String getMax_length()
    {
        return this.max_length;
    }

    public String getTime_zone()
    {
        return this.time_zone;
    }
    public String getApp()
    {
        return this.app;
    }
    public String getCredits()
    {
        return this.credits;
    }
    public String getCredits_trans()
    {
        return this.credits_trans;
    }

    public boolean getStatus()
    {
        return this.status;
    }
    public String getMsg()
    {
        return this.msg;
    }
}
