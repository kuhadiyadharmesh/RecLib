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
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.GetProfileSetting;
import com.recordapi.client.model.Setting.GetProfileSetting_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetProfileSettingAPI
{
    //private GetProfileSetting data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet;


    public GetProfileSettingAPI(Context c, RecordingApiListener mListener)
    {
        //this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetProfileSettingCall();
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

        GetProfileSetting_Response response_data ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new GetProfileSetting_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    //profile
                    sd.setProfile_JSON(jobj.toString());
                    JSONObject injob = jobj.getJSONObject(C_constant.profile);

                    response_data = new GetProfileSetting_Response(injob.getString(C_constant.pic),injob.getString(C_constant.f_name),injob.getString(C_constant.l_name),injob.getString(C_constant.email),injob.getString(C_constant.is_public),injob.getString(C_constant.language),injob.getString(C_constant.play_beep),injob.getString(C_constant.max_length),injob.getString(C_constant.time_zone),jobj.getString(C_constant.app),jobj.getString(C_constant.credits),jobj.getString(C_constant.credits_trans));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new GetProfileSetting_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new GetProfileSetting_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }
    public void GetProfileSettingCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new GetProfileSetting_Response("Please set ApiKey"));
//        if(data.getFile().equals(""))
//            return new CreateFile_Response("Please select file");
//        if(data.getData().equals(""))
//            return new CreateFile_Response("Please Enter folder name and notes");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
       // param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        //param.add(new BasicNameValuePair("data",data.getData()));

        if(internet.check_internet() && sd.getProfile_JSON().length() == 0)
        webservice_call.handleRequest(1,ApiClient.get_profile,param,"POST");
        else
        {
            try {
                JSONObject jobj = new JSONObject(sd.getProfile_JSON());
                JSONObject injob = jobj.getJSONObject(C_constant.profile);

                GetProfileSetting_Response response_data = new GetProfileSetting_Response(injob.getString(C_constant.pic),injob.getString(C_constant.f_name),injob.getString(C_constant.l_name),injob.getString(C_constant.email),injob.getString(C_constant.is_public),injob.getString(C_constant.language),injob.getString(C_constant.play_beep),injob.getString(C_constant.max_length),injob.getString(C_constant.time_zone),jobj.getString(C_constant.app),jobj.getString(C_constant.credits),jobj.getString(C_constant.credits_trans));
                mListener.onSuccess(response_data);
            } catch (JSONException e) {
                e.printStackTrace();
                mListener.onFailure(new GetProfileSetting_Response(C_constant.no_Internet));
            }
        }
    }
}
