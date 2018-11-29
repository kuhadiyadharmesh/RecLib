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
import com.recordapi.client.model.File.RecoverFile;
import com.recordapi.client.model.File.RecoverFile_Response;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

//if folder_id not provided then file will be recovered to its original folder
public class RecoverFileAPI
{
    private RecoverFile data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;


    public RecoverFileAPI(Context c, RecoverFile data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        RecoveryFileCall();
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

        RecoverFile_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new RecoverFile_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new RecoverFile_Response(true,jobj.getString(C_constant.msg));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new RecoverFile_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new RecoverFile_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }
    public void RecoveryFileCall()
    {
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new RecoverFile_Response("Please set ApiKey"));
        if(data.getFile_id().equals(""))
            mListener.onFailure(new RecoverFile_Response(C_constant.v_fileid_validation));
        if(data.getFolder_id().equals(""))
            mListener.onFailure(new RecoverFile_Response(C_constant.v_folderid_validation));

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id,data.getFile_id()));
        param.add(new BasicNameValuePair(C_constant.folder_id,data.getFolder_id()));

        webservice_call.handleRequest(1,ApiClient.recover_file,param,"POST");

    }
}
