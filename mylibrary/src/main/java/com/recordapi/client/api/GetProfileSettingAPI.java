package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.Setting.GetProfileSetting;
import com.recordapi.client.model.Setting.GetProfileSetting_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetProfileSettingAPI
{
    private GetProfileSetting data ;
    private RecordingApi recordingApi;

    public GetProfileSettingAPI(GetProfileSetting data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public GetProfileSetting_Response GetProfileSettingCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new GetProfileSetting_Response("Please set ApiKey");
//        if(data.getFile().equals(""))
//            return new CreateFile_Response("Please select file");
//        if(data.getData().equals(""))
//            return new CreateFile_Response("Please Enter folder name and notes");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
       // param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        //param.add(new BasicNameValuePair("data",data.getData()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_profile","POST",param);
        GetProfileSetting_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new GetProfileSetting_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    //profile
                    JSONObject injob = jobj.getJSONObject("profile");

                    response_data = new GetProfileSetting_Response(injob.getString("pic"),injob.getString("f_name"),injob.getString("l_name"),injob.getString("email"),injob.getString("is_public"),injob.getString("language"),injob.getString("play_beep"),injob.getString("max_length"),injob.getString("time_zone"),jobj.getString("app"),jobj.getString("credits"),jobj.getString("credits_trans"));
                    return response_data;
                }
                else
                {
                    response_data = new GetProfileSetting_Response(jobj.getString("msg"));
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
