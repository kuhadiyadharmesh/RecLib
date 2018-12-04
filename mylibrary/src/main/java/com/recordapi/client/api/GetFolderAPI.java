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
import com.recordapi.client.database.SaveData;
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.Common.FolderData;
import com.recordapi.client.model.Folder.GetFolder;
import com.recordapi.client.model.Folder.GetFolder_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/9/2018.
 */

public class GetFolderAPI
{
    //{"status":"ok","folders":[{"id":"29","name":"test","created":"1433989312"},{"id":"31","name":"test3","created":"1434009445"}],"msg":"Success"}

    //private GetFolder data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet;

    public GetFolderAPI(Context c,RecordingApiListener mListener)
    {
        //this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetFolderCall();
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

    private void handleEvent(int what, Object obj) throws JSONException {
        // TODO Auto-generated method stub
        Log.e("Event ", "response : " + obj.toString());
        JSONObject jobj = (JSONObject) obj;

        GetFolder_Response response_data = null ;//= new GetFolder_Response();

        if (jobj == null) {
            response_data = new GetFolder_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
            {
            try {
                if (jobj.getString(C_constant.status).equals(C_constant.ok)) {
                    sd.setFolders_JSON(jobj.toString());
                    JSONArray jar = jobj.getJSONArray(C_constant.folders);
                    ArrayList<FolderData> fdata = new ArrayList<>();
                    FolderData fo = null;
                    for (int i = 0; i < jar.length(); i++) {
                        JSONObject jo = jar.getJSONObject(i);
                        fo = new FolderData(jo.getString(C_constant.id), jo.getString(C_constant.name), jo.getString(C_constant.created));
                        fdata.add(fo);
                    }
                    response_data = new GetFolder_Response(true, jobj.getString(C_constant.msg), fdata);
                    mListener.onSuccess(response_data);
                } else {
//                    response_data.setStatus(false);
//                    response_data.setMsg(jobj.getString("msg"));
                    response_data = new GetFolder_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                    // return  response_data;
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
                response_data = new GetFolder_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }
         }

    }

    public void GetFolderCall()
    {
        // validation
//        if (data.getApi_key().equals(""))
//            mListener.onFailure(new GetFolder_Response("Please set Api_key"));

        // set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.api_key, sd.getToken()));

        //JSONObject jobj = null;
        //GetFolder_Response response_data  = null;//new GetFolder_Response();

        if(internet.check_internet() && sd.getFolders_JSON().length()==0)
        webservice_call.handleRequest(1,ApiClient.get_folders,param,"POST");
        else
        {
            try {
                JSONObject jobj = new JSONObject(sd.getFolders_JSON());
                JSONArray jar = jobj.getJSONArray(C_constant.folders);
                ArrayList<FolderData> fdata = new ArrayList<>();
                FolderData fo = null;
                for (int i = 0; i < jar.length(); i++) {
                    JSONObject jo = jar.getJSONObject(i);
                    fo = new FolderData(jo.getString(C_constant.id), jo.getString(C_constant.name), jo.getString(C_constant.created));
                    fdata.add(fo);
                }
               // response_data = ;
                mListener.onSuccess(new GetFolder_Response(true, jobj.getString(C_constant.msg), fdata));
            } catch (JSONException e) {
                e.printStackTrace();
                mListener.onFailure(new GetFolder_Response(C_constant.no_Internet));
            }
        }
    }


}
