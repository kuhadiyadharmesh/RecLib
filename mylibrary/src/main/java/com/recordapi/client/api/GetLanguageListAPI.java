package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.Language;
import com.recordapi.client.model.Common.Message;
import com.recordapi.client.model.Setting.GetLanguageList_Response;
import com.recordapi.client.model.Setting.GetLanguagesList;
import com.recordapi.client.model.Setting.GetMessage;
import com.recordapi.client.model.Setting.GetMessage_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetLanguageListAPI
{
    private GetLanguagesList data ;
    private RecordingApi recordingApi;

    public GetLanguageListAPI(GetLanguagesList data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public GetLanguageList_Response GetLanguagesListCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            return new GetLanguageList_Response("Please set ApiKey");
//        if(data.getFile().equals(""))
//            return new GetMessage_Response("Please select file");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        //param.add(new BasicNameValuePair("data",data.getData()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_languages","POST",param);
        GetLanguageList_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new GetLanguageList_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    JSONArray jar = jobj.getJSONArray("languages");
                    Language lang = null;
                    ArrayList<Language> languageslist = new ArrayList<>();
                    for(int i = 0 ; i < jar.length() ;i++)
                    {
                        JSONObject jo = jar.getJSONObject(i);
                        lang = new Language(jo.getString("code"),jo.getString("name"));//(jo.getString("id"),jo.getString("title"),jo.getString("body"),jo.getString("time"));
                        languageslist.add(lang);
                    }
                    response_data = new GetLanguageList_Response("language List get successfully . ",languageslist);
                    return response_data;
                }
                else
                {
                    response_data = new GetLanguageList_Response(jobj.getString("msg"));
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
