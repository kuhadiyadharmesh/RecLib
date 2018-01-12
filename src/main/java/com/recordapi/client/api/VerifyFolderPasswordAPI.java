package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Folder.UpdateFolder;
import com.recordapi.client.model.Folder.UpdateFolder_Response;
import com.recordapi.client.model.Folder.VerifyFolderPassword;
import com.recordapi.client.model.Folder.VerifyFolderPassword_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class VerifyFolderPasswordAPI
{
    private VerifyFolderPassword data ;
    private RecordingApi recordingApi;

    public VerifyFolderPasswordAPI(VerifyFolderPassword data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public VerifyFolderPassword_Response VerifyFolderPasswordCall()
    {
        // Validation
        if (data.getApi_key().equals(""))
            return  new VerifyFolderPassword_Response("Please enter api key");
        if(data.getFolder_id().equals(""))
            return  new VerifyFolderPassword_Response("Please set folder Id ");
        if (data.getPassword().equals(""))
            return  new VerifyFolderPassword_Response("Please Folder Password");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFolder_id()));
        param.add(new BasicNameValuePair("pass",data.getPassword()));


        // Call service
        JSONObject jobj = null;
        VerifyFolderPassword_Response response_data  = null;
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"verify_folder_pass","POST",param);
        if(jobj == null)
        {
            response_data = new VerifyFolderPassword_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new VerifyFolderPassword_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new VerifyFolderPassword_Response(jobj.getString("msg"));
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
