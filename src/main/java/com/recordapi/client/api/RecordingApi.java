package com.recordapi.client.api;

import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecApi;
import com.recordapi.client.model.RegisterPhone;
import com.recordapi.client.model.RegisterPhone_Response;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okio.BufferedSink;

/**
 * Created by Dharmesh-PC on 1/5/2018.
 */

public class RecordingApi
{
    public RegisterPhone data ;
    HttpUrl.Builder urlBuilder;
    RequestBody requestBody;

    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/x-www-form-urlencoded");
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
    public RegisterPhone_Response RegisterPhoneCall(int i)
    {
        urlBuilder = HttpUrl.parse(ApiClient.BasePath+"register_phone/").newBuilder();
        //urlBuilder.addQueryParameter("phone", data.getPhonenumber());
       // urlBuilder.addQueryParameter("token", data.getToken());


        if(i == 1 )
        {
            //FormEncodingBuilder builder = new FormEncodingBuilder();
            //builder.addEncoded("phone",data.getPhonenumber());
            //builder.addEncoded("token",data.getToken());
            String dd = "phone:"+data.getPhonenumber()+" token"+data.getToken();

            //MultipartBuilder body = new MultipartBuilder();
            requestBody = RequestBody.create(MEDIA_TYPE,dd.toString());
        }
        else
        {
            urlBuilder.setEncodedQueryParameter("phone", data.getPhonenumber());
            urlBuilder.setEncodedQueryParameter("token", data.getToken());
        }

       // RequestBody requestBody = RequestBody.create().


        //body.addPart("phone",new StringBod);

//        requestBody = new Request.Builder()
//                .setType(MediaType.parse("application/x-www-form-urlencoded"))
//                .addFormDataPart("somParam", "someValue")
//                .build();


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

        //RequestBody requestBody = Req

        Request request = new Request.Builder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .url(url)
                .build();
       // Request request = MakeRequest();
        ApiClient client = new ApiClient();
        String s = client.RegisterPhone(request);
        Log.e("response ","response :-"+s);
        return  s;
    }
}
