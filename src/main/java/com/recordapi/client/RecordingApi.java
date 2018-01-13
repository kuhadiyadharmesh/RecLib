package com.recordapi.client;

import android.util.Log;

import com.recordapi.client.ApiClient;
import com.recordapi.client.RecApi;
import com.recordapi.client.model.RegisterPhone;
import com.recordapi.client.model.RegisterPhone_Response;
//import com.squareup.okhttp.Call;
//import com.squareup.okhttp.FormEncodingBuilder;
//import com.squareup.okhttp.HttpUrl;
//import com.squareup.okhttp.MediaType;
//import com.squareup.okhttp.MultipartBuilder;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//import okio.BufferedSink;

/**
 * Created by Dharmesh-PC on 1/5/2018.
 */

public class RecordingApi
{
    public RegisterPhone data ;

    static InputStream is = null;
    static JSONObject jObj = null;
    static JSONArray Jarray = null;
    static String json = "";
    //String res = null;

    public JSONObject makeHttpRequestFor_SSL(String url, String method, List<NameValuePair> param)
    {
        // TODO Auto-generated method stub
        try {
            // check for request method
            if (method == "POST") {
                System.out.println(" In post Method");
                //DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpClient httpClient = createHttpClient();
                HttpPost httpPost = new HttpPost(url+"/");
                httpPost.setEntity(new UrlEncodedFormEntity(param));
                //httpPost.setS

                //Multiple
                MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

                for (int index = 0; index < param.size(); index++) {
                    if (param.get(index).getName().equalsIgnoreCase("file")) {
                        // If the key equals to "image", we use FileBody to transfer the data
                        entity.addPart(param.get(index).getName(), new FileBody(new File(param.get(index).getValue())));

                    } else {
                        // Normal string data
                        entity.addPart(param.get(index).getName(), new StringBody(param.get(index).getValue()));
                    }
                }

                httpPost.setEntity(entity);
                //Multiple

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println(" In post Method");
            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(param, "utf-8");
                url += "" + paramString;
                HttpGet httpGet = new HttpGet(url);
                //String basicAuth = "Basic " + new String(Base64.encode("tomcat:tomcat".getBytes(),Base64.NO_WRAP ));
                //post.setRequestProperty ("Authorization", basicAuth);
                //httpGet.setHeader("Authorization", basicAuth);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Error_Exception();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return Error_Exception();
        } catch (IOException e) {
            e.printStackTrace();
            return Error_Exception();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            System.out.println(" In post Method");
            json = sb.toString().trim();
            Log.e("data prints", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
            return Error_Exception();
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
            // Jarray = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            return Error_Exception();
        }

        // return JSON String
        // return jObj;
        return jObj;

    }

    public JSONArray makeHttpRequestFor_SSL_Array(String url, String method, List<NameValuePair> param)
    {
        // TODO Auto-generated method stub
        try {
            // check for request method
            if (method == "POST") {
                System.out.println(" In post Method");
                //DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpClient httpClient = createHttpClient();
                HttpPost httpPost = new HttpPost(url+"/");
                httpPost.setEntity(new UrlEncodedFormEntity(param));
                //httpPost.setS

                //Multiple
                MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

                for (int index = 0; index < param.size(); index++) {
                    if (param.get(index).getName().equalsIgnoreCase("file")) {
                        // If the key equals to "image", we use FileBody to transfer the data
                        entity.addPart(param.get(index).getName(), new FileBody(new File(param.get(index).getValue())));

                    } else {
                        // Normal string data
                        entity.addPart(param.get(index).getName(), new StringBody(param.get(index).getValue()));
                    }
                }

                httpPost.setEntity(entity);
                //Multiple

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println(" In post Method");
            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(param, "utf-8");
                url += "" + paramString;
                HttpGet httpGet = new HttpGet(url);
                //String basicAuth = "Basic " + new String(Base64.encode("tomcat:tomcat".getBytes(),Base64.NO_WRAP ));
                //post.setRequestProperty ("Authorization", basicAuth);
                //httpGet.setHeader("Authorization", basicAuth);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Error_Exception_Array();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return Error_Exception_Array();
        } catch (IOException e) {
            e.printStackTrace();
            return Error_Exception_Array();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            System.out.println(" In post Method");
            json = sb.toString().trim();
            Log.e("data prints", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
            return Error_Exception_Array();
        }

        // try parse the string to a JSON object
        try {
           // jObj = new JSONObject(json);
             Jarray = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            return Error_Exception_Array();
        }

        // return JSON String
         return Jarray;
       // return jObj;

    }

    public static HttpClient createHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    private static class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[]{tm}, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }

    public JSONObject Error_Exception()
    {
        JSONObject json = null;
        try {
            json = new JSONObject("{\"status\":\"faliar\",\"msg\":\"Please Contact your Service Provide Or please Check your internet Connection\"}");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }
    public JSONArray Error_Exception_Array()
    {
        JSONArray json = null;
        try {
//            json = new JSONArray("[{\"status\":\"faliar\",\"msg\":\"Please Contact your Service Provide Or please Check your internet Connection\"}]");
            json = new JSONArray("[]");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }
}
