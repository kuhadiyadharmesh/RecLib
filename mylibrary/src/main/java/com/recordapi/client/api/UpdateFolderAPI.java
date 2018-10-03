package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Folder.UpdateFolder;
import com.recordapi.client.model.Folder.UpdateFolder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class UpdateFolderAPI
{
    private UpdateFolder data ;
    private RecordingApi recordingApi;

    public UpdateFolderAPI(UpdateFolder data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public UpdateFolder_Response UpdateFolderCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        // Validation
        if (data.getApi_key().equals(""))
            return  new UpdateFolder_Response("Please enter api key");
        if(data.getFolder_id().equals(""))
            return  new UpdateFolder_Response("Please set folder Id ");
        if (data.getName().equals(""))
            return  new UpdateFolder_Response("Please set Folder Name");
        if (data.getPass()!="")
        {
            param.add(new BasicNameValuePair("pass",data.getPass()));
        }
        // Set parameter

        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFolder_id()));
        param.add(new BasicNameValuePair("name",data.getName()));


        // Call service
        JSONObject jobj = null;
        UpdateFolder_Response response_data  = null;
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_folder","POST",param);
        if(jobj == null)
        {
            response_data = new UpdateFolder_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new UpdateFolder_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new UpdateFolder_Response(jobj.getString("msg"));
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
