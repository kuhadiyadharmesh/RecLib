package com.recordapi.client.database;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;

public class InternetConnection
{
    private  Context c;
    private boolean is_connected = false ;

    public InternetConnection(Context c)
    {
        this.c = c;
        Tovuti.from(c).monitor(new Monitor.ConnectivityListener()
        {
            @Override
            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast)
            {
                // TODO: Handle the connection...
                is_connected = isConnected;
            }
        });
    }

    public boolean check_internet()
    {
        return is_connected;
    }

//    private boolean shouldAskPermission()
//    {
//
//        Tovuti.from(c).monitor(new Monitor.ConnectivityListener()
//        {
//            @Override
//            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast)
//            {
//                // TODO: Handle the connection...
//            }
//        });
//
//
//    }
/*
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){
            case REQUEST_CONNECTION_PERMISSION:
                Log.d(TAG, "Permission granted result : " + grantResults[0]);
                Log.d(TAG, "Permission granted result : " + grantResults[1]);
                Log.d(TAG, "Permission granted result : " + grantResults[2]);


                boolean accepted = (grantResults[0]== PackageManager.PERMISSION_GRANTED)
                        && (grantResults[1]== PackageManager.PERMISSION_GRANTED)
                        && (grantResults[2]== PackageManager.PERMISSION_GRANTED);
                if(accepted) {
                    startTcpInAThread("str");
                } else {
                    Toast.makeText(c.getApplicationContext(), "Permissions issues!!!!", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }*/
}
