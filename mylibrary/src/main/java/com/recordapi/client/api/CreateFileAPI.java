package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.Folder.CreateFolder_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class CreateFileAPI
{
    private CreateFile data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;

    public CreateFileAPI(CreateFile data)
    {
        this.data = data ;
        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        CreateFileCall();
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
    public void CreateFileCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        //Validation
        if(data.getApi_key().equals(""))
            mListener.onFailure(new CreateFile_Response("Please set ApiKey"));
        if(data.getFile().equals(""))
            mListener.onFailure(new CreateFile_Response("Please select file"));
        //if(data.getId().equals())
       // if(data.getData().equals(""))
      //      return new CreateFile_Response("Please Enter folder name and notes");
       // if (data.getRemind_days().equals(""))
        //    return new CreateFile_Response("Please Enter RemindDays.");
        //if (data.getRemind_date()!="")

        //if (data.getRemind_date()!="")
        //    param.add(new BasicNameValuePair("data",data.getData()));

        // Set parameter
       // ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("id",data.getId()));
        param.add(new BasicNameValuePair("data",data.getData()));

        webservice_call.handleRequest(1,ApiClient.BasePath+"create_file",param,"POST");
/*

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"create_file","POST",param);
        CreateFile_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new CreateFile_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new CreateFile_Response(jobj.getString("msg"),jobj.getString("id"));
                    return response_data;
                }
                else
                {
                    response_data = new CreateFile_Response(jobj.getString("msg"));
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
