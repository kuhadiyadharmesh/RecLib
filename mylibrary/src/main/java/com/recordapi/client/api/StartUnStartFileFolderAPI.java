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
    private SaveData sd;
    private InternetConnection internet ;



    public StartUnStartFileFolderAPI(Context c, StartUnStarFolderFile data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
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
        JSONObject jobj = (JSONObject) obj;

        StartUnStartFolderFile_Response response_data ;// = new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new StartUnStartFolderFile_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new StartUnStartFolderFile_Response(true,jobj.getString(C_constant.msg));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new StartUnStartFolderFile_Response(jobj.getString(C_constant.msg));
                    //response_data = new StartUnStartFolderFile_Response("Something Wrong");
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new StartUnStartFolderFile_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }

    public void StartUnStartFileFolderCall()
    {
        // Validation
//        if (data.getApi_key().equals(""))
//            mListener.onFailure( new StartUnStartFolderFile_Response("Please enter api key"));
        if(data.getFolder_id().equals(""))
            mListener.onFailure( new StartUnStartFolderFile_Response(C_constant.v_selectfile_delete_validation));
        if (data.getStar().equals(""))
            mListener.onFailure( new StartUnStartFolderFile_Response(C_constant.v_select_start_unstart_validation));
        if (data.getType().equals(""))
            mListener.onFailure( new StartUnStartFolderFile_Response(C_constant.v_set_file_folder_validation));


        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id,data.getFolder_id()));
        param.add(new BasicNameValuePair(C_constant.star,data.getStar()));
        param.add(new BasicNameValuePair(C_constant.type,data.getType()));

        if(internet.check_internet())
        webservice_call.handleRequest(1,ApiClient.update_star,param,"POST");
        else
            mListener.onFailure(new StartUnStartFolderFile_Response(C_constant.no_Internet));

    }
}
