package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.Folders;
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;
import com.recordapi.client.model.Setting.UpdateFolderOrder;
import com.recordapi.client.model.Setting.UpdateFolderOrder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/15/2018.
 */

public class UpdateFolderOrderAPI
{
    private UpdateFolderOrder data ;
    private RecordingApi recordingApi;
    public UpdateFolderOrderAPI(UpdateFolderOrder data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }
    public UpdateFolderOrder_Response UpdateFolderOrderCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new UpdateFolderOrder_Response("Please set ApiKey");
        if(data.getFolderdata().equals(null))
            return new UpdateFolderOrder_Response("Please set Folder Position and ID");
//        if(data.getDevice_type().equals(""))
//            return new UpdateFolderOrder_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        
//        for(data.get?)
        for (Folders fol:
             data.getFolderdata())
        {
            param.add(new BasicNameValuePair("folders["+fol.getId()+"]",fol.getId()));
        }

        //param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_folders_order","POST",param);
        UpdateFolderOrder_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new UpdateFolderOrder_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new UpdateFolderOrder_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new UpdateFolderOrder_Response(jobj.getString("msg"));
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
