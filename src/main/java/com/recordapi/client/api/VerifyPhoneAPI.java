package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
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
    private RecordingApi recordingApi;
    private VerifyPhone data ;

    public VerifyPhoneAPI(VerifyPhone data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public  VerifyPhone_Response  VerifyPhoneCall()
    {
        //VerifyPhone_Response response_data = new VerifyPhone_Response("server not response");

        // Validation
        if(data.getPhone().equals(""))
            return new VerifyPhone_Response("Please Enter Phone number");;
        if(data.getCode().equals(""))
            return new VerifyPhone_Response("Please Enter Code");
        if(data.getMacc().equals(""))
            return new VerifyPhone_Response("Please Enter MCC");;
        if(data.getApp().equals(""))
            return new VerifyPhone_Response("Please Enter App Free or Paid");;
        if(data.getToken().equals(""))
            return new VerifyPhone_Response("Please Enter Token");;
        if(data.getDevice_token().equals(""))
            return new VerifyPhone_Response("Please Enter Notification token");;
        if(data.getDevice_type().equals(""))
            return new VerifyPhone_Response("Please Enter Device Type");;

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
    }
}
