package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.NumberData;
import com.recordapi.client.model.Setting.GetPhoneNumber;
import com.recordapi.client.model.Setting.GetPhoneNumber_Response;
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetPhoneNumberAPI
{
    private GetPhoneNumber data ;
    private RecordingApi recordingApi;
    public GetPhoneNumberAPI(GetPhoneNumber data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }
    public GetPhoneNumber_Response GetPhoneNumberCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new GetPhoneNumber_Response("Please set ApiKey");
//        if(data.getDevice_token().equals(""))
//            return new GetPhoneNumber_Response("Please set Device Token");
//        if(data.getDevice_type().equals(""))
//            return new GetPhoneNumber_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
//        param.add(new BasicNameValuePair("device_token",data.getDevice_token()));
//        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        JSONArray jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL_Array(ApiClient.BasePath+"get_phones","POST",param);
        GetPhoneNumber_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new GetPhoneNumber_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.length() > 0)
                {
                    JSONObject jo = null;
                    ArrayList<NumberData> data = new ArrayList<>();
                    for(int i = 0 ; i <jo.length() ; i++)
                    {
                        jo = jobj.getJSONObject(0);

                        data.add(new NumberData(jo.getString("phone_number"),jo.getString("number"),jo.getString("prefix"),jo.getString("friendly_name"),jo.getString("flag"),jo.getString("country")));
                    }

                    response_data = new GetPhoneNumber_Response("data get successfully!!",data);
                    return response_data;
                }
                else
                {
                    response_data = new GetPhoneNumber_Response("Something Problem");
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
