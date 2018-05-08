package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.Folders;
import com.recordapi.client.model.Setting.NotifyUser;
import com.recordapi.client.model.Setting.NotifyUser_Response;
import com.recordapi.client.model.Setting.UpdateFolderOrder;
import com.recordapi.client.model.Setting.UpdateFolderOrder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/15/2018.
 */

public class NotifyUserAPI
{
    private NotifyUser data ;
    private RecordingApi recordingApi;
    public NotifyUserAPI(NotifyUser data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }
    public NotifyUser_Response NotifyUserCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new NotifyUser_Response("Please set ApiKey");
        if(data.getBody().equals(""))
            return new NotifyUser_Response("Please set body");
        if(data.getTitle().equals(""))
            return new NotifyUser_Response("Please set title");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("body",data.getBody()));
        param.add(new BasicNameValuePair("title",data.getTitle()));
        param.add(new BasicNameValuePair("device_type","android"));

////        for(data.get?)
//        for (Folders fol:
//                data.getFolderdata())
//        {
//            param.add(new BasicNameValuePair("folders["+fol.getId()+"]",fol.getId()));
//        }

        //param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"notify_user_custom","POST",param);
        NotifyUser_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new NotifyUser_Response("Something Wrong");
        }
        else
        {
            try
            {//"success":1
                if (jobj.getString("success").equals("1"))
                {
                    response_data = new NotifyUser_Response(true,"Message sent!");
                    return response_data;
                }
                else
                {
                    response_data = new NotifyUser_Response("Message not sent");
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
