package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Setting.GetTranslations;
import com.recordapi.client.model.Setting.GetTranslations_Response;
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetTranslationsAPI
{
    private GetTranslations data ;
    private RecordingApi recordingApi;
    public GetTranslationsAPI(GetTranslations data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }
    public GetTranslations_Response GetTranslationsCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new GetTranslations_Response("Please set ApiKey");
        if(data.getLanguage().equals(""))
            return new GetTranslations_Response("Please set Language en-us and en-uk");
//        if(data.getDevice_type().equals(""))
//            return new UpdateDeviceToken_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("language",data.getLanguage()));
//        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_translations","POST",param);
        GetTranslations_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new GetTranslations_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new GetTranslations_Response("Translations get successfully.",jobj.getJSONObject("translation").getString("Trash"),jobj.getJSONObject("translation").getString("All Files"));
                    return response_data;
                }
                else
                {
                    response_data = new GetTranslations_Response(jobj.getString("msg"));
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
