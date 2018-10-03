package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.CloneFile;
import com.recordapi.client.model.File.CloneFile_Response;
import com.recordapi.client.model.File.DeleteFile;
import com.recordapi.client.model.File.DeleteFile_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class CloneFileAPI
{
    //action=remove_forever
    private CloneFile data ;
    private RecordingApi recordingApi;

    public CloneFileAPI(CloneFile data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public CloneFile_Response CloneFileCall()
    {


        if(data.getApi_key().equals(""))
            return  new CloneFile_Response("Please set Api Key ");
        if(data.getFile_ids().equals(""))
            return  new CloneFile_Response("Please Select At least one File for delete");
//        else
//        {
//            String s = data.getFile_ids();
//            String[] ars = s.split(",");
//            if(ars.length > 30)
//            {
//                return new DeleteFile_Response("Maximum 30 File you can delete once right now you selected "+ars.length+" .");
//            }
//        }
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFile_ids()));

        // Call service
        JSONObject jobj = null;
        CloneFile_Response response_data  = null;
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"clone_file","POST",param);
        if(jobj == null)
        {
            response_data = new CloneFile_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new CloneFile_Response(true,jobj.getString("msg"),jobj.getString("id"));
                    return response_data;
                }
                else
                {
                    response_data = new CloneFile_Response(jobj.getString("msg"));
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
