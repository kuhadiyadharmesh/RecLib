package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.Meta.CreateMetaFile;
import com.recordapi.client.model.Meta.CreateMetaFile_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class CreateMetaFileAPI
{
    private CreateMetaFile data ;
    private RecordingApi recordingApi;

    public CreateMetaFileAPI(CreateMetaFile data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public CreateMetaFile_Response CreateMetaFileCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new CreateMetaFile_Response("Please set ApiKey");
        if(data.getFile().equals(""))
            return new CreateMetaFile_Response("Please select file");
        if(data.getName().equals(""))
            return new CreateMetaFile_Response("Please Enter Name");
        if(data.getParent_id().equals(""))
            return new CreateMetaFile_Response("Please set parent id");
        if(data.getId().equals(""))
            return new CreateMetaFile_Response("Please set id default 0");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("name",data.getName()));
        param.add(new BasicNameValuePair("parent_id",data.getParent_id()));
        param.add(new BasicNameValuePair("id",data.getId()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"upload_meta_file","POST",param);
        CreateMetaFile_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new CreateMetaFile_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new CreateMetaFile_Response(jobj.getString("msg"),jobj.getString("id"));
                    return response_data;
                }
                else
                {
                    response_data = new CreateMetaFile_Response(jobj.getString("msg"));
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
