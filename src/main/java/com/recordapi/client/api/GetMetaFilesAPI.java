package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.FileData;
import com.recordapi.client.model.Common.MetaFileData;
import com.recordapi.client.model.File.GetFiles;
import com.recordapi.client.model.File.GetFiles_Response;
import com.recordapi.client.model.Meta.GetMetaFiles;
import com.recordapi.client.model.Meta.GetMetaFiles_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetMetaFilesAPI
{
    private GetMetaFiles data ;
    private RecordingApi recordingApi ;

    public GetMetaFilesAPI(GetMetaFiles data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public GetMetaFiles_Response GetMetaFilesCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        //Validation
        if(data.getApi_key().equals(""))
            return new GetMetaFiles_Response("Plese set ApiKey");
        if(data.getParent_id().equals(""))
            return new GetMetaFiles_Response("Plese set Parent Key");


        param.add(new BasicNameValuePair("api_key", data.getApi_key()));
        param.add(new BasicNameValuePair("parent_id", data.getParent_id()));


        JSONObject jobj = null;
        GetMetaFiles_Response response_data  = null;//new GetFolder_Response();

        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_meta_files","POST",param);
        if(jobj == null)
        {
            response_data = new GetMetaFiles_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    JSONArray jar = jobj.getJSONArray("meta_files");
                    ArrayList<MetaFileData> fdata = new ArrayList<>();
                    MetaFileData fo = null;
                    for (int i = 0; i < jar.length(); i++)
                    {
                        JSONObject jo = jar.getJSONObject(i);
                        fo = new MetaFileData(jo.getString("id"),jo.getString("name"),jo.getString("file"),jo.getString("parent_id"),jo.getString("user_id"),jo.getString("time"));
                        fdata.add(fo);
                    }
                    response_data = new GetMetaFiles_Response("Metafile get successfully .",fdata);
                    return response_data;
                }
                else
                {
//                    response_data.setStatus(false);
//                    response_data.setMsg(jobj.getString("msg"));
                    response_data = new GetMetaFiles_Response(jobj.getString("msg"));

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
