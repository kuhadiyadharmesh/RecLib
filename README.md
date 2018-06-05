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

*BusinessApi* | [**addNewEmployee**](docs/BusinessApi.md#addNewEmployee) | **POST** /v1/Business/AddNewEmployee | Add Comapny Employee
