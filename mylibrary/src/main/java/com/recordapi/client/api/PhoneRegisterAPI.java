package com.recordapi.client.api;

import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.AsyncClass;
import com.recordapi.client.Listener.InternalApiListener;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.RegisterPhone;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class PhoneRegisterAPI
{
    public RegisterPhone data ;
   // private RecordingApi recordingApi;
    private RecordingApiListener mListener;
    private RegisterPhone_Response returnObject;
    private AsyncClass asyncClass;

    public PhoneRegisterAPI(RegisterPhone data,RecordingApiListener mListener)
    {
        this.data= data;
       // recordingApi = new RecordingApi();

        this.mListener = mListener;
        RegisterPhoneCall(mListener);
    }
//RegisterPhone_Response
    public void RegisterPhoneCall(final RecordingApiListener mListener)
    {
        Log.e("new method called","new called");
        //this.mListener = mListener;

        if (data.getPhonenumber()=="")
        {
            returnObject = new RegisterPhone_Response("please enter phonenumber");
            mListener.onFailure(returnObject);
        }//return new RegisterPhone_Response("please enter phonenumber");
        if(data.getToken().equals(""))
        {
            returnObject = new RegisterPhone_Response("please enter valid token");
            mListener.onFailure(returnObject);
        }


        final ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("phone", data.getPhonenumber()));
        param.add(new BasicNameValuePair("token", data.getToken()));



       // String call_response = "";//CallService();

        JSONObject jobj = null;
        RegisterPhone_Response response_data  = new RegisterPhone_Response();

        new AsyncClass(ApiClient.BasePath + "register_phone", "POST", param, new InternalApiListener()
        {
            @Override
            public void onSuccess(JSONObject jobj)
            {
                RegisterPhone_Response response_data  = new RegisterPhone_Response();
                //jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"register_phone","POST",param);
                if(jobj == null)
                {
                    response_data = new RegisterPhone_Response("Something wrong ");
                }
                else
                {
                    try
                    {
                        if (jobj.getString("status").equals("ok"))
                        {response_data.setStatus(true);
                            response_data.setMsg(jobj.getString("msg"));
                            response_data.setPhone(jobj.getString("phone"));

                            returnObject = response_data;
                            mListener.onSuccess(returnObject);
                        }
                        else
                        {
                            response_data.setStatus(false);
                            response_data.setMsg(jobj.getString("msg"));

                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        //mListener.onFailure(new RegisterPhone_Response("please enter valid token"));
                    }

                }
                returnObject = response_data;
                mListener.onFailure(returnObject);
            }

            @Override
            public void onError(JSONObject jdata)
            {
                mListener.onFailure(new RegisterPhone_Response("Something wrong "));
            }
        }).execute();

       // asyncClass.execute();


        /*
        recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath + "register_phone", "POST", param, new InternalApiListener()
        {
            @Override
            public void onSuccess(JSONObject jobj)
            {
                RegisterPhone_Response response_data  = new RegisterPhone_Response();
                //jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"register_phone","POST",param);
                if(jobj == null)
                {
                    response_data = new RegisterPhone_Response("Something wrong ");
                }
                else
                {
                    try
                    {
                        if (jobj.getString("status").equals("ok"))
                        {response_data.setStatus(true);
                            response_data.setMsg(jobj.getString("msg"));
                            response_data.setPhone(jobj.getString("phone"));

                            returnObject = response_data;
                            mListener.onSuccess(returnObject);
                        }
                        else
                        {
                            response_data.setStatus(false);
                            response_data.setMsg(jobj.getString("msg"));

                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        //mListener.onFailure(new RegisterPhone_Response("please enter valid token"));
                    }

                }
                returnObject = response_data;
                mListener.onFailure(returnObject);
            }

            @Override
            public void onError(JSONObject jdata)
            {
                mListener.onFailure(new RegisterPhone_Response("Something wrong "));
            }
        });


        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"register_phone","POST",param);
        if(jobj == null)
        {
            response_data = new RegisterPhone_Response("Something wrong ");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {response_data.setStatus(true);
                    response_data.setMsg(jobj.getString("msg"));
                    response_data.setPhone(jobj.getString("phone"));

                    returnObject = response_data;

                    mListener.onSuccess(returnObject);
                }
                else
                {
                    response_data.setStatus(false);
                    response_data.setMsg(jobj.getString("msg"));

                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                //mListener.onFailure(new RegisterPhone_Response("please enter valid token"));
            }

        }
        */
        returnObject = response_data;
        mListener.onFailure(returnObject);
       // return  response_data;
    }

    public RegisterPhone_Response ResponseData()
    {
        return returnObject;
    }
}
