package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.GetTranslations;
import com.recordapi.client.model.Setting.GetTranslations_Response;
import com.recordapi.client.model.Setting.UpdateDeviceToken;
import com.recordapi.client.model.Setting.UpdateDeviceToken_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetTranslationsAPI
{
    private GetTranslations data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;

    public GetTranslationsAPI(GetTranslations data)
    {
        this.data = data ;
        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetTranslationsCall();
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
    public void GetTranslationsCall()
    {
        //Validation
        if(data.getApi_key().equals(""))
            mListener.onFailure(new GetTranslations_Response("Please set ApiKey"));
        if(data.getLanguage().equals(""))
            mListener.onFailure(new GetTranslations_Response("Please set Language en-us or en-uk"));
//        if(data.getDevice_type().equals(""))
//            return new UpdateDeviceToken_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("language",data.getLanguage()));
//        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        webservice_call.handleRequest(1,ApiClient.BasePath+"get_translations",param,"POST");

        /*
        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_translations","POST",param);
        GetTranslations_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new GetTranslations_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new GetTranslations_Response("Translations get successfully.",jobj.getJSONObject("translation").getString("Trash"),jobj.getJSONObject("translation").getString("All Files"),jobj.getJSONObject("translation").toString());
                    return response_data;
                }
                else
                {
                    response_data = new GetTranslations_Response(jobj.getString("msg"));
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
