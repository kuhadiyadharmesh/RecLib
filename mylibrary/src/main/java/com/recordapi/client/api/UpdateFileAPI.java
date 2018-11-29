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
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.File.UpdateFile;
import com.recordapi.client.model.File.UpdateFile_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/12/2018.
 */

public class UpdateFileAPI
{
    private UpdateFile data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;


    public UpdateFileAPI(Context c,UpdateFile data, RecordingApiListener mListener)
    {
        this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        UpdateFileCall();

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

        UpdateFile_Response response_data  ;//= new UpdateFile_Response();

        if(jobj == null)
        {
            response_data = new UpdateFile_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
        {
            try
            {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    response_data = new UpdateFile_Response(true,jobj.getString(C_constant.msg),jobj.getString(C_constant.id));
                    mListener.onSuccess(response_data);
                }
                else
                {
                    response_data = new UpdateFile_Response(jobj.getString(C_constant.msg));
                   mListener.onFailure(response_data);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                response_data = new UpdateFile_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }
    public void UpdateFileCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();

          if (data.getFileInfo().equals(null))
            mListener.onFailure(new UpdateFile_Response(C_constant.v_enterfileinfo_validation));
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new UpdateFile_Response("Please set Api Key"));
        if(data.getFileInfo().getId().equals(""))
            mListener.onFailure(new UpdateFile_Response(C_constant.v_setfolder_id_validation));

        if (data.getFileInfo().getF_name()!="")
        param.add(new BasicNameValuePair(C_constant.f_name,data.getFileInfo().getF_name()));
        //return new UpdateFile_Response("Please set File FName.");
        if (data.getFileInfo().getL_name()!="")
            param.add(new BasicNameValuePair(C_constant.l_name,data.getFileInfo().getL_name()));
        // return new UpdateFile_Response("Please set File LName.");
        if (data.getFileInfo().getNotes()!="")
            param.add(new BasicNameValuePair(C_constant.notes,data.getFileInfo().getNotes()));
        //return new UpdateFile_Response("Please set File Notes.");
        if (data.getFileInfo().getEmail()!="")
            param.add(new BasicNameValuePair(C_constant.email,data.getFileInfo().getEmail()));
        // return new UpdateFile_Response("Please set File Email.");
        if (data.getFileInfo().getPhone()!="")
            param.add(new BasicNameValuePair(C_constant.phone,data.getFileInfo().getPhone()));
        //return new UpdateFile_Response("Please set File Phone.");
        if (data.getFileInfo().getTags()!="")
            param.add(new BasicNameValuePair(C_constant.tags,data.getFileInfo().getTags()));
        if (data.getFileInfo().getMeta_notes()!="")
            param.add(new BasicNameValuePair(C_constant.meta_note,data.getFileInfo().getMeta_notes()));
        // return new UpdateFile_Response("Please set Meta Notes.");
        if(data.getFileInfo().getMeta_url()!="")
            param.add(new BasicNameValuePair(C_constant.meta_url,data.getFileInfo().getMeta_url()));
        if(data.getFileInfo().getMeta_duration()!="")
            param.add(new BasicNameValuePair(C_constant.meta_duration,data.getFileInfo().getMeta_duration()));
        // return  new UpdateFile_Response("Please set Meta URL ");
        if(data.getFileInfo().getFolder_id()!="")
            param.add(new BasicNameValuePair(C_constant.folder_id,data.getFileInfo().getFolder_id()));
//            return  new UpdateFile_Response("Please set FolderId");
        if(data.getFileInfo().getName()!="")
            param.add(new BasicNameValuePair(C_constant.name,data.getFileInfo().getName()));
        if(data.getFileInfo().getReminder_days()!="")
            param.add(new BasicNameValuePair(C_constant.remind_days,data.getFileInfo().getReminder_days()));
//            return new UpdateFile_Response("Please set Reminder Date");
        if(data.getFileInfo().getReminder_dates()!="")
            param.add(new BasicNameValuePair(C_constant.remind_date,data.getFileInfo().getReminder_dates()));
          // return new UpdateFile_Response("Please set Reminder Days");
      /*  if(data.getFileInfo().getTags().equals(""))
            return new UpdateFile_Response("Please set Tags");
        if(data.getFileInfo().getIsStar().equals(""))
            return new UpdateFile_Response("Please set IsStart");*/


        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id,data.getFileInfo().getId()));

        webservice_call.handleRequest(1,ApiClient.update_file,param,"POST");




    }
}
