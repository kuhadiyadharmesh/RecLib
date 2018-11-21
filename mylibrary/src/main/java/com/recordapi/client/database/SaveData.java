package com.recordapi.client.database;

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData
{
    private SharedPreferences pref;
    private SharedPreferences.Editor edit ;

    public SaveData(Context c)
    {
        pref = c.getSharedPreferences("recordingfile",Context.MODE_PRIVATE);
        edit = pref.edit();
    }


    public void setToken(String token)
    {
        edit.putString("token",token).commit();
    }
    public String getToken()
    {
        return pref.getString("token","");
    }

}
