package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Folder.DeleteFolder;
import com.recordapi.client.model.Folder.DeleteFolder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class DeleteFolderAPI
{

    private DeleteFolder data ;
    private RecordingApi recordingApi;

    public DeleteFolderAPI(DeleteFolder data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public DeleteFolder_Response DeleteFolderCall()
    {
        // Validation
        if (data.getApi_key().equals(""))
            return  new DeleteFolder_Response("Please enter api key");
        if(data.getFolder_id().equals(""))
            return  new DeleteFolder_Response("Please set folder Id ");
        if (data.getMove_to().equals(""))
            return  new DeleteFolder_Response("Please set 31 in move_to");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFolder_id()));
        param.add(new BasicNameValuePair("move_to",data.getMove_to()));


        // Call service
        JSONObject jobj = null;
        DeleteFolder_Response response_data  = null;
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"delete_folder","POST",param);
        if(jobj == null)
        {
            response_data = new DeleteFolder_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new DeleteFolder_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new DeleteFolder_Response(jobj.getString("msg"));
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
