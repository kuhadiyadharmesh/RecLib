package com.recordapi.client.api;

import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecApi;
import com.recordapi.client.model.RegisterPhone;
import com.recordapi.client.model.RegisterPhone_Response;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dharmesh-PC on 1/5/2018.
 */

public class RecordingApi
{
    public RegisterPhone data ;
    HttpUrl.Builder urlBuilder;
    public RecordingApi(RegisterPhone data)
    {
        this.data = data ;
    }



//    public Request MakeRequest()
//    {
//
//
//        return  request;
//    }
    public RegisterPhone_Response RegisterPhoneCall()
    {
        urlBuilder = HttpUrl.parse(ApiClient.BasePath+"register_phone/").newBuilder();
        urlBuilder.addQueryParameter("phone", data.getPhonenumber());
        urlBuilder.addQueryParameter("token", data.getToken());
       // RegisterPhone_Response response_data = new RegisterPhone_Response();


        if (data.getPhonenumber()=="")
            return new RegisterPhone_Response("please enter phonenumber");
        if(data.getToken().equals(""))
            return new RegisterPhone_Response("please enter valid token");

        String call_response = CallService();

        JSONObject jobj = null;
        RegisterPhone_Response response_data  = new RegisterPhone_Response();
        try
        {
            jobj = new JSONObject(call_response);
        }
        catch (JSONException jo)
        {
            jo.printStackTrace();
        }
        if(jobj == null)
        {

        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data.setStatus(true);
                    //data.setMsg(jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data.setStatus(false);
                    response_data.setMsg(jobj.getString("msg"));
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


    public String CallService()
    {
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
       // Request request = MakeRequest();
        ApiClient client = new ApiClient();
        String s = client.RegisterPhone(request);
        Log.e("response ","response :-"+s);
        return  s;
    }
}
