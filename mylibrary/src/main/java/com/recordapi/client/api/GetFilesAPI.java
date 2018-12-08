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

public class GetFilesAPI {

    private GetFiles data;
    private RecordingApiListener mListener;
    private Parse webservice_call;
    private Handler uiHandler;
    private SaveData sd;
    private InternetConnection internet;

    public GetFilesAPI(Context c, GetFiles data, RecordingApiListener mListener) {
        this.data = data;
        this.mListener = mListener;

        sd = new SaveData(c);
        internet = new InternetConnection(c);
        Handlar_call();
        webservice_call = new Parse(uiHandler, null);
        GetFileCall();
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

        GetFiles_Response response_data;//= new RegisterPhone_Response();

        if (jobj == null) {
            response_data = new GetFiles_Response(C_constant.wrong_message);
            mListener.onFailure(response_data);
        } else {
            try {
                if (jobj.getString(C_constant.status).equals(C_constant.ok)) {
                    sd.setFoldersFiles_JSON(jobj.toString(), data.getFolder_id());
                    JSONArray jar = jobj.getJSONArray(C_constant.files);
                    ArrayList<FileData> fdata = new ArrayList<>();
                    FileData fo = null;
                    for (int i = 0; i < jar.length(); i++) {
                        JSONObject jo = jar.getJSONObject(i);//jo.getString("access_number")


                        fo = new FileData(jo.getString(C_constant.id), jo.getString(C_constant.order_id), jo.getString(C_constant.sid), jo.getString(C_constant.name), jo.getString(C_constant.f_name), jo.getString(C_constant.l_name), jo.getString(C_constant.email), jo.getString(C_constant.phone), jo.getString(C_constant.notes), jo.getString(C_constant.source), jo.getString(C_constant.url), jo.getString(C_constant.duration), jo.getString(C_constant.time), jo.getString(C_constant.share_url), jo.getString(C_constant.download_url), jo.getString(C_constant.is_star), data.getReminder() == true ? jo.getString(C_constant.remind_days) : "", data.getReminder() == true ? jo.getString(C_constant.remind_date) : "");
                        fdata.add(fo);
                    }
                    response_data = new GetFiles_Response("File List Available.", fdata, "" + jobj.getInt(C_constant.credits));
                    mListener.onSuccess(response_data);
                } else {
//                    response_data.setStatus(false);
//                    response_data.setMsg(jobj.getString("msg"));
                    response_data = new GetFiles_Response(jobj.getString(C_constant.msg));

                    mListener.onFailure(response_data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                response_data = new GetFiles_Response(C_constant.wrong_message);
                mListener.onFailure(response_data);
            }

        }

    }

    public void GetFileCall() {
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        //Validation
       /* if(data.getApi_key().equals(""))
            mListener.onFailure(new GetFiles_Response("Plese set ApiKey"));*/
        if (data.getFolder_id() != "")
            param.add(new BasicNameValuePair(C_constant.folder_id, data.getFolder_id()));
        if (data.getPage() != "")
            param.add(new BasicNameValuePair(C_constant.page, data.getPage()));
        if (data.getPass() != "")
            param.add(new BasicNameValuePair(C_constant.pass, data.getPass()));
        if (data.getSource() != "")
            param.add(new BasicNameValuePair(C_constant.source, data.getSource()));
        if (data.getReminder())
            param.add(new BasicNameValuePair(C_constant.reminder, "" + data.getReminder()));
        if (data.getSearch_text() != "")
            param.add(new BasicNameValuePair(C_constant.q, data.getSearch_text()));

        param.add(new BasicNameValuePair(C_constant.api_key, sd.getToken()));


        /*
        :["folder_id": "335", "reminder": "true", "api_key": "59f445d164a8a59f445d164ac5", "source": "all"]

*/
        if (internet.check_internet())
            webservice_call.handleRequest(1, ApiClient.get_files, param, "POST");
        else {
            try {

                JSONObject jobj = new JSONObject(sd.getFolderFiles_JSON(data.getFolder_id()));
                JSONArray jar = jobj.getJSONArray(C_constant.files);
                ArrayList<FileData> fdata = new ArrayList<>();
                FileData fo = null;


                if (data.getFolder_id().contentEquals("all")) {
                    JSONArray jaoff = new JSONArray(sd.getOfflineFileCreated());
                    for (int i = 0; i > jaoff.length(); i++) {
                        JSONArray ja_sub = jaoff.getJSONArray(i);

                       // JSONObject joo = (JSONObject) ja_sub.get(0);
                        String file = ((JSONObject) ja_sub.get(0)).getString(C_constant.file);
                       // String api_key = ((JSONObject) ja_sub.get(1)).getString(C_constant.api_key);
                        //String id = ((JSONObject) ja_sub.get(2)).getString(C_constant.id);
                        JSONObject data_obj = new JSONObject(((JSONObject) ja_sub.get(3)).getString(C_constant.data));

                        fo = new FileData(((-i) - 1) + "", "", "", data_obj.getString("name"), "", "", "", "", "", "", file, "", "", "", file, "false", "", "");
                        fdata.add(fo);
                    }
                }

                for (int i = 0; i < jar.length(); i++) {
                    JSONObject jo = jar.getJSONObject(i);//jo.getString("access_number")


                    fo = new FileData(jo.getString(C_constant.id), jo.getString(C_constant.order_id), jo.getString(C_constant.sid), jo.getString(C_constant.name), jo.getString(C_constant.f_name), jo.getString(C_constant.l_name), jo.getString(C_constant.email), jo.getString(C_constant.phone), jo.getString(C_constant.notes), jo.getString(C_constant.source), jo.getString(C_constant.url), jo.getString(C_constant.duration), jo.getString(C_constant.time), jo.getString(C_constant.share_url), jo.getString(C_constant.download_url), jo.getString(C_constant.is_star), data.getReminder() == true ? jo.getString(C_constant.remind_days) : "", data.getReminder() == true ? jo.getString(C_constant.remind_date) : "");
                    fdata.add(fo);
                }
                //response_data = new GetFiles_Response("File List Available.",fdata,""+jobj.getInt(C_constant.credits));
                mListener.onSuccess(new GetFiles_Response("File List Available.", fdata, "" + jobj.getInt(C_constant.credits)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mListener.onFailure(new GetFiles_Response(C_constant.no_Internet));
        }
    }


}
