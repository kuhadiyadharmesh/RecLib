package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Folder.VerifyFolderPassword;
import com.recordapi.client.model.Folder.VerifyFolderPassword_Response;
import com.recordapi.client.model.StartUnStarFolderFile;
import com.recordapi.client.model.StartUnStartFolderFile_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class StartUnStartFileFolderAPI
{
    private StartUnStarFolderFile data ;
    private RecordingApi recordingApi;

    public StartUnStartFileFolderAPI(StartUnStarFolderFile data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public StartUnStartFolderFile_Response StartUnStartFileFolderCall()
    {
        // Validation
        if (data.getApi_key().equals(""))
            return  new StartUnStartFolderFile_Response("Please enter api key");
        if(data.getFolder_id().equals(""))
            return  new StartUnStartFolderFile_Response("Please set folder Id or file Id");
        if (data.getStar().equals(""))
            return  new StartUnStartFolderFile_Response("Please Set Start or UnStart");
        if (data.getType().equals(""))
            return  new StartUnStartFolderFile_Response("Please Set File or Folder as type");


        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFolder_id()));
        param.add(new BasicNameValuePair("star",data.getStar()));
        param.add(new BasicNameValuePair("type",data.getType()));


        // Call service
        JSONObject jobj = null;
        StartUnStartFolderFile_Response response_data  = null;
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_star","POST",param);
        if(jobj == null)
        {
            response_data = new StartUnStartFolderFile_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new StartUnStartFolderFile_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new StartUnStartFolderFile_Response(jobj.getString("msg"));
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
