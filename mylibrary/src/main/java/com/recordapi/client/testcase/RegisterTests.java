package com.recordapi.client.testcase;

import android.util.Log;

import com.recordapi.client.Listener.RecordingApiListener;
import com.recordapi.client.api.PhoneRegisterAPI;
import com.recordapi.client.api.VerifyPhoneAPI;
import com.recordapi.client.model.RegisterPhone;
import com.recordapi.client.model.RegisterPhone_Response;
import com.recordapi.client.model.VerifyPhone;
import com.recordapi.client.model.VerifyPhone_Response;

public class RegisterTests
{
    private RegisterPhone req_data;
    public RegisterTests()
    {
        req_data = new RegisterPhone("+40727272727");
        Service_Call();
    }

    public void S01_RegisterWrongNumber()
    {
        req_data = new RegisterPhone("1234567891");
        Service_Call();
    }
    public void S02_RegisterWrongCode()
    {
        req_data = new RegisterPhone("+40727272727");
        Service_Call1(req_data,"+40727272727","1234","","","","");
    }
    public void S03_RegisterWrongPhone()
    {
        req_data = new RegisterPhone("+40727272727");
        Service_Call1(req_data,"+919236589521","","300","","appCode","DeviceToken");
    }

    public void S04_RegisterEmptyPhone()
    {
        req_data = new RegisterPhone("+40727272727");
        Service_Call1(req_data,"","","300","","appCode","DeviceToken");
    }
    public void S05_RegisterEmptyToken()
    {

    }

    public void Service_Call()
    {
        new PhoneRegisterAPI(null, req_data, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                RegisterPhone_Response data = (RegisterPhone_Response) obj;
                Log.d("Response Success","RegisterPhone -- "+data.getMsg());
            }

            @Override
            public void onFailure(Object obj) {
                RegisterPhone_Response data = (RegisterPhone_Response) obj;
                Log.d("Response ERROR","RegisterPhone -- "+data.getMsg());
            }
        });
    }

    public void Service_Call1(final RegisterPhone req_data , final String phone , final String code , final String mcc , final String token, final String app, final String device_token)
    {
        new PhoneRegisterAPI(null, req_data, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                RegisterPhone_Response data = (RegisterPhone_Response) obj;
                Log.d("Response Success","RegisterPhone -- "+data.getMsg());

                //new VerifyPhoneAPI(null,new VerifyPhone("+40727272727","",","))
                String code_f = code;
                if(code == "")
                {
                    code_f = data.getCode();
                }


                Service_VerifyPhoneApi(phone,code_f,mcc,token,app,device_token);
            }

            @Override
            public void onFailure(Object obj) {
                RegisterPhone_Response data = (RegisterPhone_Response) obj;
                Log.d("Response ERROR","RegisterPhone -- "+data.getMsg());
            }
        });
    }

    public void Service_VerifyPhoneApi(String phone , String code ,String mcc , String token,String app,String device_token)
    {
        VerifyPhone rdata = new VerifyPhone(phone,code,mcc,app,device_token,"10","");
        new VerifyPhoneAPI(null, rdata, new RecordingApiListener() {
            @Override
            public void onSuccess(Object obj) {
                VerifyPhone_Response data = (VerifyPhone_Response) obj;
                Log.d("Response Success","RegisterPhone -- "+data.getMsg());
            }

            @Override
            public void onFailure(Object obj) {
                VerifyPhone_Response data = (VerifyPhone_Response) obj;
                Log.d("Response ERROR","RegisterPhone -- "+data.getMsg());
            }
        });
    }
}
