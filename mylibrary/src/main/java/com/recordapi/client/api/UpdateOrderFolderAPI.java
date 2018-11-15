package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.Common_Response;
import com.recordapi.client.model.Common.UpdateOrderData;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 5/8/2018.
 */

public class UpdateOrderFolderAPI
{
    private UpdateOrderData data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;

    public UpdateOrderFolderAPI(UpdateOrderData data)
    {
        this.data = data ;
        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        UpdateOrderFolderCall();
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
    public void UpdateOrderFolderCall()
    {
        //Validation
        if(data.getApikey().equals(""))
            mListener.onFailure(new Common_Response("Please set ApiKey"));
        if(data.getId()== 0)
            mListener.onFailure(new Common_Response("Please set file/folder id ."));
//        if(data.getTop_Id()==0)
//            return new Common_Response("Please set Index position .");
//        if(data.getDevice_type().equals(""))
//            return new UpdateFolderOrder_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApikey()));
        param.add(new BasicNameValuePair("id",""+data.getId()));
        //param.add(new BasicNameValuePair("index",""+data.getIndex()));
        param.add(new BasicNameValuePair("top_id",""+data.getTop_Id()));
        param.add(new BasicNameValuePair("type","folder"));


        webservice_call.handleRequest(1,ApiClient.BasePath+"update_order",param,"POST");


    }
}
