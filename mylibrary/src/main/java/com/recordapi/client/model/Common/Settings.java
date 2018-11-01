package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class Settings
{
    private String play_beep, file_permission;


    public Settings(String play_beep , String file_permission)
    {
        this.play_beep = play_beep;
        this.file_permission = file_permission;
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
