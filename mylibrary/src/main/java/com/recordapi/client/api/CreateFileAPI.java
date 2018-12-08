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
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;
import com.recordapi.client.model.Folder.CreateFolder_Response;
import com.recordapi.client.model.RegisterPhone_Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class CreateFileAPI {
    private CreateFile data;
    private RecordingApiListener mListener;
    private Parse webservice_call;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet;

    public CreateFileAPI(Context c, CreateFile data, RecordingApiListener mListener) {
        this.data = data;
        this.mListener = mListener;
        sd = new SaveData(c);
        internet = new InternetConnection(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler, null);
        CreateFileCall();
    }


    private void Handlar_call() {
        uiHandler = new Handler() {
            public void handleMessage(Message msg) {
                try {
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

        CreateFile_Response response_data = null;// = new RegisterPhone_Response();

        if (jobj == null) {
            response_data = new CreateFile_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        } else {
            try {
                if (jobj.getString(C_constant.status).equals(C_constant.ok)) {
                    response_data = new CreateFile_Response(jobj.getString(C_constant.msg), jobj.getString(C_constant.id));
                    mListener.onSuccess(response_data);
                } else {
                    response_data = new CreateFile_Response(jobj.getString(C_constant.msg));
                    mListener.onFailure(response_data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                response_data = new CreateFile_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }
        /*
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
*/
    }

    public void CreateFileCall() {
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        //Validation
//        if(data.getApi_key().equals(""))
//            mListener.onFailure(new CreateFile_Response("Please set ApiKey"));
        if (data.getFile().equals(""))
            mListener.onFailure(new CreateFile_Response(C_constant.v_fileselect_validation));

        // ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(C_constant.file, data.getFile()));
        param.add(new BasicNameValuePair(C_constant.api_key, sd.getToken()));
        param.add(new BasicNameValuePair(C_constant.id, data.getId()));
        param.add(new BasicNameValuePair(C_constant.data, data.getData()));

        if (internet.check_internet())
            webservice_call.handleRequest(1, ApiClient.create_file, param, "POST");
        else {
            try {


                JSONArray ja_off = new JSONArray();
                JSONArray ja = new JSONArray();
                for (int i = 0; i < param.size(); i++) {
                    JSONObject jo = new JSONObject();
                    jo.put(param.get(i).getName(), param.get(i).getValue());
                    ja.put(jo);
                }
                ja_off.put(ja);

                /*String s = "";
                for (int i = 0; i < param.size(); i++) {
                    if (s == "") {
                        s = "\"" + param.get(i).getName() + "\":\"" + param.get(i).getValue() + "\"";
                    } else
                        s = s + ",\"" + param.get(i).getName() + "\":\"" + param.get(i).getValue() + "\"";  // "file":"value"
                }

                s = "[" + s + "]";

                // JSONArray shh = new JSONArray();

                if (!sd.getOfflineFileCreated().contains("")) {
                    ja_off = new JSONArray(sd.getOfflineFileCreated());
                }

                ja_off.put(new JSONObject(param.toString()));   */


                sd.setOfflineFileCreate(ja_off.toString());
                CreateFile_Response response_data = new CreateFile_Response("Offline save", "-" + ja_off.length());
                mListener.onSuccess(response_data);

            } catch (Exception e) {
                mListener.onFailure(new CreateFile_Response(C_constant.no_Internet));
            }
        }

    }

}
