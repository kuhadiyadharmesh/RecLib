package com.recordapi.client.api;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.database.InternetConnection;
import com.recordapi.client.database.SaveData;
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.Common.Message;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.GetMessage;
import com.recordapi.client.model.Setting.GetMessage_Response;
import com.recordapi.client.model.Setting.UpdateProfilePicture;
import com.recordapi.client.model.Setting.UpdateProfilePicure_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetMessageAPI
{
    //private GetMessage data ;
    private RecordingApiListener mListener;
    private Parse webservice_call ;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet ;

    public GetMessageAPI(Context c, RecordingApiListener mListener)
    {
        //this.data = data ;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler,null);
        GetMessageCall();
    }

    private void Handlar_call()
    {
        uiHandler = new Handler() {
            public void handleMessage(android.os.Message msg)
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

        GetMessage_Response response_data;// = new RegisterPhone_Response();

        if (jobj == null) {
            response_data = new GetMessage_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        }
        else
            {
            try {
                if (jobj.getString(C_constant.status).equals(C_constant.ok))
                {
                    sd.setGetMessage_JSON(jobj.toString());
                    JSONArray jar = jobj.getJSONArray(C_constant.msgs);
                    Message msg = null;
                    ArrayList<Message> messagelist = new ArrayList<>();
                    for (int i = 0; i < jar.length(); i++) {
                        JSONObject jo = jar.getJSONObject(i);
                        msg = new Message(jo.getString(C_constant.id), jo.getString(C_constant.title), jo.getString(C_constant.body), jo.getString(C_constant.time));

                        messagelist.add(msg);

                    }
                    response_data = new GetMessage_Response(C_constant.s_msg_list_successfully, messagelist);
                    mListener.onSuccess(response_data);
                } else {
                    response_data = new GetMessage_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                response_data = new GetMessage_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }
    }
    public void GetMessageCall()
    {
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        //param.add(new BasicNameValuePair("file",data.getFile()));
        param.add(new BasicNameValuePair(C_constant.api_key,sd.getToken()));
        //param.add(new BasicNameValuePair("data",data.getData()));


        if(internet.check_internet() && sd.getGetMessage_JSON().length() == 0)
        webservice_call.handleRequest(1,ApiClient.get_msgs,param,"POST");
        else
        {
            try {

                JSONObject jobj = new JSONObject(sd.getGetMessage_JSON());

                JSONArray jar = jobj.getJSONArray(C_constant.msgs);
                Message msg = null;
                ArrayList<Message> messagelist = new ArrayList<>();
                for (int i = 0; i < jar.length(); i++) {
                    JSONObject jo = jar.getJSONObject(i);
                    msg = new Message(jo.getString(C_constant.id), jo.getString(C_constant.title), jo.getString(C_constant.body), jo.getString(C_constant.time));

                    messagelist.add(msg);

                }
                GetMessage_Response  response_data = new GetMessage_Response(C_constant.s_msg_list_successfully, messagelist);
                mListener.onSuccess(response_data);
            } catch (JSONException e) {
               mListener.onFailure(new GetMessage_Response(C_constant.no_Internet) );
            }
        }

    }
}
