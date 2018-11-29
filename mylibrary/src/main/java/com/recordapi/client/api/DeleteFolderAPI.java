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
import com.recordapi.client.model.Folder.DeleteFolder;
import com.recordapi.client.model.Folder.DeleteFolder_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class DeleteFolderAPI
{

    private DeleteFolder data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;

    public DeleteFolderAPI(Context c, DeleteFolder data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        DeleteFolderCall();
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

        DeleteFolder_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new DeleteFolder_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new DeleteFolder_Response(true,jobj.getString(C_constant.msg));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new DeleteFolder_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new DeleteFolder_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }
        /*
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
        mListener.onFailure(response_data);*/

    }
    public void DeleteFolderCall()
    {
        // Validation
//        if (data.getApi_key().equals(""))
//            mListener.onFailure(new DeleteFolder_Response("Please enter api key"));
        if(data.getFolder_id().equals(""))
            mListener.onFailure(  new DeleteFolder_Response(C_constant.v_setfolder_id_validation));
        if (data.getMove_to().equals(""))
            mListener.onFailure(  new DeleteFolder_Response(C_constant.v_move_to_validation));

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id,data.getFolder_id()));
        param.add(new BasicNameValuePair(C_constant.move_to,data.getMove_to()));


        webservice_call.handleRequest(1,ApiClient.delete_folder,param,"POST");

    }

}
