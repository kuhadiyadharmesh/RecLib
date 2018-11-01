package com.recordapi.client.Listener;

import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.Setting.GetPhoneNumber_Response;

public interface RecordingApiListener
{
    public void onSuccess(final Class<?> response);

    public void onSuccess(RegisterPhone_Response data);

    public void onSuccess(GetPhoneNumber_Response data);

    public void onFailure();
}
