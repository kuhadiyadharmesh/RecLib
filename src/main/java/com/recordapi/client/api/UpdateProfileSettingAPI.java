package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;
import com.recordapi.client.model.Setting.UpdateProfileSetting;
import com.recordapi.client.model.Setting.UpdateProfileSetting_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateProfileSettingAPI
{
    private UpdateProfileSetting data ;
    private RecordingApi recordingApi;

    public UpdateProfileSettingAPI(UpdateProfileSetting data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public UpdateProfileSetting_Response UpdateProfileSettingCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //Validation
        if(data.getApi_key().equals(""))
            return new UpdateProfileSetting_Response("Please set ApiKey");
        if(data.getF_name().equals(""))
            return new UpdateProfileSetting_Response("Please set Fname");
        if(data.getL_name().equals(""))
            return new UpdateProfileSetting_Response("Please set Lname");
        if(data.getEmail().equals(""))
            return new UpdateProfileSetting_Response("Please set Email");
            if(data.getIs_public().equals(""))
            {}
            else
            {
                if(0 == Integer.parseInt(data.getIs_public().toString()) | Integer.parseInt(data.getIs_public().toString()) == 1)
                {
                    param.add(new BasicNameValuePair("data[is_public]",data.getMax_length()));
                }
                else
                {
                    return new UpdateProfileSetting_Response("Please set IsPublic is 0 or 1");
                }
            }
        if(data.getLanguage().equals(""))
            return new UpdateProfileSetting_Response("Please set Language");
        if(data.getMax_length().equals(""))
        {}
        else
        {
            if(0 < Integer.parseInt(data.getMax_length().toString()) && Integer.parseInt(data.getMax_length().toString()) <= 120)
            {
                param.add(new BasicNameValuePair("data[max_length]",data.getMax_length()));
            }
            else
            {
                return new UpdateProfileSetting_Response("Please set Max Length between 1 to 120");
            }
        }
        if(data.getPlay_beep().equals(""))
        {}
        else
        {
            if(0 == Integer.parseInt(data.getPlay_beep().toString()) | Integer.parseInt(data.getPlay_beep().toString()) == 1)
            {
                param.add(new BasicNameValuePair("data[play_beep]",data.getMax_length()));
            }
            else
            {
                return new UpdateProfileSetting_Response("Please set Play Beep is 0 or 1");
            }
        }
            //return new UpdateProfileSetting_Response("Please set Fname");

        // Set parameter
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("data[f_name]",data.getF_name()));
        param.add(new BasicNameValuePair("data[l_name]",data.getL_name()));
        param.add(new BasicNameValuePair("data[language]",data.getLanguage()));
        param.add(new BasicNameValuePair("data[email]",data.getEmail()));

        //param.add(new BasicNameValuePair("data",data.getData()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_profile","POST",param);
        UpdateProfileSetting_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new UpdateProfileSetting_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new UpdateProfileSetting_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new UpdateProfileSetting_Response(jobj.getString("msg"));
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
