package com.recordapi.client;

import android.content.Context;

import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.api.PhoneRegisterAPI;
import com.recordapi.client.database.InternetConnection;
import com.recordapi.client.database.SaveData;
import com.recordapi.client.model.C_constant;
import com.recordapi.client.model.RegisterPhone;
import com.recordapi.client.model.RegisterPhone_Response;

/**
 * Created by Dharmesh-PC on 1/5/2018.
 */

public class RecApi
{
    String message ;
    private RecordingApiListener mListener;
    private Context c;
    private InternetConnection internet ;
    private SaveData sd ;

    public RecApi(Context c , RecordingApiListener mListener )
    {
        this.c = c;
        this.mListener = mListener;
        internet = new InternetConnection(c);
        sd = new SaveData(c);
    }

    public void RegisterAPI(RegisterPhone data)
    {
        if(internet.check_internet())
        new PhoneRegisterAPI(c,data,mListener);
        else
        {
            mListener.onFailure(new RegisterPhone_Response(C_constant.no_Internet));
            /*
            if(sd.getPhoneApidata())
            {
                mListener.onSuccess(sd.getPhoneApidata());
            }
            else
            {
                mListener.onFailure(new RegisterPhone_Response(C_constant.no_Internet));
            }*/
        }
    }

    public void Log()
    {
        System.out.println(message);
    }
}
