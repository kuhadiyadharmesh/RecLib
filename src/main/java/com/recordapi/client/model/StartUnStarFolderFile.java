package com.recordapi.client.model;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class StartUnStarFolderFile
{
    //"api_key=553a431e192d2553a431e1930f&star=1&type=file&id=55"

    private String api_key,folder_id,type;
    private boolean star = false ;

    //file or folder
    //star=true or false
    public StartUnStarFolderFile(String api_key,String id , String type,boolean star )
    {
        this.api_key = api_key;
        this.folder_id = id ;
        this.star = star;
        this.type = type;
    }

    public String getApi_key()
    {
        return  this.api_key;
    }
    public String getFolder_id()
    {
        return  this.folder_id;
    }
    public String getType()
    {
        return this.type;
    }
    public String getStar(){
        return (star==true ? "1":"0");
    }
}
