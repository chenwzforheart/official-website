package com.cwzsmile.distributed.base.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author csh9016
 * @date 2019/11/10
 */
public class HttpClientTest {

    public static void main1(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        httpGet.setHeader("Content-Type", "text/html");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        Header[] allHeaders = response.getAllHeaders();
        Arrays.stream(allHeaders).forEach(s -> {
            System.out.println(s.getName() + ":" + s.getValue());
        });
        try {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            byte[] buf = new byte[1024];
            int i = 0;
            int len = 0;
            while ((len = content.read(buf)) != -1) {
                String s = new String(buf, 0, len);
                System.out.println((++i) + "::" + s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) throws IOException {
        StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);
    }

    public static void main3(String[] args) {
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("param1", "value1"));
        formparams.add(new BasicNameValuePair("param2", "value2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httpPost = new HttpPost("http://lcoalhost/handler.do");
        httpPost.setEntity(entity);
    }

    public static void main4(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost/json");

        ResponseHandler<JSONObject> rh = new ResponseHandler<JSONObject>() {
            @Override
            public JSONObject handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                InputStreamReader reader = new InputStreamReader(entity.getContent(), charset);

                //return gson.fromJson(reader,JSONObject.class);
                return null;
            }
        };
        JSONObject response = httpClient.execute(httpGet, rh);
    }

    public static void main5(String[] args) {
        DefaultConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    keepAlive = 5000;
                }
                return keepAlive;
            }
        };
        CloseableHttpClient httpClient = HttpClients.custom().setKeepAliveStrategy(keepAliveStrat).build();
    }

    public static void main6(String[] args) {

        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {
                    //Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    return false;
                }
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    //Retry if the request is considered idempotent
                    return true;
                }
                return false;
            }
        };
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryHandler(myRetryHandler)
                .build();
        HttpGet httpGet1 = new HttpGet("http://localhost/1");
        httpGet1.setConfig(requestConfig);
    }

    public static void main(String[] args) throws IOException{
        CloseableHttpClient httpClient = HttpClients.custom().addInterceptorLast(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                AtomicInteger count = (AtomicInteger) context.getAttribute("count");
                request.addHeader("Count", Integer.toString(count.getAndIncrement()));
            }
        }).build();
        AtomicInteger count = new AtomicInteger(1);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAttribute("count",count);

        HttpGet httpGet = new HttpGet("http://localhost/");
        for (int i = 0; i < 10; i++) {
            CloseableHttpResponse response = httpClient.execute(httpGet, localContext);
            try {
                HttpEntity entity = response.getEntity();
            } finally {
                response.close();
            }
        }
    }
}
