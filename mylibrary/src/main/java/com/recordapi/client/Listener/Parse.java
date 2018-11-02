package com.recordapi.client.Listener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.recordapi.client.RecordingApi;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

//import com.netd.activitys.Order_Conformation;
//import com.netd.activitys.Order_summery;

public class Parse {
    private Handler uiHandler = null;
    //private Context context = null;
    // private String reqJson = null;
    private final String TAG = "LoginRequestHandler";
    private int requestType = 0;
    private String request_url = "";

    private RecordingApi recordingApi;

    private String method = "";
    private List<NameValuePair> get_parameter;
    private String param;

    public Parse(Handler uiHandler, Context cont) {
        this.uiHandler = uiHandler;
        //this.context = cont;
        this.recordingApi = new RecordingApi();

        // logger = MyLogger.getInstance();
    }

    public void handleRequest(int reqType, String request_url, List<NameValuePair> param, String method) {
        // this.reqJson = reqJson;
        requestType = reqType;
        this.get_parameter = param;
        this.request_url = request_url;
        this.method = method;
        new Thread(new GET_CALLS()).start();
    }

    public void handleRequest(int reqType, String param, String method) {
        // this.reqJson = reqJson;
        requestType = reqType;
        this.param = param;
        //this.request_url = request_url;
        this.method = method;
        new Thread(new GET_CALLS()).start();
    }

    public class GET_CALLS implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub

            JSONObject jsonobject = null;
            JSONArray jsonarray = null;


            //Log.e("PARAM ;;", "get_parameter:-:" + param + " ;;" + get_parameter);


            if (method.equals("POST"))
            {
                if (requestType == 1)
                    jsonobject = recordingApi.makeHttpRequestFor_SSL(request_url, method, get_parameter);
//                jsonparse.makeHTTPPOST(Constant.get_mob_register_api, method, param);//

                else if (requestType == 2)
                    jsonarray = recordingApi.makeHttpRequestFor_SSL_Array(request_url, method, get_parameter);


            }
            else
                {
               /* if (Constant.Registration_type == requestType)
                    jsonobject = jsonparse.makeHttpRequest(Constant.Registration_api, "POST", get_parameter);*/


            }

            if (uiHandler != null) {
                Message message = uiHandler.obtainMessage();
                message.what = requestType;
                if (jsonobject == null)
                    message.obj = jsonarray;
                else
                    message.obj = jsonobject; // setresponse hare
                uiHandler.sendMessage(message);
            }
        }
    }
}
