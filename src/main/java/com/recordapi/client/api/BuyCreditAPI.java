package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.BuyCredit;
import com.recordapi.client.model.BuyCredit_Response;
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
    private RecordingApi recordingApi;

    public BuyCreditAPI(BuyCredit data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }

    public BuyCredit_Response BuyCreditCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //Validation
        if(data.getApi_key().equals(""))
            return new BuyCredit_Response("Please set ApiKey");
        if(data.getAmount().equals(""))
            return new BuyCredit_Response("Please set Amount");
        if(data.getReciept().equals(""))
            return new BuyCredit_Response("Please set Reciept");
        if(data.getProduct_id()!="")
        {
            param.add(new BasicNameValuePair("product_id",data.getProduct_id()));
            param.add(new BasicNameValuePair("device_type",data.getDevice_type()));
//            param.add(new )
        }
            //return new UpdateProfilePicure_Response("Please select file");

        // Set parameter

//        param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair("api_key",data.getApi_key()));
        param.add(new BasicNameValuePair("amount",data.getAmount()));
        param.add(new BasicNameValuePair("reciept",data.getReciept()));
        //param.add(new BasicNameValuePair("data",data.getData()));

        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"buy_credits","POST",param);
        BuyCredit_Response response_data  = null;

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
                    return response_data;
                }
                else
                {
                    response_data = new BuyCredit_Response(jobj.getString("msg"));
                    return  response_data;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }
        return  response_data;

    }
}
