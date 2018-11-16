package com.recordapi.client.api;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.database.SaveData;
import com.recordapi.client.model.Common.Language;
import com.recordapi.client.model.Common.Message;
import com.recordapi.client.model.RegisterPhone_Response;
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
    //private GetLanguagesList data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;

    public GetLanguageListAPI(Context c, RecordingApiListener mListener)
    {
        //this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetLanguagesListCall();
    }

    private void Handlar_call()
    {
        uiHandler = new Handler() {
            public void handleMessage(android.os.Message msg)
            {
                try
                {
                    handleEvent(msg.what, msg.obj);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.e("error", "::" + e);
                    e.printStackTrace();
                    //Prg_dialog(false);
                }
            }
        };
    }

    private void handleEvent(int what, Object obj) throws JSONException
    {
        // TODO Auto-generated method stub
        Log.e("Event ", "response : " + obj.toString());
        JSONObject jobj = (JSONObject) obj;

        GetLanguageList_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new GetLanguageList_Response("Something Wrong");
            mListener.onFailure(response_data);
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
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new GetLanguageList_Response(jobj.getString("msg"));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new GetLanguageList_Response("Something Wrong");
                mListener.onFailure(response_data);
            }

        }
        /*
        if(response == null)
        {
            response_data = new RegisterPhone_Response("Something wrong ");
        }
        else
        {
            try
            {
                if (response.getString("status").equals("ok"))
                {response_data.setStatus(true);
                    response_data.setMsg(response.getString("msg"));
                    response_data.setPhone(response.getString("phone"));

                    //returnObject = response_data;
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data.setStatus(false);
                    response_data.setMsg(response.getString("msg"));

                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                //mListener.onFailure(new RegisterPhone_Response("please enter valid token"));
            }

        }
        //returnObject = response_data;
        mListener.onFailure(response_data);
        */

    }
    public void GetLanguagesListCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new GetLanguageList_Response("Please set ApiKey"));
//        if(data.getFile().equals(""))
//            return new GetMessage_Response("Please select file");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",sd.getToken()));
        //param.add(new BasicNameValuePair("data",data.getData()));

        webservice_call.handleRequest(1,ApiClient.BasePath+"get_languages",param,"POST");

    }
}
