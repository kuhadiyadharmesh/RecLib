package com.recordapi.client.api;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.database.InternetConnection;
import com.recordapi.client.database.SaveData;
import com.recordapi.client.model.C_constant;
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
    private SaveData sd;
    private InternetConnection internet;

    public UpdateProfileSettingAPI(Context c, UpdateProfileSetting data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
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
        JSONObject jobj = (JSONObject) obj;

        UpdateProfileSetting_Response response_data ;// = new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new UpdateProfileSetting_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new UpdateProfileSetting_Response(true,jobj.getString(C_constant.msg));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new UpdateProfileSetting_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new UpdateProfileSetting_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }
    public void UpdateProfileSettingCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new UpdateProfileSetting_Response("Please set ApiKey"));
        if(data.getF_name().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response(C_constant.v_setfname_validation));
        if(data.getL_name().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response(C_constant.v_setlname_validation));
        if(data.getEmail().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response(C_constant.v_setemail_validation));
            if(data.getIs_public().equals(""))
            {}
            else
            {
                if(0 == Integer.parseInt(data.getIs_public().toString()) | Integer.parseInt(data.getIs_public().toString()) == 1)
                {
                    param.add(new BasicNameValuePair(C_constant.data_public,data.getIs_public()));
                }
                else
                {
                    mListener.onFailure(new UpdateProfileSetting_Response(C_constant.v_setispublic_validation));
                }
            }
        if(data.getLanguage().equals(""))
            mListener.onFailure(new UpdateProfileSetting_Response(C_constant.v_setlanguage_validation));
        if(data.getMax_length().equals(""))
        {}
        else
        {
            if(0 < Integer.parseInt(data.getMax_length().toString()) && Integer.parseInt(data.getMax_length().toString()) <= 120)
            {
                param.add(new BasicNameValuePair(C_constant.data_maxlength,data.getMax_length()));
            }
            else
            {
                mListener.onFailure(new UpdateProfileSetting_Response(C_constant.v_setmaxlength_validation));
            }
        }
        if(data.getPlay_beep().equals(""))
        {}
        else
        {
            if(0 == Integer.parseInt(data.getPlay_beep().toString()) | Integer.parseInt(data.getPlay_beep().toString()) == 1)
            {
                param.add(new BasicNameValuePair(C_constant.data_play_beep,data.getPlay_beep()));
            }
            else
            {
                mListener.onFailure(new UpdateProfileSetting_Response(C_constant.v_setplaybeep_validation));
            }
        }
            //return new UpdateProfileSetting_Response("Please set Fname");

        // Set parameter
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.data_fname,data.getF_name()));
        param.add(new BasicNameValuePair(C_constant.data_lname,data.getL_name()));
        param.add(new BasicNameValuePair(C_constant.data_language,data.getLanguage()));
        param.add(new BasicNameValuePair(C_constant.data_email,data.getEmail()));

        //param.add(new BasicNameValuePair("data",data.getData()));


        if(internet.check_internet())
        webservice_call.handleRequest(1,ApiClient.update_profile,param,"POST");
        else
            mListener.onFailure(new UpdateProfileSetting_Response(C_constant.no_Internet));

    }
}
