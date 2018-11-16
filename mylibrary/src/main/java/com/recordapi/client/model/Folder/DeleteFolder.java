package com.recordapi.client.model.Folder;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class DeleteFolder
{
    //"api_key=557872b508520557872b50855c&id=32&move_to=31"

    private String folder_id,move_to;

    public DeleteFolder(String id , String move_to)
    {

        this.folder_id = id ;
        this.move_to = move_to;
    }

//    public String getApi_key()
//    {
//        return  this.api_key;
//    }
    public String getFolder_id()
    {
        return  this.folder_id;
    }
    public String getMove_to()
    {
        return this.move_to;
    }


}
