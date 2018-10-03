package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.Folder.CreateFolder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class CreateFileAPI
{
    private CreateFile data ;
    private RecordingApi recordingApi;

    public CreateFileAPI(CreateFile data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public CreateFile_Response CreateFileCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new CreateFile_Response("Please set ApiKey");
        if(data.getFile().equals(""))
            return new CreateFile_Response("Please select file");
        if(data.getData().equals(""))
            return new CreateFile_Response("Please Enter folder name and notes");
        if (data.getRemind_days().equals(""))
            return new CreateFile_Response("Please Enter RemindDays.");
        if (data.getRemind_date().equals(""))
            return new CreateFile_Response("Please Enter RemindDate.");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("data",data.getData()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"create_file","POST",param);
        CreateFile_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new CreateFile_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new CreateFile_Response(jobj.getString("msg"),jobj.getString("id"));
                    return response_data;
                }
                else
                {
                    response_data = new CreateFile_Response(jobj.getString("msg"));
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
