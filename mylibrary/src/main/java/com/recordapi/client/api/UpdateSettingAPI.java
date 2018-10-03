package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.RecoverFile;
import com.recordapi.client.model.File.RecoverFile_Response;
import com.recordapi.client.model.Setting.UpdateSetting;
import com.recordapi.client.model.Setting.UpdateSetting_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateSettingAPI
{
    private UpdateSetting data ;
    private RecordingApi recordingApi;

    public UpdateSettingAPI(UpdateSetting data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public UpdateSetting_Response UpdateSettingCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new UpdateSetting_Response("Please set ApiKey");
        if(data.getPlay_beep().equals(""))
            return new UpdateSetting_Response("Please set PlayBeep");
        if(data.getFile_permission().equals(""))
            return new UpdateSetting_Response("Please set FilePermission ");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("play_beep",data.getPlay_beep()));
        param.add(new BasicNameValuePair("file_permission",data.getFile_permission()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_settings","POST",param);
        UpdateSetting_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new UpdateSetting_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new UpdateSetting_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new UpdateSetting_Response(jobj.getString("msg"));
                    return  response_data;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }
        return  response_data;

    }
}
