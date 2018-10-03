# RecLib
RecordingLibrary
## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

Refer to the [official documentation](https://maven.apache.org/plugins/maven deploy plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
<dependency>
	    <groupId>com.github.kuhadiyadharmesh</groupId>
	    <artifactId>RecLib</artifactId>
	    <version>0.1.15</version>
</dependency>
```
### Gradle users

Add this dependency to your project's build file:
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```



```groovy
implementation 'com.github.kuhadiyadharmesh:RecLib:0.1.15'
```


## Getting Started

   =======
  * 1> RegisterAPI* | [**PhoneRegisterAPI**](docs/PhoneRegisterAPI.md#addNewEmployee) | **POST** | PhoneRegisterAPI
  *MODEL RegisterPhone*
  ```
  RegisterPhone obj = new RegisterPhone();
  obj.setPhonenumber("049632587410");
  RegisterPhone_Response obj_response = obj.RegisterPhoneCall()
  Log.e("Response ok .",obj_response.getMsg())
  ```
 
  * 2> VerifyPhoneAPI* | [**VerifyPhoneAPI**](docs/VerifyPhoneAPI.md#addNewEmployee) | **POST** | VerifyPhoneAPI
  *MODEL VerifyPhoneAPI*
  ```
  VerifyPhone verifyPhone = new VerifyPhone("919737711174", et_code.getText().toString(), "300", "free", "55942ee3894f51000530894", "cryJVtuHFno:APA91bHRWhN  VFDMC2bdYbOuP53qNpMS MhCKmEXk8RmdVA0hOYu2FXsAChMSig6SNspL XWNS 26cmwlTB1aRJ epBgp8abP4Zt2NqrH2lWyCSav1Ek2mQrOExQ7m9AYFxZbGGV3k9", "android", "10");
 
  VerifyPhoneAPI papi = new VerifyPhoneAPI(verifyPhone);
  VerifyPhone_Response response = papi.VerifyPhoneCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 3> GetFolderAPI* | [**GetFolderAPI**](docs/GetFolderAPI.md#addNewEmployee) | **POST** | GetFolderAPI
  *MODEL GetFolderAPI*
  ```
  GetFolder getFolder = new GetFolder("5a0c3c58957ab5a0c3c58957ed");
  GetFolderAPI papi = new GetFolderAPI(getFolder);
  GetFolder_Response response = papi.GetFolderCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 4> CreateFolderAPI* | [**CreateFolderAPI**](docs/CreateFolderAPI.md#addNewEmployee) | **POST** | CreateFolderAPI
  *MODEL CreateFolderAPI*
  ```
  CreateFolder getFolder = new CreateFolder();
  getFolder.setApi_key("5a0c3c58957ab5a0c3c58957ed");
  getFolder.setName("sunilTest");
  getFolder.setPass("11111");
  CreateFolderAPI capi = new CreateFolderAPI(getFolder);
  CreateFolder_Response response = capi.CreateFolderCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 5> UpdateFolderAPI* | [**UpdateFolderAPI**](docs/UpdateFolderAPI.md#addNewEmployee) | **POST** | UpdateFolderAPI
  *MODEL UpdateFolderAPI*
  ```
  UpdateFolder UpdFolder = new UpdateFolder("5a0c3c58957ab5a0c3c58957ed", folder_id, "Sunil Update Test");
  UpdateFolderAPI capi = new UpdateFolderAPI(UpdFolder);
  UpdateFolder_Response response = capi.UpdateFolderCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 6> UpdateFolderOrderAPI* | [**UpdateFolderOrderAPI**](docs/UpdateFolderOrderAPI.md#addNewEmployee) | **POST** | UpdateFolderOrderAPI
  *MODEL UpdateFolderOrderAPI*
  ```
  ArrayList<Folders> fd = new ArrayList<>();
  fd.add(new Folders("119", "2"));
  UpdateFolderOrder methd = new UpdateFolderOrder("5a0c3c58957ab5a0c3c58957ed", fd);
  UpdateFolderOrderAPI m_api = new UpdateFolderOrderAPI(methd);
  UpdateFolderOrder_Response response = m_api.UpdateFolderOrderCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 7> VerifyFolderPasswordAPI* | [**VerifyFolderPasswordAPI**](docs/VerifyFolderPasswordAPI.md#addNewEmployee) | **POST** | VerifyFolderPasswordAPI
  *MODEL VerifyFolderPassword*
  ```
  VerifyFolderPassword VerfFolder = new VerifyFolderPassword("5a0c3c58957ab5a0c3c58957ed", folder_id, "11111");
  VerifyFolderPasswordAPI capi = new VerifyFolderPasswordAPI(VerfFolder);
  VerifyFolderPassword_Response response = capi.VerifyFolderPasswordCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 8> DeleteFolderAPI* | [**DeleteFolderAPI**](docs/DeleteFolderAPI.md#addNewEmployee) | **POST** | DeleteFolderAPI
  *MODEL DeleteFolder*
  ```
  DeleteFolder DelFolder = new DeleteFolder("5a0c3c58957ab5a0c3c58957ed", folder_id, "31");  //pass move to '31' mandatory.
  DeleteFolderAPI capi = new DeleteFolderAPI(DelFolder);
  DeleteFolder_Response response = capi.DeleteFolderCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 9> GetFilesAPI* | [**GetFilesAPI**](docs/GetFilesAPI.md#addNewEmployee) | **POST** | GetFilesAPI
  *MODEL GetFilesAPI*
  ```
  GetFiles methd = new GetFiles("5a0c3c58957ab5a0c3c58957ed", "", "", "", "", false);
  GetFilesAPI m_api = new GetFilesAPI(methd);
  GetFiles_Response response = m_api.GetFileCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 10> CreateFileAPI* | [**CreateFileAPI**](docs/CreateFileAPI.md#addNewEmployee) | **POST** | CreateFileAPI
  *MODEL CreateFileAPI*
  ```
  CreateFile creat_file = new CreateFile("5a0c3c58957ab5a0c3c58957ed", "/storage/emulated/0/Call Rec/rec_audio_file.mp3", "sp_file", "sp_note","remind_days","reminde_date  ");
  CreateFileAPI cfapi = new CreateFileAPI(creat_file);
  CreateFile_Response response = cfapi.CreateFileCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 11> StartUnStartFileFolderAPI* | [**StartUnStartFileFolderAPI**](docs/StartUnStartFileFolderAPI.md#StartUnStartFileFolderAPI) | **POST** | StartUnStartFileFolderAPI
  *MODEL StartUnStartFileFolderAPI*
  ```
  StartUnStarFolderFile star = new StartUnStarFolderFile("5a0c3c58957ab5a0c3c58957ed", "338", "file", true);
  StartUnStartFileFolderAPI cfapi = new StartUnStartFileFolderAPI(star);
  StartUnStartFolderFile_Response response = cfapi.StartUnStartFileFolderCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 12> UpdateFileAPI* | [**UpdateFileAPI**](docs/UpdateFileAPI.md#UpdateFileAPI) | **POST** | UpdateFileAPI
  *MODEL UpdateFileAPI*
  ```
  FileData fd = new FileData("338", "sp_file", "s", "p", "netd.sunil@gmail.com", "9722547251", "testing", "20", "meta test", "meta_test_url", "0", "10", "11", "me test");
  UpdateFile methd = new UpdateFile("5a0c3c58957ab5a0c3c58957ed", fd);
  UpdateFileAPI m_api = new UpdateFileAPI(methd);
  UpdateFile_Response response = m_api.UpdateFileCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 13> CloneFileAPI* | [**CloneFileAPI**](docs/CloneFileAPI.md#CloneFileAPI) | **POST** | CloneFileAPI
  *MODEL CloneFileAPI*
  ```
  CloneFile methd = new CloneFile("5a0c3c58957ab5a0c3c58957ed", file_id);
  CloneFileAPI m_api = new CloneFileAPI(methd);
  CloneFile_Response response = m_api.CloneFileCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 14> DeleteFilesAPI* | [**DeleteFilesAPI**](docs/DeleteFilesAPI.md#DeleteFilesAPI) | **POST** | DeleteFilesAPI
  *MODEL DeleteFilesAPI*
  ```
  DeleteFile methd = new DeleteFile("5a0c3c58957ab5a0c3c58957ed", file_id, true);
  DeleteFilesAPI m_api = new DeleteFilesAPI(methd);
  DeleteFile_Response response = m_api.DeleteFileCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
  * 15> RecoverFileAPI* | [**RecoverFileAPI**](docs/RecoverFileAPI.md#RecoverFileAPI) | **POST** | RecoverFileAPI
  *MODEL RecoverFileAPI*
  ```
  RecoverFile methd = new RecoverFile("5a0c3c58957ab5a0c3c58957ed", file_id, "all");
  RecoverFileAPI m_api = new RecoverFileAPI(methd);
  RecoverFile_Response response = m_api.RecoveryFileCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 16> GetProfileSettingAPI* | [**GetProfileSettingAPI**](docs/GetProfileSettingAPI.md#GetProfileSettingAPI) | **POST** | GetProfileSettingAPI
  *MODEL GetProfileSettingAPI*
  ```
  GetProfileSetting methd = new GetProfileSetting("5a0c3c58957ab5a0c3c58957ed");
  GetProfileSettingAPI m_api = new GetProfileSettingAPI(methd);
  GetProfileSetting_Response response = m_api.GetProfileSettingCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 17> UpdateProfileSettingAPI* | [**UpdateProfileSettingAPI**](docs/UpdateProfileSettingAPI.md#UpdateProfileSettingAPI) | **POST** | UpdateProfileSettingAPI
  *MODEL UpdateProfileSettingAPI*
  ```
  UpdateProfileSetting methd = new UpdateProfileSetting("5a0c3c58957ab5a0c3c58957ed", "s", "p", "netd.sunil@gmail.com", "0", "en us", "0", "20");
  UpdateProfileSettingAPI m_api = new UpdateProfileSettingAPI(methd);
  UpdateProfileSetting_Response response = m_api.UpdateProfileSettingCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 18> UpdateSettingAPI* | [**UpdateSettingAPI**](docs/UpdateSettingAPI.md#UpdateSettingAPI) | **POST** | UpdateSettingAPI
  *MODEL UpdateSettingAPI*
  ```
  UpdateSetting methd = new UpdateSetting("5a0c3c58957ab5a0c3c58957ed", "no", "public");
  UpdateSettingAPI m_api = new UpdateSettingAPI(methd);
  UpdateSetting_Response response = m_api.UpdateSettingCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
  * 19> GetSettingAPI* | [**GetSettingAPI**](docs/GetSettingAPI.md#GetSettingAPI) | **POST** | GetSettingAPI
  *MODEL GetSettingAPI*
  ```
  GetSetting methd = new GetSetting("5a0c3c58957ab5a0c3c58957ed");
  GetSettingAPI m_api = new GetSettingAPI(methd);
  GetSetting_Response response = m_api.GetSettingCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
  * 20> GetMessageAPI* | [**GetMessageAPI**](docs/GetMessageAPI.md#GetMessageAPI) | **POST** | GetMessageAPI
  *MODEL GetMessageAPI*
  ```
  GetMessage methd = new GetMessage("5a0c3c58957ab5a0c3c58957ed");
  GetMessageAPI m_api = new GetMessageAPI(methd);
  GetMessage_Response response = m_api.GetMessageCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 21> BuyCreditAPI* | [**BuyCreditAPI**](docs/BuyCreditAPI.md#BuyCreditAPI) | **POST** | BuyCreditAPI
  *MODEL BuyCreditAPI*
  ```
  BuyCredit methd = new BuyCredit("5a0c3c58957ab5a0c3c58957ed", "2", "hknidfpldcgkjhcfngfjbbbd.AO J1OxrRmZtK9HYii FUNijFiDe N8IsMUqlZo5 _3LXNoqdfFU45szvSUq44EFkFv1OkAncS09cX5Cz9EcJua0z7F708JHqNu_oagSMK5rVdqZxTyHQSGihGd7NR9uvoEO_Oi8xj9i6rJ0Astv_8ykZqoA84SVsg");
              methd.setForAndroid("com.pk.bsocredit");
  BuyCreditAPI m_api = new BuyCreditAPI(methd);
  BuyCredit_Response response = m_api.BuyCreditCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 22> UpdateDeviceTokenAPI* | [**UpdateDeviceTokenAPI**](docs/UpdateDeviceTokenAPI.md#UpdateDeviceTokenAPI) | **POST** | UpdateDeviceTokenAPI
  *MODEL UpdateDeviceTokenAPI*
  ```
  UpdateDeviceToken methd = new UpdateDeviceToken("5a0c3c58957ab5a0c3c58957ed", "55942ee3894f51000530894", "android");
  UpdateDeviceTokenAPI m_api = new UpdateDeviceTokenAPI(methd);
  UpdateDeviceToken_Response response = m_api.UpdateDeviceTokenCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
  * 23> GetTranslationsAPI* | [**GetTranslationsAPI**](docs/GetTranslationsAPI.md#GetTranslationsAPI) | **POST** | GetTranslationsAPI
  *MODEL GetTranslationsAPI*
  ```
  GetTranslations methd = new GetTranslations("5a0c3c58957ab5a0c3c58957ed", "en_US");
  GetTranslationsAPI m_api = new GetTranslationsAPI(methd);
  GetTranslations_Response response = m_api.GetTranslationsCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
  * 24> GetLanguageListAPI* | [**GetLanguageListAPI**](docs/GetLanguageListAPI.md#GetLanguageListAPI) | **POST** | GetLanguageListAPI
  *MODEL GetLanguageListAPI*
  ```
  GetLanguagesList methd = new GetLanguagesList("5a0c3c58957ab5a0c3c58957ed");
  GetLanguageListAPI m_api = new GetLanguageListAPI(methd);
  GetLanguageList_Response response = m_api.GetLanguagesListCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 25> GetPhoneNumberAPI* | [**GetPhoneNumberAPI**](docs/GetPhoneNumberAPI.md#GetPhoneNumberAPI) | **POST** | GetPhoneNumberAPI
  *MODEL GetPhoneNumberAPI*
  ```
  GetPhoneNumber methd = new GetPhoneNumber("5a0c3c58957ab5a0c3c58957ed");
  GetPhoneNumberAPI m_api = new GetPhoneNumberAPI(methd);
  GetPhoneNumber_Response response = m_api.GetPhoneNumberCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 26> UpdateUserAPI* | [**UpdateUserAPI**](docs/UpdateUserAPI.md#UpdateUserAPI) | **POST** | UpdateUserAPI
  *MODEL UpdateUserAPI*
  ```
  UpdateUser methd = new UpdateUser("5a0c3c58957ab5a0c3c58957ed", "free");
  UpdateUserAPI m_api = new UpdateUserAPI(methd);
              UpdateUser_Response response = m_api.UpdateUserCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 27> NotifyUserAPI* | [**NotifyUserAPI**](docs/NotifyUserAPI.md#NotifyUserAPI) | **POST** | NotifyUserAPI
  *MODEL NotifyUserAPI*
  ```
  NotifyUser methd = new NotifyUser("5a0c3c58957ab5a0c3c58957ed", "Test Title", "Test Body");
  NotifyUserAPI m_api = new NotifyUserAPI(methd);
  NotifyUser_Response response = m_api.NotifyUserCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 28> GetMetaFilesAPI* | [**GetMetaFilesAPI**](docs/GetMetaFilesAPI.md#GetMetaFilesAPI) | **POST** | GetMetaFilesAPI
  *MODEL GetMetaFilesAPI*
  ```
  GetMetaFiles methd = new GetMetaFiles("5a0c3c58957ab5a0c3c58957ed", "338");
  GetMetaFilesAPI m_api = new GetMetaFilesAPI(methd);
  GetMetaFiles_Response response = m_api.GetMetaFilesCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 29> DeleteMetaFilesAPI* | [**DeleteMetaFilesAPI**](docs/DeleteMetaFilesAPI.md#DeleteMetaFilesAPI) | **POST** | DeleteMetaFilesAPI
  *MODEL DeleteMetaFilesAPI*
  ```
  DeleteMetaFiles methd = new DeleteMetaFiles("5a0c3c58957ab5a0c3c58957ed", meta_file_id);
  DeleteMetaFilesAPI m_api = new DeleteMetaFilesAPI(methd);
  DeleteMetaFiles_Response response = m_api.DeleteMetaFilesCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
  * 29> UpdateProfilePicureAPI* | [**UpdateProfilePicureAPI**](docs/UpdateProfilePicureAPI.md#UpdateProfilePicureAPI) | **POST** | UpdateProfilePicureAPI
  *MODEL UpdateProfilePicureAPI*
  ```
  UpdateProfilePicture methd = new UpdateProfilePicture("5a0c3c58957ab5a0c3c58957ed", path);
  UpdateProfilePicureAPI m_api = new UpdateProfilePicureAPI(methd);
  UpdateProfilePicure_Response response = m_api.UpdateProfilePicureCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
 
  * 30> CreateMetaFileAPI* | [**CreateMetaFileAPI**](docs/CreateMetaFileAPI.md#CreateMetaFileAPI) | **POST** | CreateMetaFileAPI
  *MODEL CreateMetaFileAPI*
  ```
  CreateMetaFile methd = new CreateMetaFile("5a0c3c58957ab5a0c3c58957ed", path, "Test Meta", "338");
  CreateMetaFileAPI m_api = new CreateMetaFileAPI(methd);
  CreateMetaFile_Response response = m_api.CreateMetaFileCall();
  Log.e("Response", response.getStatus()  "   "  response.getMsg());
  ```
  * 31> UpdateOrderFolderAPI* | [**UpdateOrderFolderAPI**](docs/UpdateOrderFolderAPI.md#UpdateProfilePicureAPI) | **POST** | UpdateOrderFolderAPI
    *MODEL UpdateOrderFolderAPI*
    ```
    UpdateOrderData methd = new UpdateOrderData(apikey, id ,  index)
    UpdateOrderFolderAPI m_api = new UpdateOrderFolderAPI(methd);
    Common_Response response = m_api.UpdateOrderFolderCall();
    Log.e("Response", response.getStatus()  "   "  response.getMsg());
    ```

  * 32> UpdateOrderFileAPI* | [**UpdateOrderFileAPI**](docs/UpdateOrderFileAPI.md#UpdateOrderFileAPI) | **POST** | UpdateOrderFileAPI
    *MODEL UpdateOrderFileAPI*
    ```
    UpdateOrderData methd = new UpdateOrderData(String apikey,int id , int index)
    UpdateOrderFileAPI m_api = new UpdateOrderFileAPI(methd);
    Common_Response response = m_api.UpdateOrderFileCall();
    Log.e("Response", response.getStatus()  "   "  response.getMsg());
    ```