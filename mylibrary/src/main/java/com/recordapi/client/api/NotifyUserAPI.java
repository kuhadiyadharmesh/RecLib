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
import com.recordapi.client.model.Common.Folders;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.NotifyUser;
import com.recordapi.client.model.Setting.NotifyUser_Response;
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

public class NotifyUserAPI
{
    private NotifyUser data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;

    public NotifyUserAPI(Context c, NotifyUser data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        NotifyUserCall();
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

        NotifyUser_Response response_data  ;// new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new NotifyUser_Response("Something Wrong");
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {//"success":1
                if (jobj.getString("success").equals("1"))
                {
                    response_data = new NotifyUser_Response(true,"Message sent!");
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new NotifyUser_Response("Message not sent");
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new NotifyUser_Response("Something Wrong");
                mListener.onFailure(response_data);
            }

        }

    }
    public void NotifyUserCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new NotifyUser_Response("Please set ApiKey"));
        if(data.getBody().equals(""))
            mListener.onFailure(new NotifyUser_Response("Please set body"));
        if(data.getTitle().equals(""))
            mListener.onFailure(new NotifyUser_Response("Please set title"));

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",sd.getToken()));
        param.add(new BasicNameValuePair("body",data.getBody()));
        param.add(new BasicNameValuePair("title",data.getTitle()));
        param.add(new BasicNameValuePair("device_type","android"));

        webservice_call.handleRequest(1,ApiClient.BasePath+"notify_user_custom",param,"POST");

    }
}
