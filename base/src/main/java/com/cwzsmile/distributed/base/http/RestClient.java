package com.cwzsmile.distributed.base.http;

import com.google.common.collect.Lists;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author csh9016
 * @date 2019/8/9
 */
public class RestClient {

    private RestClient() {
        throw new UnsupportedOperationException();
    }

    private static final RestTemplate restTemplate;

    static {
        //生成一个设置了连接超时时间、请求超时时间、异常最大重试次数的httpClient
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10_000).setConnectTimeout(10_000).setSocketTimeout(30_000).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 40
        cm.setDefaultMaxPerRoute(50);

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(config)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                .build();

        //使用httpClient创建一个ClientHttpRequestFactory的实现
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getMessageConverters().set(2, new FastJsonHttpMessageConverter5());
        restTemplate.setInterceptors(Lists.newArrayList(new LogClientHttpRequestInterceptor()));
    }

    public static RestTemplate client() {
        return restTemplate;
    }
}
