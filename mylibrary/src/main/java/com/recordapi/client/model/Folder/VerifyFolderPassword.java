package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class VerifyFolderPassword
{
    //"api_key=557872b508520557872b50855c&pass=1234&id=32"

    private String folder_id,password;

    public VerifyFolderPassword(String id , String password)
    {

        this.folder_id = id ;
        this.password = password;
    }

//    public String getApi_key()
//    {
//        return  this.api_key;
//    }
    public String getFolder_id()
    {
        return  this.folder_id;
    }
    public String getPassword()
    {
        return this.password;
    }
}
