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
import com.recordapi.client.model.File.CloneFile;
import com.recordapi.client.model.File.CloneFile_Response;
import com.recordapi.client.model.File.DeleteFile;
import com.recordapi.client.model.File.DeleteFile_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class CloneFileAPI
{
    //action=remove_forever
    private CloneFile data ;
    //private RecordingApi recordingApi;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;

    public CloneFileAPI(Context c, CloneFile data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        CloneFileCall();
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

        CloneFile_Response response_data  ; //new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new CloneFile_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new CloneFile_Response(true,jobj.getString(C_constant.msg),jobj.getString(C_constant.id));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new CloneFile_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {

                e.printStackTrace();
                response_data = new CloneFile_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }
        /*
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

        }*/
        //returnObject = response_data;


    }

    public void CloneFileCall()
    {


//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new CloneFile_Response("Please set Api Key "));
        if(data.getFile_ids().equals(""))
            mListener.onFailure(new CloneFile_Response(C_constant.v_fileselect_validation));

        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id,data.getFile_ids()));

        webservice_call.handleRequest(1,ApiClient.clone_file,param,"POST");


    }

}
