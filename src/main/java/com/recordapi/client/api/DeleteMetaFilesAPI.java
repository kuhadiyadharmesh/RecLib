package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.DeleteFile;
import com.recordapi.client.model.File.DeleteFile_Response;
import com.recordapi.client.model.Meta.DeleteMetaFiles;
import com.recordapi.client.model.Meta.DeleteMetaFiles_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class DeleteMetaFilesAPI
{
    //action=remove_forever
    private DeleteMetaFiles data ;
    private RecordingApi recordingApi;

    public DeleteMetaFilesAPI(DeleteMetaFiles data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public DeleteMetaFiles_Response DeleteMetaFilesCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        if(data.getApi_key().equals(""))
            return  new DeleteMetaFiles_Response("Please set Api Key ");
        if (data.getParent_id()!=0)
            param.add(new BasicNameValuePair("parent_id",data.getParent_id()+""));
        else
        {
            if(data.getIds().equals(""))
                return  new DeleteMetaFiles_Response("Please Select At least one File for delete");
            else
            {
                String s = data.getIds();
                String[] ars = s.split(",");
                if(ars.length > 30)
                {
                    return new DeleteMetaFiles_Response("Maximum 30 File you can delete once right now you selected "+ars.length+" .");
                }
            }
            param.add(new BasicNameValuePair("ids",data.getIds()));
        }


        param.add(new BasicNameValuePair("api_key",data.getApi_key()));


        // Call service
        JSONObject jobj = null;
        DeleteMetaFiles_Response response_data  = null;
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"delete_meta_files","POST",param);
        if(jobj == null)
        {
            response_data = new DeleteMetaFiles_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new DeleteMetaFiles_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new DeleteMetaFiles_Response(jobj.getString("msg"));
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
