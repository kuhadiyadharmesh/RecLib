package com.recordapi.client.database;

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData extends Activity
{
    private SharedPreferences pref;
    private SharedPreferences.Editor edit ;

    public SaveData()
    {
        pref = getApplication().getSharedPreferences("recordingfile",Context.MODE_PRIVATE);
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
