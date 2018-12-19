package com.recordapi.client.database;

import android.content.Context;

import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.api.GetFilesAPI;
import com.recordapi.client.api.GetFolderAPI;
import com.recordapi.client.api.GetLanguageListAPI;
import com.recordapi.client.api.GetMessageAPI;
import com.recordapi.client.api.GetPhoneNumberAPI;
import com.recordapi.client.api.GetProfileSettingAPI;
import com.recordapi.client.api.GetSettingAPI;
import com.recordapi.client.api.GetTranslationsAPI;
import com.recordapi.client.model.Common.FolderData;
import com.recordapi.client.model.File.GetFiles;
import com.recordapi.client.model.Folder.GetFolder_Response;

import java.util.ArrayList;

public class Records_Data
        {
    private Context c;
    private SaveData sd ;
    public Records_Data(Context c)
    {
        this.c = c;
        sd = new SaveData(c);
    }

    public void Call_All_Service()
    {
        Call_Setting_Service();
    }

    public void Call_Setting_Service()
    {
        new GetSettingAPI(c, new RecordingApiListener()
        {
            @Override
            public void onSuccess(Object obj) {
                Call_Messages_Service();
            }

            @Override
            public void onFailure(Object obj)
            {

            }
        });
    }

    public void Call_Messages_Service()
    {
        new GetMessageAPI(c, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                Call_Language_Service();
            }

            @Override
            public void onFailure(Object obj) {

            }
        });
    }
    public void Call_Language_Service()
    {
        new GetLanguageListAPI(c, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                Call_Transalation_Service();
            }

            @Override
            public void onFailure(Object obj) {

            }
        });
    }
    public void Call_Transalation_Service()
    {


        new GetTranslationsAPI(c, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                Call_Profile_Service();
            }

            @Override
            public void onFailure(Object obj) {

            }
        });
    }

    public void Call_Profile_Service()
    {
        new GetProfileSettingAPI(c, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                Call_GetPhone_Service();
            }

            @Override
            public void onFailure(Object obj) {

            }
        });
    }

    public void Call_GetPhone_Service()
    {
        new GetPhoneNumberAPI(c, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                Call_GetFolder_Service();
            }

            @Override
            public void onFailure(Object obj) {

            }
        });
    }

    public void Call_GetFolder_Service()
    {
        new GetFolderAPI(c, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj)
            {
                GetFolder_Response data = (GetFolder_Response)obj;
                ArrayList<FolderData> folderdata = data.getFolderdata();
                Call_GetFiles_Service("all");
                for (int i = 0; i < folderdata.size(); i++)
                {
                    Call_GetFiles_Service(folderdata.get(i).getId());
                }

            }

            @Override
            public void onFailure(Object obj) {

            }
        });
    }

    public void Call_GetFiles_Service(String folder_id )
    {
        GetFiles getfile = new GetFiles(folder_id, true , "all");

        new GetFilesAPI(c, getfile, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {

                //return true;
            }

            @Override
            public void onFailure(Object obj)
            {
                            }
        });

    }


        }
