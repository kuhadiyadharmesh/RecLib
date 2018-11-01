package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Folder.CreateFolder;
import com.recordapi.client.model.Folder.CreateFolder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class CreateFolderAPI
{
    private RecordingApi recordingApi = null;
    private CreateFolder createFolder = null;

    public CreateFolderAPI(CreateFolder createFolder)
    {
        this.createFolder = createFolder;
        recordingApi = new RecordingApi();
    }

    public CreateFolder_Response CreateFolderCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",createFolder.getApi_key()));
        param.add(new BasicNameValuePair("name",createFolder.getName()));

        // Validation
        if (createFolder.getApi_key().equals(""))
            return  new CreateFolder_Response("Please enter api key");
        if(createFolder.getName().equals(""))
            return  new CreateFolder_Response("Please enter folder name ");
        if (createFolder.getPass()!="")
        {
            param.add(new BasicNameValuePair("pass",createFolder.getPass()));
        }
           // return  new CreateFolder_Response("Please enter password of this folder");

        // Set parameter




        // Call service
        JSONObject jobj = null;
        CreateFolder_Response response_data  = null;
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"create_folder","POST",param);
        if(jobj == null)
        {
            response_data = new CreateFolder_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new CreateFolder_Response(jobj.getString("id"),jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new CreateFolder_Response(jobj.getString("msg"));
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
