package com.recordapi.client;

//import com.


import java.io.IOException;

/**
 * Created by Dharmesh-PC on 1/5/2018.
 */

public class ApiClient
{
    private static String BasePath = "https://app2.virtualbrix.net/rapi/";
    public static String Profile_Img_Path = "https://app2.virtualbrix.net/upload/update_profile_img";

    public static String register_phone= BasePath+"register_phone";
    public static String verify_phone= BasePath+"verify_phone";
    public static String buy_credits= BasePath+"buy_credits";
    public static String clone_file= BasePath+"clone_file";
    public static String create_file= BasePath+"create_file";
    public static String create_folder= BasePath+"create_folder";
    public static String upload_meta_file= BasePath+"upload_meta_file";
    public static String delete_files= BasePath+"delete_files";
    public static String delete_folder= BasePath+"delete_folder";
    public static String delete_meta_files= BasePath+"delete_meta_files";
    public static String get_files= BasePath+"get_files";
    public static String get_folders= BasePath+"get_folders";
    public static String get_languages= BasePath+"get_languages";
    public static String get_msgs= BasePath+"get_msgs";
    public static String get_meta_files= BasePath+"get_meta_files";
    public static String get_phones= BasePath+"get_phones";
    public static String get_profile= BasePath+"get_profile";
    public static String get_settings= BasePath+"get_settings";
    public static String get_translations= BasePath+"get_translations";
    public static String notify_user_custom= BasePath+"notify_user_custom";
    public static String recover_file= BasePath+"recover_file";
    public static String update_star= BasePath+"update_star";
    public static String update_device_toke= BasePath+"update_device_toke";
    public static String update_file= BasePath+"update_file";
    public static String update_folder= BasePath+"update_folder";
    public static String update_order= BasePath+"update_order";
    public static String verify_folder_pass= BasePath+"verify_folder_pass";
    public static String update_user= BasePath+"update_user";
    public static String update_settings= BasePath+"update_settings";
    public static String update_profile= BasePath+"update_profile";
   // public static String update_order= BasePath+"update_order";




//    private OkHttpClient httpClient;
//
//    public ApiClient()
//    {
//        httpClient = new OkHttpClient();
//    }
//
//
//    public String RegisterPhone(Request request)
//    {
//        try
//        {
//            Response response = httpClient.newCall(request).execute();
//            return response.body().string();
//        }
//        catch (IOException ed)
//        {
//            return "";
//        }
//    }




}
