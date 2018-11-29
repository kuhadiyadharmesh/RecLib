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
import com.recordapi.client.model.BuyCredit;
import com.recordapi.client.model.BuyCredit_Response;
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class BuyCreditAPI
{
    private BuyCredit data ;
    //private RecordingApi recordingApi;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;

    public BuyCreditAPI(Context c, BuyCredit data , RecordingApiListener mListener)
    {
        this.data = data ;
        //recordingApi = new RecordingApi();
        sd = new SaveData(c);
        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        BuyCreditCall();
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

        BuyCredit_Response response_data  = null;//new RegisterPhone_Response();
        if(jobj == null)
        {
            response_data = new BuyCredit_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    //Credits","code":"amount_added","credits_added":300,"credits":8550,"credits_trans":300,"rem_expiry":null
                    response_data = new BuyCredit_Response(jobj.getString("msg"),jobj.getString("credits_added"),jobj.getString("credits"),jobj.getString("credits_trans"),jobj.getString("rem_expiry"));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new BuyCredit_Response(jobj.getString("msg"));
                   // return  response_data;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new BuyCredit_Response("Something Wrong");
            }

        }
       // return  response_data;
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

        }*/
        //returnObject = response_data;
        mListener.onFailure(response_data);

    }

    public void BuyCreditCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new BuyCredit_Response("Please set ApiKey"));
        if(data.getAmount().equals(""))
            mListener.onFailure( new BuyCredit_Response(C_constant.v_amount_validation));
        if(data.getReciept().equals(""))
            mListener.onFailure( new BuyCredit_Response(C_constant.v_receipt_validation));
        if(data.getProduct_id()!="")
        {
            param.add(new BasicNameValuePair(C_constant.product_id,data.getProduct_id()));
            param.add(new BasicNameValuePair(C_constant.device_type,data.getDevice_type()));
//            param.add(new )
        }
            //return new UpdateProfilePicure_Response("Please select file");

        // Set parameter

//        param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.amount,data.getAmount()));
        param.add(new BasicNameValuePair(C_constant.receipt,data.getReciept()));
        //param.add(new BasicNameValuePair("data",data.getData()));


        webservice_call.handleRequest(1,ApiClient.buy_credits,param,"POST");


    }
}
