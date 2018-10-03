package com.recordapi.client.api;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecordingApi;
import com.recordapi.client.model.Common.Common_Response;
import com.recordapi.client.model.Common.UpdateOrderData;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dharmesh-PC on 5/8/2018.
 */

public class UpdateOrderFolderAPI
{
    private UpdateOrderData data ;
    private RecordingApi recordingApi;
    public UpdateOrderFolderAPI(UpdateOrderData data)
    {
        this.data = data ;
        recordingApi = new RecordingApi();
    }
    public Common_Response UpdateOrderFolderCall()
    {
        //Validation
        if(data.getApikey().equals(""))
            return new Common_Response("Please set ApiKey");
        if(data.getId()== 0)
            return new Common_Response("Please set file/folder id .");
        if(data.getTop_Id()==0)
            return new Common_Response("Please set Index position .");
//        if(data.getDevice_type().equals(""))
//            return new UpdateFolderOrder_Response("Please set Device Type android or iphone");

        // Set parameter
        ArrayList<NameValuePair> param = new  ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("api_key",data.getApikey()));
        param.add(new BasicNameValuePair("id",""+data.getId()));
        //param.add(new BasicNameValuePair("index",""+data.getIndex()));
        param.add(new BasicNameValuePair("top_id",""+data.getTop_Id()));
        param.add(new BasicNameValuePair("type","folder"));


//        for(data.get?)
        //for (Folders fol: data.getFolderdata())
        //{
       //     param.add(new BasicNameValuePair("folders["+fol.getId()+"]",fol.getId()));
       // }

        //param.add(new BasicNameValuePair("device_type",data.getDevice_type()));


        JSONObject jobj = null ;
        jobj = recordingApi.makeHttpRequestFor_SSL(ApiClient.BasePath+"update_order","POST",param);
        Common_Response response_data  = null;

        if(jobj == null)
        {
            response_data = new Common_Response("Something Wrong");
        }
        else
        {
            try
            {
                if (jobj.getString("status").equals("ok"))
                {
                    response_data = new Common_Response(true,jobj.getString("msg"));
                    return response_data;
                }
                else
                {
                    response_data = new Common_Response(jobj.getString("msg"));
                    return  response_data;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }
        return  response_data;

    }
}
