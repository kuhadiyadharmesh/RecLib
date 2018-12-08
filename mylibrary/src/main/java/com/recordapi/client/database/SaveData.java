package com.recordapi.client.database;

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData {
    private SharedPreferences pref;
    private SharedPreferences.Editor edit;

    public SaveData(Context c) {
        pref = c.getSharedPreferences("recordingfile", Context.MODE_PRIVATE);
        edit = pref.edit();
    }


    public void setToken(String token) {
        edit.putString("token", token).commit();
    }

    public String getToken() {
        return pref.getString("token", "");
    }

    public void setPhone(String phone) {
        edit.putString("phone", phone).commit();
    }

    public String getPhone() {
        return pref.getString("phone", "");
    }

    public void setSetting_JSON(String json) {
        edit.putString("setting_response", json).commit();
    }

    public String getSetting_JSON() {
        return pref.getString("setting_response", "");
    }

    public void setGetMessage_JSON(String json) {
        edit.putString("getmessage", json).commit();
    }

    public String getGetMessage_JSON() {
        return pref.getString("getmessage", "");
    }

    public void setLanguageList_JOSN(String json) {
        edit.putString("setlanguage", json).commit();
    }

    public String getLanguageList_JSON() {
        return pref.getString("setlanguage", "");
    }

    public void setTranslation_JSON(String json) {
        edit.putString("transalation", json).commit();
    }

    public String getTranslation_JSON() {
        return pref.getString("transalation", "");
    }

    public void setProfile_JSON(String json) {
        edit.putString("profile", json).commit();
    }

    public String getProfile_JSON() {
        return pref.getString("profile", "");
    }

    public void setPhoneNumbers_JSON(String json) {
        edit.putString("phonenumbers", json).commit();
    }

    public String getPhoneNumbers_JSON() {
        return pref.getString("phonenumbers", "");
    }

    public void setFolders_JSON(String json) {
        edit.putString("folders", json).commit();
    }

    public String getFolders_JSON() {
        return pref.getString("folders", "");
    }

    public void setFoldersFiles_JSON(String json, String folder_id) {
        edit.putString("" + folder_id, json).commit();
    }

    public String getFolderFiles_JSON(String folder_id) {
        return pref.getString(folder_id, "");
    }

    public void setOfflineFileCreate(String offline_json) {
        edit.putString("offline_created_files", offline_json).commit();
    }

    public String getOfflineFileCreated() {
        return pref.getString("offline_created_files", "");
    }
}
