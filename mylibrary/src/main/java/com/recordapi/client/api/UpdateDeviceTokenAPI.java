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
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;
import com.recordapi.client.model.Setting.UpdateUser;
import com.recordapi.client.model.Setting.UpdateUser_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class UpdateDeviceTokenAPI
{
    private UpdateDeviceToken data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet ;

    public UpdateDeviceTokenAPI(Context c, UpdateDeviceToken data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
       internet = new InternetConnection(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        UpdateDeviceTokenCall();
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

        UpdateDeviceToken_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new UpdateDeviceToken_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new UpdateDeviceToken_Response(true,jobj.getString(C_constant.msg));
                   mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new UpdateDeviceToken_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new UpdateDeviceToken_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }
    public void UpdateDeviceTokenCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new UpdateDeviceToken_Response("Please set ApiKey"));
        if(data.getDevice_token().equals(""))
            mListener.onFailure(new UpdateDeviceToken_Response(C_constant.v_set_devicetoken_validation));
        //if(data.getDevice_type().equals(""))
        //    return new UpdateDeviceToken_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.device_token,data.getDevice_token()));
        param.add(new BasicNameValuePair(C_constant.device_type,"android"));


        if(internet.check_internet())
        webservice_call.handleRequest(1,ApiClient.update_device_toke,param,"POST");
        else
            mListener.onFailure(new UpdateDeviceToken_Response(C_constant.no_Internet));

    }
}
