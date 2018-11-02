package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.VerifyPhone;
import com.recordapi.client.model.VerifyPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class VerifyPhoneAPI
{
    //private RecordingApi recordingApi;
    private VerifyPhone data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    public Handler uiHandler;

    public VerifyPhoneAPI(VerifyPhone data,RecordingApiListener mListener)
    {
        this.data = data ;

        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        VerifyPhoneCall();
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

    public  void  VerifyPhoneCall()
    {
        //VerifyPhone_Response response_data = new VerifyPhone_Response("server not response");

        // Validation
        if(data.getPhone().equals(""))
            mListener.onFailure(new VerifyPhone_Response("Please Enter Phone number"));///return new VerifyPhone_Response("Please Enter Phone number");;
        if(data.getCode().equals(""))
            mListener.onFailure(new VerifyPhone_Response("Please Enter Code"));
        if(data.getMacc().equals(""))
            mListener.onFailure(new VerifyPhone_Response("Please Enter MCC"));;
        if(data.getApp().equals(""))
            mListener.onFailure(new VerifyPhone_Response("Please Enter App Free or Paid"));;
        if(data.getToken().equals(""))
            mListener.onFailure( new VerifyPhone_Response("Please Enter Token"));;
        if(data.getDevice_token().equals(""))
            mListener.onFailure(new VerifyPhone_Response("Please Enter Notification token"));;
        if(data.getDevice_type().equals(""))
            mListener.onFailure(new VerifyPhone_Response("Please Enter Device Type"));;

        // Setting
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("phone",data.getPhone()));
        param.add(new BasicNameValuePair("code",data.getCode()));
        param.add(new BasicNameValuePair("mcc",data.getMacc()));
        param.add(new BasicNameValuePair("app",data.getApp()));
        param.add(new BasicNameValuePair("token",data.getToken()));
        param.add(new BasicNameValuePair("device_token",data.getDevice_token()));
        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));
        param.add(new BasicNameValuePair("time_zone",data.getTime_zone()));

        JSONObject jobj = null;
        VerifyPhone_Response response_data  = null;



        webservice_call.handleRequest(1 ,ApiClient.BasePath + "verify_phone", param,"POST");
        /*
        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"verify_phone","POST",param);

        if(jobj == null)
        {
           response_data = new VerifyPhone_Response("Something wrong ");
        }
        else
            {
                try
                {if (jobj.getString("status").equals("ok"))
                {

                    response_data = new VerifyPhone_Response(true , jobj.getString("phone"),jobj.getString("api_key"),jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new VerifyPhone_Response(jobj.getString("msg"));
                    return  response_data;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            }
         return  response_data;
        */
    }
}
