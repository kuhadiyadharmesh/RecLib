package com.recordapi.client.api;

import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecApi;
import com.recordapi.client.model.RegisterPhone;
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
    public RecordingApi(RegisterPhone data)
    {
        this.data = data ;
    }
    public boolean ValidateParameters()
    {
        if (data.getPhonenumber()=="")
            return false;
        if(data.getToken().equals(""))
            return false;
        else
            return true;
    }

    public Request MakeRequest()
    {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(ApiClient.BasePath+"register_phone/").newBuilder();
        urlBuilder.addQueryParameter("phone", data.getPhonenumber());
        urlBuilder.addQueryParameter("token", data.getToken());
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        return  request;
    }
    public void CallService()
    {
        Request request = MakeRequest();
        ApiClient client = new ApiClient();
        String s = client.RegisterPhone(request);
        Log.e("response ","response :-"+s);
        JSONObject jobj = null;
        //{"status":"ok","phone":"+16463742122","code":"54004","msg":"Verification Code Sent"}
        try
        {
            jobj = new JSONObject(s);

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
            try {
                if (jobj.getString("status").equals("ok"))
                {

                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }




    }
}
