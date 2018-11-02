package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
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
    private GetPhoneNumber data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;

    public GetPhoneNumberAPI(GetPhoneNumber data)
    {
        this.data = data ;
        this.mListener = mListener;
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

    public void GetPhoneNumberCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            mListener.onFailure(new GetPhoneNumber_Response("Please set ApiKey"));
//        if(data.getDevice_token().equals(""))
//            return new GetPhoneNumber_Response("Please set Device Token");
//        if(data.getDevice_type().equals(""))
//            return new GetPhoneNumber_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
//        param.add(new BasicNameValuePair("device_token",data.getDevice_token()));
//        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));

        //recordingApi.makeHttpRequestFor_SSL_Array(ApiClient.BasePath+"get_phones","POST",param , new Ine);

        webservice_call.handleRequest(1,ApiClient.BasePath+"get_phones",param,"POST");
    /*
        JSONArray jobj = null ;

        jobj = recordingApi.makeHttpRequestFor_SSL_Array(ApiClient.BasePath+"get_phones","POST",param);
        GetPhoneNumber_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new GetPhoneNumber_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.length() > 0)
                {
                    JSONObject jo = null;
                    ArrayList<NumberData> data = new ArrayList<>();
                    for(int i = 0 ; i <jobj.length() ; i++)
                    {
                        jo = jobj.getJSONObject(i);

                        data.add(new NumberData(jo.getString("phone_number"),jo.getString("number"),jo.getString("prefix"),jo.getString("friendly_name"),jo.getString("flag"),jo.getString("country")));
                    }

                    response_data = new GetPhoneNumber_Response("data get successfully!!",data);
                    return response_data;
                }
                else
                {
                    response_data = new GetPhoneNumber_Response("Something Problem");
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
