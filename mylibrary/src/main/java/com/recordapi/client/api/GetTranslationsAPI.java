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
   // private GetTranslations data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet ;

    public GetTranslationsAPI(Context c, RecordingApiListener mListener)
    {
        //this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
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
        JSONObject jobj = (JSONObject) obj;

        GetTranslations_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new GetTranslations_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    sd.setTranslation_JSON(jobj.toString());
                    response_data = new GetTranslations_Response(C_constant.s_transactionget_successfully,jobj.getJSONObject(C_constant.translation).getString(C_constant.Trash),jobj.getJSONObject(C_constant.translation).getString(C_constant.All_Files),jobj.getJSONObject(C_constant.translation).toString());
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new GetTranslations_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new GetTranslations_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }
    public void GetTranslationsCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new GetTranslations_Response("Please set ApiKey"));
       // if(data.getLanguage().equals(""))
       //     mListener.onFailure(new GetTranslations_Response(C_constant.v_select_lenguage_validation));
//        if(data.getDevice_type().equals(""))
//            return new UpdateDeviceToken_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.language,C_constant.s_language_parameter));
//        param.add(new BasicNameValuePair("device_type",data.getDevice_type()));

        if(internet.check_internet() && sd.getTranslation_JSON().length() == 0)
        webservice_call.handleRequest(1,ApiClient.get_translations,param,"POST");
        else
        {
            try
            {
                JSONObject jobj = new JSONObject(sd.getTranslation_JSON());
                GetTranslations_Response response_data = new GetTranslations_Response(C_constant.s_transactionget_successfully,jobj.getJSONObject(C_constant.translation).getString(C_constant.Trash),jobj.getJSONObject(C_constant.translation).getString(C_constant.All_Files),jobj.getJSONObject(C_constant.translation).toString());
                mListener.onSuccess(response_data);

            } catch (JSONException e) {
                e.printStackTrace();
                mListener.onFailure(new GetTranslations_Response(C_constant.no_Internet));
            }
        }

    }
}
