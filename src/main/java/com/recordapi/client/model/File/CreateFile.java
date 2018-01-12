package com.recordapi.client.model.File;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class CreateFile
{
    //curl -X POST  -F "file=@/var/www/apps/phone/message.wav" -F "api_key=557872b508520557872b50855c" -F
    // 'data={"name":"testfile","notes":"testnotes",”remind_days”:”10”,”remind_date”:”10-jan-2017 23:23:52”}' https://app2.virtualbrix.net/rapi/create_file/

    private String api_key,file,data;
    private String name , notes , remind_days,remind_date;

    public CreateFile(String api_key,String file , String name,String notes)
    {
        this.api_key = api_key;
        this.file = file;
        if(name.equals("") | notes.equals(""))
            data = "";
        else
        data="{\"name\":\""+name+"\",\"notes\":\""+notes+"\"}";
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getFile()
    {
        return this.file;
    }
    public String getData()
    {
        return this.data;
    }


}
