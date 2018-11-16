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
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateProfilePicureAPI
{
    private UpdateProfilePicture data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;


    public UpdateProfilePicureAPI(Context c, UpdateProfilePicture data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        UpdateProfilePicureCall();
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

        UpdateProfilePicure_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new UpdateProfilePicure_Response("Something Wrong");
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new UpdateProfilePicure_Response(jobj.getString("msg"),jobj.getString("file"),jobj.getString("path"));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new UpdateProfilePicure_Response(jobj.getString("msg"));
                   // response_data = new UpdateProfilePicure_Response("Something Wrong");
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new UpdateProfilePicure_Response("Something Wrong");
                mListener.onFailure(response_data);
            }

        }

    }

    public void UpdateProfilePicureCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            mListener.onFailure(new UpdateProfilePicure_Response("Please set ApiKey"));
        if(data.getFile().equals(""))
            mListener.onFailure(new UpdateProfilePicure_Response("Please select file"));

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        //param.add(new BasicNameValuePair("data",data.getData()));

        webservice_call.handleRequest(1,ApiClient.Profile_Img_Path,param,"POST");


    }
}
