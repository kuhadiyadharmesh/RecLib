package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.FolderData;
import com.recordapi.client.model.Folder.GetFolder;
import com.recordapi.client.model.Folder.GetFolder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class GetFolderAPI
{
    //{"status":"ok","folders":[{"id":"29","name":"test","created":"1433989312"},{"id":"31","name":"test3","created":"1434009445"}],"msg":"Success"}

    private GetFolder data ;
    private RecordingApi recordingApi;

    public GetFolderAPI(GetFolder data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public GetFolder_Response GetFolderCall()
    {
        // validation
        if (data.getApi_key().equals(""))
            return  new GetFolder_Response("Please set Api_key");

        // set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key", data.getApi_key()));

        JSONObject jobj = null;
        GetFolder_Response response_data  = null;//new GetFolder_Response();

        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_folders","POST",param);
        if(jobj == null)
        {
            response_data = new GetFolder_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    JSONArray jar = jobj.getJSONArray("folders");
                    ArrayList<FolderData> fdata = new ArrayList<>();
                    FolderData fo = null;
                    for (int i = 0; i < jar.length(); i++)
                    {
                        JSONObject jo = jar.getJSONObject(i);
                        fo = new FolderData(jo.getString("id"),jo.getString("name"),jo.getString("created"));
                        fdata.add(fo);
                    }
                    response_data = new GetFolder_Response(true ,jobj.getString("msg"),fdata);
                    return response_data;
                }
                else
                {
//                    response_data.setStatus(false);
//                    response_data.setMsg(jobj.getString("msg"));
                    response_data = new GetFolder_Response(jobj.getString("msg"));

                    return  response_data;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }
        return  response_data;

        //return null;
    }


}
