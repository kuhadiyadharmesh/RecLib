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
    private SaveData sd;

    public UpdateOrderFolderAPI(Context c, UpdateOrderData data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
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
        JSONObject jobj = (JSONObject) obj;

        Common_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new Common_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new Common_Response(true,jobj.getString(C_constant.msg));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new Common_Response(jobj.getString(C_constant.msg));
//                    response_data = new Common_Response("Something Wrong");
                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new Common_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }
    public void UpdateOrderFolderCall()
    {
        //Validation
//        if(data.getApikey().equals(""))
//            mListener.onFailure(new Common_Response("Please set ApiKey"));
        if(data.getId()== 0)
            mListener.onFailure(new Common_Response(C_constant.v_setfolder_id_validation));
//        if(data.getTop_Id()==0)
//            return new Common_Response("Please set Index position .");
//        if(data.getDevice_type().equals(""))
//            return new UpdateFolderOrder_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id,""+data.getId()));
        //param.add(new BasicNameValuePair("index",""+data.getIndex()));
        param.add(new BasicNameValuePair(C_constant.top_id,""+data.getTop_Id()));
        param.add(new BasicNameValuePair(C_constant.type,"folder"));


        webservice_call.handleRequest(1,ApiClient.update_order,param,"POST");


    }
}
