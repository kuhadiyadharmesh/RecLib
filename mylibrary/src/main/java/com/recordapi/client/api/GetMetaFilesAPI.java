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
import com.recordapi.client.model.Common.FileData;
import com.recordapi.client.model.Common.MetaFileData;
import com.recordapi.client.model.File.GetFiles;
import com.recordapi.client.model.File.GetFiles_Response;
import com.recordapi.client.model.Meta.GetMetaFiles;
import com.recordapi.client.model.Meta.GetMetaFiles_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class GetMetaFilesAPI
{
    private GetMetaFiles data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd ;


    public GetMetaFilesAPI(Context c, GetMetaFiles data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetMetaFilesCall();
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

        GetMetaFiles_Response response_data  ;//= new RegisterPhone_Response();

        if(jobj == null)
        {
            response_data = new GetMetaFiles_Response("Something Wrong");
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    JSONArray jar = jobj.getJSONArray("meta_files");
                    ArrayList<MetaFileData> fdata = new ArrayList<>();
                    MetaFileData fo = null;
                    for (int i = 0; i < jar.length(); i++)
                    {
                        JSONObject jo = jar.getJSONObject(i);
                        fo = new MetaFileData(jo.getString("id"),jo.getString("name"),jo.getString("file"),jo.getString("parent_id"),jo.getString("user_id"),jo.getString("time"));
                        fdata.add(fo);
                    }
                    response_data = new GetMetaFiles_Response("Metafile get successfully .",fdata);
                    mListener.onSuccess(response_data);
                }
                else
                {
//                    response_data.setStatus(false);
//                    response_data.setMsg(jobj.getString("msg"));
                    response_data = new GetMetaFiles_Response(jobj.getString("msg"));

                    mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new GetMetaFiles_Response("Something Wrong");
                mListener.onFailure(response_data);
            }

        }
        //returnObject = response_data;
        mListener.onFailure(response_data);

    }
    public void GetMetaFilesCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new GetMetaFiles_Response("Plese set ApiKey"));
        if(data.getParent_id().equals(""))
            mListener.onFailure(new GetMetaFiles_Response("Plese set Parent Key"));


        param.add(new BasicNameValuePair("api_key", sd.getToken()));
        param.add(new BasicNameValuePair("parent_id", data.getParent_id()));

        webservice_call.handleRequest(1,ApiClient.BasePath+"get_meta_files",param,"POST");

    }
}
