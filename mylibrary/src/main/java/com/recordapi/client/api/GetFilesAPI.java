package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.FileData;
import com.recordapi.client.model.Common.FolderData;
import com.recordapi.client.model.File.GetFiles;
import com.recordapi.client.model.File.GetFiles_Response;
import com.recordapi.client.model.Folder.GetFolder_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class GetFilesAPI
{

    private GetFiles data ;
    private RecordingApi recordingApi ;

    public GetFilesAPI(GetFiles data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public GetFiles_Response GetFileCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        //Validation
        if(data.getApi_key().equals(""))
            return new GetFiles_Response("Plese set ApiKey");
        if (data.getFolder_id()!="")
            param.add(new BasicNameValuePair("folder_id", data.getFolder_id()));
        if (data.getPage()!="")
            param.add(new BasicNameValuePair("page", data.getPage()));
        if(data.getPass()!="")
            param.add(new BasicNameValuePair("pass", data.getPass()));
        if(data.getSource()!="")
            param.add(new BasicNameValuePair("source", data.getSource()));
        if (data.getReminder())
            param.add(new BasicNameValuePair("reminder", ""+data.getReminder()));
        if (data.getSearch_text()!="")
            param.add(new BasicNameValuePair("q", data.getSearch_text()));

        param.add(new BasicNameValuePair("api_key", data.getApi_key()));

        JSONObject jobj = null;
        GetFiles_Response response_data  = null;//new GetFolder_Response();

        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_files","POST",param);
        if(jobj == null)
        {
            response_data = new GetFiles_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    JSONArray jar = jobj.getJSONArray("files");
                    ArrayList<FileData> fdata = new ArrayList<>();
                    FileData fo = null;
                    for (int i = 0; i < jar.length(); i++)
                    {
                        JSONObject jo = jar.getJSONObject(i);//jo.getString("access_number")
                        fo = new FileData(jo.getString("id"),"",jo.getString("name"),jo.getString("f_name"),jo.getString("l_name"),jo.getString("email"),jo.getString("phone"),jo.getString("notes"),jo.getString("meta"),jo.getString("source"),jo.getString("url"),jo.getString("credits"),jo.getString("duration"),jo.getString("time"),jo.getString("share_url"),jo.getString("download_url"),jo.getString("is_star"));
                        fdata.add(fo);
                    }
                    response_data = new GetFiles_Response("File List Available.",fdata,""+jobj.getInt("credits"));
                    return response_data;
                }
                else
                {
//                    response_data.setStatus(false);
//                    response_data.setMsg(jobj.getString("msg"));
                    response_data = new GetFiles_Response(jobj.getString("msg"));

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
