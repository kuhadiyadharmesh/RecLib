package com.recordapi.client.Listener;

import org.json.JSONObject;

public interface InternalApiListener
{
    public void onSuccess(JSONObject jdata);
    public void onError(JSONObject jdata);
}
