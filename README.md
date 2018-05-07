# RecLib
RecordingLibrary
## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

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
	    <version>0.1.4</version>
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
compile 'com.github.kuhadiyadharmesh:RecLib:0.1.4'
```


## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

*RegisterAPI* | [**PhoneRegisterAPI**](docs/PhoneRegisterAPI.md#addNewEmployee) | **POST** | PhoneRegisterAPI
*MODEL-RegisterPhone*  
```
RegisterPhone obj = new RegisterPhone();
obj.setPhonenumber("+049632587410");
RegisterPhone_Response obj_response = obj.RegisterPhoneCall()
Log.e("Response ok .",obj_response.getMsg())
```

*VerifyPhoneAPI* | [**VerifyPhoneAPI**](docs/VerifyPhoneAPI.md#addNewEmployee) | **POST** | VerifyPhoneAPI
*MODEL-VerifyPhoneAPI*  
```
VerifyPhone verifyPhone = new VerifyPhone("+919737711174", et_code.getText().toString(), "300", "free", "55942ee3894f51000530894", "cryJVtuHFno:APA91bHRWhN--VFDMC2bdYbOuP53qNpMS-MhCKmEXk8RmdVA0hOYu2FXsAChMSig6SNspL-XWNS-26cmwlTB1aRJ-epBgp8abP4Zt2NqrH2lWyCSav1Ek2mQrOExQ7m9AYFxZbGGV3k9", "android", "10");

VerifyPhoneAPI papi = new VerifyPhoneAPI(verifyPhone);
VerifyPhone_Response response = papi.VerifyPhoneCall();
Log.e("Response", response.getStatus() + "---" + response.getMsg());
```

*GetFolderAPI* | [**GetFolderAPI**](docs/GetFolderAPI.md#addNewEmployee) | **POST** | GetFolderAPI
*MODEL-GetFolderAPI*  
```
GetFolder getFolder = new GetFolder("5a0c3c58957ab5a0c3c58957ed");
GetFolderAPI papi = new GetFolderAPI(getFolder);
GetFolder_Response response = papi.GetFolderCall();
Log.e("Response", response.getStatus() + "---" + response.getMsg());
```

*CreateFolderAPI* | [**CreateFolderAPI**](docs/CreateFolderAPI.md#addNewEmployee) | **POST** | CreateFolderAPI
*MODEL-CreateFolderAPI*  
```
CreateFolder getFolder = new CreateFolder();
getFolder.setApi_key("5a0c3c58957ab5a0c3c58957ed");
getFolder.setName("sunilTest");
getFolder.setPass("11111");
CreateFolderAPI capi = new CreateFolderAPI(getFolder);
CreateFolder_Response response = capi.CreateFolderCall();
Log.e("Response", response.getStatus() + "---" + response.getMsg());
```

*UpdateFolderAPI* | [**UpdateFolderAPI**](docs/UpdateFolderAPI.md#addNewEmployee) | **POST** | UpdateFolderAPI
*MODEL-UpdateFolderAPI*  
```
UpdateFolder UpdFolder = new UpdateFolder("5a0c3c58957ab5a0c3c58957ed", folder_id, "Sunil Update Test");
UpdateFolderAPI capi = new UpdateFolderAPI(UpdFolder);
UpdateFolder_Response response = capi.UpdateFolderCall();
Log.e("Response", response.getStatus() + "---" + response.getMsg());
```

