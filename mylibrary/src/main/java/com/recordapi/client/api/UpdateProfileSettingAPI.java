package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;
import com.recordapi.client.model.Setting.UpdateProfileSetting;
import com.recordapi.client.model.Setting.UpdateProfileSetting_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateProfileSettingAPI
{
    private UpdateProfileSetting data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;


    public UpdateProfileSettingAPI(UpdateProfileSetting data,RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        UpdateProfileSettingCall();
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
        JSONObject response = (JSONObject) obj;

        RegisterPhone_Response response_data  = new RegisterPhone_Response();

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

    }
    public void UpdateProfileSettingCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //Validation
        if(data.getApi_key().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response("Please set ApiKey"));
        if(data.getF_name().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response("Please set Fname"));
        if(data.getL_name().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response("Please set Lname"));
        if(data.getEmail().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response("Please set Email"));
            if(data.getIs_public().equals(""))
            {}
            else
            {
                if(0 == Integer.parseInt(data.getIs_public().toString()) | Integer.parseInt(data.getIs_public().toString()) == 1)
                {
                    param.add(new BasicNameValuePair("data[is_public]",data.getIs_public()));
                }
                else
                {
                    mListener.onFailure(new UpdateProfileSetting_Response("Please set IsPublic is 0 or 1"));
                }
            }
        if(data.getLanguage().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response("Please set Language"));
        if(data.getMax_length().equals(""))
        {}
        else
        {
            if(0 < Integer.parseInt(data.getMax_length().toString()) && Integer.parseInt(data.getMax_length().toString()) <= 120)
            {
                param.add(new BasicNameValuePair("data[max_length]",data.getMax_length()));
            }
            else
            {
                mListener.onFailure(new UpdateProfileSetting_Response("Please set Max Length between 1 to 120"));
            }
        }
        if(data.getPlay_beep().equals(""))
        {}
        else
        {
            if(0 == Integer.parseInt(data.getPlay_beep().toString()) | Integer.parseInt(data.getPlay_beep().toString()) == 1)
            {
                param.add(new BasicNameValuePair("data[play_beep]",data.getPlay_beep()));
            }
            else
            {
                mListener.onFailure(new UpdateProfileSetting_Response("Please set Play Beep is 0 or 1"));
            }
        }
            //return new UpdateProfileSetting_Response("Please set Fname");

        // Set parameter
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("data[f_name]",data.getF_name()));
        param.add(new BasicNameValuePair("data[l_name]",data.getL_name()));
        param.add(new BasicNameValuePair("data[language]",data.getLanguage()));
        param.add(new BasicNameValuePair("data[email]",data.getEmail()));

        //param.add(new BasicNameValuePair("data",data.getData()));


        webservice_call.handleRequest(1,ApiClient.BasePath+"update_profile",param,"POST");


    }
}
