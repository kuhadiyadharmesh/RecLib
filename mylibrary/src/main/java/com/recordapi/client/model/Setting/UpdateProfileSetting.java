package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateProfileSetting
{
    //api_key=553a431e192d2553a431e1930f&
    // data[f_name]=first&
    // data[l_name]=last&
    // data[email]=test@test,com&
    // data[is_public]=1&
    // data[language]=en-us"


    // Optional
//    play_beep = 1 or 0  ,
//    max_length = 1 to 120  ,

    private String api_key , f_name , l_name , email , is_public , language, play_beep , max_length;

    public UpdateProfileSetting(String api_key, String f_name, String l_name, String email , String is_public, String language)
    {
        this.api_key = api_key;
        this.f_name = f_name ;
        this.l_name = l_name;
        this.email = email;
        this.is_public = is_public;
        this.language = language;
    }
    public UpdateProfileSetting(String api_key, String f_name, String l_name, String email , String is_public, String language,String play_beep, String max_length)
    {
        this.api_key = api_key;
        this.f_name = f_name ;
        this.l_name = l_name;
        this.email = email;
        this.is_public = is_public;
        this.language = language;
        this.play_beep = play_beep;
        this.max_length = max_length;
    }

    public String getApi_key()
    {
        return this.api_key;
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
}
