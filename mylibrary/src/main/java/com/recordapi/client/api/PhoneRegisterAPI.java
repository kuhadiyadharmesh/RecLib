package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;

import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;

import com.recordapi.client.model.RegisterPhone;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class PhoneRegisterAPI
{
    public RegisterPhone data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    public Handler uiHandler;

    public PhoneRegisterAPI(RegisterPhone data,RecordingApiListener mListener)
    {
        this.data= data;
       // recordingApi = new RecordingApi();

        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        RegisterPhoneCall();
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

//RegisterPhone_Response
    public void RegisterPhoneCall()
    {
        Log.e("new method called","new called");
        if (data.getPhonenumber()=="")
        {
            mListener.onFailure(new RegisterPhone_Response("please enter phonenumber"));
        }/*
        if(data.getToken().equals(""))
        {
            mListener.onFailure(new RegisterPhone_Response("please enter valid token"));
        }*/


        final ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("phone", data.getPhonenumber()));
        param.add(new BasicNameValuePair("token", "55942ee3894f51000530894"));

        webservice_call.handleRequest(1 ,ApiClient.BasePath + "register_phone", param,"POST");//(Constant.get_mob_register_type,Constant.Generate_request(str),"POST");







    }

}
