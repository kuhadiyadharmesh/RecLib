package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Folder.VerifyFolderPassword;
import com.recordapi.client.model.Folder.VerifyFolderPassword_Response;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.StartUnStarFolderFile;
import com.recordapi.client.model.StartUnStartFolderFile_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class StartUnStartFileFolderAPI
{
    private StartUnStarFolderFile data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;


    public StartUnStartFileFolderAPI(StartUnStarFolderFile data,RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        StartUnStartFileFolderCall();
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

    public void StartUnStartFileFolderCall()
    {
        // Validation
        if (data.getApi_key().equals(""))
            mListener.onFailure( new StartUnStartFolderFile_Response("Please enter api key"));
        if(data.getFolder_id().equals(""))
            mListener.onFailure( new StartUnStartFolderFile_Response("Please set folder Id or file Id"));
        if (data.getStar().equals(""))
            mListener.onFailure( new StartUnStartFolderFile_Response("Please Set Start or UnStart"));
        if (data.getType().equals(""))
            mListener.onFailure( new StartUnStartFolderFile_Response("Please Set File or Folder as type"));


        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getFolder_id()));
        param.add(new BasicNameValuePair("star",data.getStar()));
        param.add(new BasicNameValuePair("type",data.getType()));

        webservice_call.handleRequest(1,ApiClient.BasePath+"update_star",param,"POST");


    }
}
