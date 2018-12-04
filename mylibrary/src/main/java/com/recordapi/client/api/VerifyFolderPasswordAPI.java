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
import com.recordapi.client.model.Folder.UpdateFolder;
import com.recordapi.client.model.Folder.UpdateFolder_Response;
import com.recordapi.client.model.Folder.VerifyFolderPassword;
import com.recordapi.client.model.Folder.VerifyFolderPassword_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class VerifyFolderPasswordAPI
{
    private VerifyFolderPassword data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet;


    public VerifyFolderPasswordAPI(Context c, VerifyFolderPassword data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        VerifyFolderPasswordCall();
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

        VerifyFolderPassword_Response  response_data ;// = new VerifyFolderPassword_Response ();

        if(response == null)
        {
            response_data = new VerifyFolderPassword_Response (C_constant.wrong_message);
        }
        else
        {
            try
            {
                if (response.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new VerifyFolderPassword_Response(true,response.getString("msg"));
//                    response_data.setStatus(true);
//                    response_data.setMsg(response.getString(C_constant.msg));
//                    response_data.setPhone(response.getString(C_constant.phone));

                    //returnObject = response_data;
                    mListener.onSuccess(response_data);
                }
                else
                {
                    //response_data.setStatus(false);
                    response_data = new VerifyFolderPassword_Response (response.getString(C_constant.msg));

                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                //mListener.onFailure(new RegisterPhone_Response("please enter valid token"));
                response_data = new VerifyFolderPassword_Response (C_constant.wrong_message);
            }

        }
        //returnObject = response_data;
        mListener.onFailure(response_data);

    }

    public void VerifyFolderPasswordCall()
    {
        // Validation
//        if (data.getApi_key().equals(""))
//            mListener.onFailure( new VerifyFolderPassword_Response("Please enter api key"));
        if(data.getFolder_id().equals(""))
            mListener.onFailure( new VerifyFolderPassword_Response(C_constant.v_setfolder_id_validation));
        if (data.getPassword().equals(""))
            mListener.onFailure( new VerifyFolderPassword_Response(C_constant.v_enterfilepassword_validation));

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id,data.getFolder_id()));
        param.add(new BasicNameValuePair(C_constant.pass,data.getPassword()));


        if(internet.check_internet())
        webservice_call.handleRequest(1,ApiClient.verify_folder_pass,param,"POST");
        else
            mListener.onFailure(new VerifyFolderPassword_Response(C_constant.no_Internet));

    }
}
