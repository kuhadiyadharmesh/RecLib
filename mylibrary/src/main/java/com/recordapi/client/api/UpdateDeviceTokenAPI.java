package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;
import com.recordapi.client.model.Setting.UpdateUser;
import com.recordapi.client.model.Setting.UpdateUser_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class UpdateDeviceTokenAPI
{
    private UpdateDeviceToken data ;
    private RecordingApi recordingApi;
    public UpdateDeviceTokenAPI(UpdateDeviceToken data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }
    public UpdateDeviceToken_Response UpdateDeviceTokenCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new UpdateDeviceToken_Response("Please set ApiKey");
        if(data.getDevice_token().equals(""))
            return new UpdateDeviceToken_Response("Please set Device Token");
        if(data.getDevice_type().equals(""))
            return new UpdateDeviceToken_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("device_token",data.getDevice_token()));
        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_device_toke","POST",param);
        UpdateDeviceToken_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new UpdateDeviceToken_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new UpdateDeviceToken_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new UpdateDeviceToken_Response(jobj.getString("msg"));
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
