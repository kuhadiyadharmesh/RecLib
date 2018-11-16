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


    public GetProfileSettingAPI(Context c, RecordingApiListener mListener)
    {
        //this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
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
            response_data = new GetProfileSetting_Response("Something Wrong");
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    //profile
                    JSONObject injob = jobj.getJSONObject("profile");

                    response_data = new GetProfileSetting_Response(injob.getString("pic"),injob.getString("f_name"),injob.getString("l_name"),injob.getString("email"),injob.getString("is_public"),injob.getString("language"),injob.getString("play_beep"),injob.getString("max_length"),injob.getString("time_zone"),jobj.getString("app"),jobj.getString("credits"),jobj.getString("credits_trans"));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new GetProfileSetting_Response(jobj.getString("msg"));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new GetProfileSetting_Response("Something Wrong");
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
        param.add(new BasicNameValuePair("api_key",sd.getToken()));
        //param.add(new BasicNameValuePair("data",data.getData()));


    }
}
