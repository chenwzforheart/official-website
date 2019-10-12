package com.cwzsmile.distributed.base.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author csh9016
 * @date 2019/8/8
 */
@Slf4j
public class LogClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("------------------------rest request----------------------------\n"
                        + "request url:{}\n"
                        + "request method:{}\n"
                        + "request header:{}\n"
                        + "request body:{}\n"
                        + "----------------------------------------------------------------",
                httpRequest.getURI(), httpRequest.getMethodValue(), httpRequest.getHeaders().toString(),
                new String(body)
        );
        ClientHttpResponse response = execution.execute(httpRequest, body);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (response.getStatusCode() == HttpStatus.OK) {
            byte[] bys = new byte[512];
            int length;
            while ((length = response.getBody().read(bys)) > 0) {
                outputStream.write(bys, 0, length);
            }
        }
        log.info("-----------------------rest response----------------------------\n"
                        + "response code:{}\n"
                        + "response msg:{}\n"
                        + "response headers:{}\n"
                        + "response body:{}\n"
                        + "----------------------------------------------------------------",
                response.getStatusCode(), response.getStatusText(), response.getHeaders().toString(),
                new String(outputStream.toByteArray(), "utf-8"));

        return new ClientHttpResponseWrapper(response, outputStream.toByteArray());
    }
}
