package com.recordapi.client.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.FileData;
import com.recordapi.client.model.Common.FolderData;
import com.recordapi.client.model.File.GetFiles;
import com.recordapi.client.model.File.GetFiles_Response;
import com.recordapi.client.model.Folder.GetFolder_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class GetFilesAPI
{

    private GetFiles data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;

    public GetFilesAPI(GetFiles data)
    {
        this.data = data ;
        this.mListener = mListener;
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetFileCall();
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

    public void GetFileCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

        //Validation
        if(data.getApi_key().equals(""))
            mListener.onFailure(new GetFiles_Response("Plese set ApiKey"));
        if (data.getFolder_id()!="")
            param.add(new BasicNameValuePair("folder_id", data.getFolder_id()));
        if (data.getPage()!="")
            param.add(new BasicNameValuePair("page", data.getPage()));
        if(data.getPass()!="")
            param.add(new BasicNameValuePair("pass", data.getPass()));
        if(data.getSource()!="")
            param.add(new BasicNameValuePair("source", data.getSource()));
        if (data.getReminder())
            param.add(new BasicNameValuePair("reminder", ""+data.getReminder()));
        if (data.getSearch_text()!="")
            param.add(new BasicNameValuePair("q", data.getSearch_text()));

        param.add(new BasicNameValuePair("api_key", data.getApi_key()));


        webservice_call.handleRequest(1,ApiClient.BasePath+"get_files",param,"POST");
/*
        JSONObject jobj = null;
        GetFiles_Response response_data  = null;//new GetFolder_Response();

        jobj =  recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"get_files","POST",param);
        if(jobj == null)
        {
            response_data = new GetFiles_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    JSONArray jar = jobj.getJSONArray("files");
                    ArrayList<FileData> fdata = new ArrayList<>();
                    FileData fo = null;
                    for (int i = 0; i < jar.length(); i++)
                    {
                        JSONObject jo = jar.getJSONObject(i);//jo.getString("access_number")


                       fo = new FileData(jo.getString("id"), jo.getString("order_id"),jo.getString("sid"),jo.getString("name"),jo.getString("f_name"),jo.getString("l_name"),jo.getString("email"),jo.getString("phone"),jo.getString("notes"),jo.getString("source"),jo.getString("url"),jo.getString("duration"),jo.getString("time"),jo.getString("share_url"),jo.getString("download_url"),jo.getString("is_star"), data.getReminder() == true ?jo.getString("remind_days"):"",data.getReminder() ==true?jo.getString("remind_date"):"");
                        fdata.add(fo);
                    }
                    response_data = new GetFiles_Response("File List Available.",fdata,""+jobj.getInt("credits"));
                    return response_data;
                }
                else
                {
//                    response_data.setStatus(false);
//                    response_data.setMsg(jobj.getString("msg"));
                    response_data = new GetFiles_Response(jobj.getString("msg"));

                    return  response_data;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }
        return  response_data;*/
    }


}
