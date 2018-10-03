package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.Message;
import com.recordapi.client.model.Setting.GetMessage;
import com.recordapi.client.model.Setting.GetMessage_Response;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetMessageAPI
{
    private GetMessage data ;
    private RecordingApi recordingApi;

    public GetMessageAPI(GetMessage data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public GetMessage_Response GetMessageCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new GetMessage_Response("Please set ApiKey");
//        if(data.getFile().equals(""))
//            return new GetMessage_Response("Please select file");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        //param.add(new BasicNameValuePair("data",data.getData()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_msgs","POST",param);
        GetMessage_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new GetMessage_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    JSONArray jar = jobj.getJSONArray("msgs");
                    Message msg = null;
                    ArrayList<Message> messagelist = new ArrayList<>();
                    for(int i = 0 ; i < jar.length() ;i++)
                    {
                        JSONObject jo = jar.getJSONObject(i);
                        msg = new Message(jo.getString("id"),jo.getString("title"),jo.getString("body"),jo.getString("time"));

                        messagelist.add(msg);

                    }
                    response_data = new GetMessage_Response("Message list get Successfully .",messagelist);
                    return response_data;
                }
                else
                {
                    response_data = new GetMessage_Response(jobj.getString("msg"));
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
