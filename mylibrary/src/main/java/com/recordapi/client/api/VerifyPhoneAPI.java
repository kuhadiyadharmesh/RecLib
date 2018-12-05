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
import com.recordapi.client.database.Records_Data;
import com.recordapi.client.database.SaveData;
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.VerifyPhone;
import com.recordapi.client.model.VerifyPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class VerifyPhoneAPI
{
    //private RecordingApi recordingApi;
    private VerifyPhone data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    public Handler uiHandler;
    private InternetConnection internet ;
    SaveData sd ;
    private Context c ;

    public VerifyPhoneAPI( Context c ,VerifyPhone data, RecordingApiListener mListener )
    {
        this.data = data ;

        this.mListener = mListener;
        this.c = c;
        this.internet = new InternetConnection(c);
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        VerifyPhoneCall();
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

        VerifyPhone_Response response_data  ;//= new RegisterPhone_Response();

        if(response == null)
        {
            response_data = new VerifyPhone_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (response.getString(C_constant.status).equals(C_constant.ok))
                {


                    response_data =  new VerifyPhone_Response(true , response.getString(C_constant.phone),response.getString(C_constant.msg));

                    sd.setToken(response.getString(C_constant.api_key));
                    sd.setPhone(response.getString(C_constant.phone));
                    new Runnable(){
                        @Override
                        public void run() {
                            new Records_Data(c).Call_All_Service();
                        }
                    }.run();

                    mListener.onSuccess(response_data);
                }
                else
                {
                     response_data  = new VerifyPhone_Response(response.getString(C_constant.msg));
                    mListener.onFailure(response_data);

                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new VerifyPhone_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
                //mListener.onFailure(new RegisterPhone_Response("please enter valid token"));
            }

        }
        //returnObject = response_data;
        //mListener.onFailure(response_data);

    }

    public  void  VerifyPhoneCall()
    {
        //VerifyPhone_Response response_data = new VerifyPhone_Response("server not response");

        // Validation
        if(data.getPhone().equals(""))
            mListener.onFailure(new VerifyPhone_Response(C_constant.v_phone_validation));///return new VerifyPhone_Response("Please Enter Phone number");;
        if(data.getCode().equals(""))
            mListener.onFailure(new VerifyPhone_Response(C_constant.v_code_validation));
        if(data.getMacc().equals(""))
            mListener.onFailure(new VerifyPhone_Response(C_constant.v_setmcc_validation));;
        if(data.getApp().equals(""))
            mListener.onFailure(new VerifyPhone_Response(C_constant.v_setappfreeorpaid_validation));;
        /*if(data.getToken().equals(""))
            mListener.onFailure( new VerifyPhone_Response("Please Enter Token"));;*/
        if(data.getDevice_token().equals(""))
            mListener.onFailure(new VerifyPhone_Response(C_constant.v_setnotitoken_validation));;
            /*
        if(data.getDevice_type().equals(""))
            mListener.onFailure(new VerifyPhone_Response("Please Enter Device Type"));;*/

        // Setting
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.phone,data.getPhone()));
        param.add(new BasicNameValuePair(C_constant.code,data.getCode()));
        param.add(new BasicNameValuePair(C_constant.mcc,data.getMacc()));
        param.add(new BasicNameValuePair(C_constant.app,data.getApp()));
        param.add(new BasicNameValuePair(C_constant.token,C_constant.s_token));
        param.add(new BasicNameValuePair(C_constant.device_token,data.getDevice_token()));
        param.add(new BasicNameValuePair(C_constant.device_type,"android"));
        param.add(new BasicNameValuePair(C_constant.time_zone,data.getTime_zone()));
        param.add(new BasicNameValuePair(C_constant.device_id,data.getDeviceId()));

        JSONObject jobj = null;
        VerifyPhone_Response response_data  = null;


        if(internet.check_internet())
        webservice_call.handleRequest(1 ,ApiClient.verify_phone, param,"POST");
        else
        mListener.onFailure( new RegisterPhone_Response(C_constant.no_Internet));

    }
}
