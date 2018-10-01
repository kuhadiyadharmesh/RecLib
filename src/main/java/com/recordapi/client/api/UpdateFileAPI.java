package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.File.UpdateFile;
import com.recordapi.client.model.File.UpdateFile_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateFileAPI
{
    private UpdateFile data ;
    private RecordingApi recordingApi;

    public UpdateFileAPI(UpdateFile data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public UpdateFile_Response UpdateFileCall()
    {
          if (data.getFileInfo().equals(null))
            return new UpdateFile_Response("Please EnterFile Info.");
        if(data.getApi_key().equals(""))
            return new UpdateFile_Response("Please set Api Key");
        if(data.getFileInfo().getId().equals(""))
            return new UpdateFile_Response("Please set File Id.");
        if (data.getFileInfo().getF_name().equals(""))
            return new UpdateFile_Response("Please set File FName.");
        if (data.getFileInfo().getL_name().equals(""))
                    return new UpdateFile_Response("Please set File LName.");
        if (data.getFileInfo().getNotes().equals(""))
                            return new UpdateFile_Response("Please set File Notes.");
        if (data.getFileInfo().getEmail().equals(""))
                                    return new UpdateFile_Response("Please set File Email.");
        if (data.getFileInfo().getPhone().equals(""))
                                            return new UpdateFile_Response("Please set File Phone.");
        if (data.getFileInfo().getMeta_notes().equals(""))
                                                    return new UpdateFile_Response("Please set Meta Notes.");
        if(data.getFileInfo().getMeta_url().equals(""))
            return  new UpdateFile_Response("Please set Meta URL ");
        if(data.getFileInfo().getFolder_id().equals(""))
            return  new UpdateFile_Response("Please set FolderId");
        if(data.getFileInfo().getReminder_dates().equals(""))
            return new UpdateFile_Response("Please set Reminder Date");
        if(data.getFileInfo().getReminder_days().equals(""))
           return new UpdateFile_Response("Please set Reminder Days");
      /*  if(data.getFileInfo().getTags().equals(""))
            return new UpdateFile_Response("Please set Tags");
        if(data.getFileInfo().getIsStar().equals(""))
            return new UpdateFile_Response("Please set IsStart");*/

        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFileInfo().getId()));
        param.add(new BasicNameValuePair("f_name",data.getFileInfo().getF_name()));
        param.add(new BasicNameValuePair("l_name",data.getFileInfo().getL_name()));
        param.add(new BasicNameValuePair("notes",data.getFileInfo().getNotes()));
        param.add(new BasicNameValuePair("email",data.getFileInfo().getEmail()));
        param.add(new BasicNameValuePair("phone",data.getFileInfo().getPhone()));
        param.add(new BasicNameValuePair("tags",data.getFileInfo().getTags()));
        param.add(new BasicNameValuePair("meta[note]",data.getFileInfo().getMeta_notes()));
        param.add(new BasicNameValuePair("meta[duration]",data.getFileInfo().getMeta_duration()));
        param.add(new BasicNameValuePair("meta[url]",data.getFileInfo().getMeta_url()));
        param.add(new BasicNameValuePair("folder_id",data.getFileInfo().getFolder_id()));
        param.add(new BasicNameValuePair("name",data.getFileInfo().getName()));
        param.add(new BasicNameValuePair("remind_days",data.getFileInfo().getReminder_days()));
        param.add(new BasicNameValuePair("remind_date",data.getFileInfo().getReminder_dates()));


        JSONObject jobj = null;
        UpdateFile_Response response_data = null;

        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_file","POST",param);

        if(jobj == null)
        {
            response_data = new UpdateFile_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new UpdateFile_Response(true,jobj.getString("msg"),jobj.getString("id"));
                    return response_data;
                }
                else
                {
                    response_data = new UpdateFile_Response(jobj.getString("msg"));
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
