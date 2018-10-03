package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.RecoverFile;
import com.recordapi.client.model.File.RecoverFile_Response;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

//if folder_id not provided then file will be recovered to its original folder
public class RecoverFileAPI
{
    private RecoverFile data ;
    private RecordingApi recordingApi;

    public RecoverFileAPI(RecoverFile data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public RecoverFile_Response RecoveryFileCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new RecoverFile_Response("Please set ApiKey");
        if(data.getFile_id().equals(""))
            return new RecoverFile_Response("Please set fileId");
        if(data.getFolder_id().equals(""))
            return new RecoverFile_Response("Please set FolderId");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFile_id()));
        param.add(new BasicNameValuePair("folder_id",data.getFolder_id()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"recover_file","POST",param);
        RecoverFile_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new RecoverFile_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new RecoverFile_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new RecoverFile_Response(jobj.getString("msg"));
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
