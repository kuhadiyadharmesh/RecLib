package com.recordapi.client.api;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.database.SaveData;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.GetTranslations;
import com.recordapi.client.model.Setting.GetTranslations_Response;
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetTranslationsAPI
{
    private GetTranslations data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;

    public GetTranslationsAPI(Context c, GetTranslations data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetTranslationsCall();
    }
    private void Handlar_call()
    {
        uiHandler = new Handler() {
            public void handleMessage(Message msg)
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

        GetTranslations_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new GetTranslations_Response("Something Wrong");
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new GetTranslations_Response("Translations get successfully.",jobj.getJSONObject("translation").getString("Trash"),jobj.getJSONObject("translation").getString("All Files"),jobj.getJSONObject("translation").toString());
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new GetTranslations_Response(jobj.getString("msg"));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new GetTranslations_Response("Something Wrong");
                mListener.onFailure(response_data);
            }

        }

    }
    public void GetTranslationsCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new GetTranslations_Response("Please set ApiKey"));
        if(data.getLanguage().equals(""))
            mListener.onFailure(new GetTranslations_Response("Please set Language en-us or en-uk"));
//        if(data.getDevice_type().equals(""))
//            return new UpdateDeviceToken_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",sd.getToken()));
        param.add(new BasicNameValuePair("language",data.getLanguage()));
//        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        webservice_call.handleRequest(1,ApiClient.BasePath+"get_translations",param,"POST");


    }
}
