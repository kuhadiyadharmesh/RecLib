package com.recordapi.client.database;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.recordapi.client.ApiClient;
import com.recordapi.client.Listener.Parse;
import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.api.CreateFileAPI;
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.File.CreateFile;
import com.recordapi.client.model.File.CreateFile_Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class Internet_Broadcast extends BroadcastReceiver {
    public static final String NETWORK_CHANGE_ACTION = "com.androiderstack.broadcastreceiverdemo.NetworkChangeReceiver";
    private SaveData sd;
    int position = 0;

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (isOnline(context)) {
            sd = new SaveData(context);
            try {
            /*
            Intent intent = new Intent();
            intent.putExtra("status", status);
            intent.setAction(NETWORK_CHANGE_ACTION);
            context.sendBroadcast(intent);
            */
                final JSONArray ja = new JSONArray(sd.getOfflineFileCreated());
                position = 0;
                for (int i = 0; i < ja.length(); i++) {

                    JSONObject jo = ja.getJSONObject(i);
                    position = i;

                    CreateFile createFile = new CreateFile(jo.getString(C_constant.file), jo.getString(C_constant.id));
                    createFile.setName(jo.getJSONObject(C_constant.data).getString("name"));
                    createFile.setNotes(jo.getJSONObject(C_constant.data).getString("notes"));
                    createFile.setRemind_days(jo.getJSONObject(C_constant.data).getString("remind_days"));
                    createFile.setRemind_date(jo.getJSONObject(C_constant.data).getString("remind_date"));

                    new CreateFileAPI(context, createFile, new RecordingApiListener() {
                        @Override
                        public void onSuccess(Object o) {
                            CreateFile_Response response = (CreateFile_Response) o;
                            if (response != null) {
                                //Toast.makeText(context, response.getMsg(), Toast.LENGTH_SHORT).show();
                                if (response.getStaus()) {
                                   /* SmoothlyStopAll();
                                    finish();*/
                                    if (position >= (ja.length() - 1)) {
                                        sd.setOfflineFileCreate("");
                                    }
                                } else {
                                }
                            } else {
                                //Toast.makeText(context, "please try again.", Toast.LENGTH_SHORT).show();
                            }
                            //Prg_dialog(false);
                        }

                        @Override
                        public void onFailure(Object o) {
                            //Prg_dialog(false);
                        }
                    });

                }


                Log.e("Library", "Internet connection updated");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //sendInternalBroadcast(context, "Internet Connected");
        } else {
            sendInternalBroadcast(context, "Internet Not Connected");
        }
    }

    private void sendInternalBroadcast(Context context, String status) {
        try {
            /*
            Intent intent = new Intent();
            intent.putExtra("status", status);
            intent.setAction(NETWORK_CHANGE_ACTION);
            context.sendBroadcast(intent);
            */
            Log.e("Library", "Internet connection updated");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isOnline(Context context) {
        boolean isOnline = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            isOnline = (netInfo != null && netInfo.isConnected());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return isOnline;
    }
}
