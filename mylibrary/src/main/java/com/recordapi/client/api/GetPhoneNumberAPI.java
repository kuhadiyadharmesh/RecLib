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
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.Common.NumberData;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.GetPhoneNumber;
import com.recordapi.client.model.Setting.GetPhoneNumber_Response;
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetPhoneNumberAPI
{

    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;

    public GetPhoneNumberAPI(Context c, RecordingApiListener mListener)
    {
       // this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetPhoneNumberCall();
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
        JSONArray jobj = (JSONArray) obj;

        GetPhoneNumber_Response response_data  ;//= new GetPhoneNumber_Response();

        if(jobj == null)
        {
            response_data = new GetPhoneNumber_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.length() > 0)
                {
                    JSONObject jo = null;
                    ArrayList<NumberData> data = new ArrayList<>();
                    for( int i = 0 ; i <jobj.length() ; i++)
                    {
                        jo = jobj.getJSONObject(i);

                        data.add(new NumberData(jo.getString(C_constant.phone_number),jo.getString(C_constant.number),jo.getString(C_constant.prefix),jo.getString(C_constant.friendly_name),jo.getString(C_constant.flag),jo.getString(C_constant.country)));
                    }

                    response_data = new GetPhoneNumber_Response(C_constant.s_data_list_successfully,data);
                    mListener.onSuccess(response_data);
                    //return response_data;
                }
                else
                {
                    response_data = new GetPhoneNumber_Response(C_constant.wrong_message);
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new GetPhoneNumber_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }

    public void GetPhoneNumberCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new GetPhoneNumber_Response("Please set ApiKey"));
//        if(data.getDevice_token().equals(""))
//            return new GetPhoneNumber_Response("Please set Device Token");
//        if(data.getDevice_type().equals(""))
//            return new GetPhoneNumber_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
//        param.add(new BasicNameValuePair("device_token",data.getDevice_token()));
//        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));

        //recordingApi.makeHttpRequestFor_SSL_Array(ApiClient.BasePath+"get_phones","POST",param , new Ine);

        webservice_call.handleRequest(2,ApiClient.get_phones,param,"POST");

    }
}
