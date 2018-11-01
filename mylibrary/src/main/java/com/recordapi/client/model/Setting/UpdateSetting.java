package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateSetting
{
    //api_key=557872b508520557872b50855c&play_beep=yes&files_permission=public”

    private String api_key , play_beep , file_permission ;

    public UpdateSetting(String api_key , String play_beep, String file_permission)
    {
        this.api_key = api_key;
        this.play_beep = play_beep;
        this.file_permission = file_permission;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getPlay_beep()
    {
        return this.play_beep;
    }
    public String getFile_permission()
    {
        return this.file_permission;
    }
}
