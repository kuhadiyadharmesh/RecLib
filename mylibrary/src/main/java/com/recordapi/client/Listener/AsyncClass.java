package com.recordapi.client.Listener;

import android.os.AsyncTask;

import com.recordapi.client.RecordingApi;

import org.apache.http.NameValuePair;

import java.util.List;

public class AsyncClass extends AsyncTask<String, Void, String>
{
    String request_url = "",method = "" ;
    List<NameValuePair> param = null;
    InternalApiListener mListener = null;

    private RecordingApi recordingApi;

    public AsyncClass(String url, String method, List<NameValuePair> param, InternalApiListener mListener)
    {
        this.request_url= url ;
        this.method = method ;
        this.param = param;
        this.mListener = mListener;
        recordingApi = new RecordingApi();
    }



    @Override
    protected String doInBackground(String... req_par)
    {

        recordingApi.makeHttpRequestFor_SSL(request_url,method,param,mListener);

        return "";
    }

}




