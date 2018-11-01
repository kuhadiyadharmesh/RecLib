package com.recordapi.client.Listener;

import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.GetPhoneNumber_Response;

import java.util.Map;

public  interface RecordingApiListener
{


    public abstract void onSuccess(Object obj);

    public abstract void onFailure();
}
