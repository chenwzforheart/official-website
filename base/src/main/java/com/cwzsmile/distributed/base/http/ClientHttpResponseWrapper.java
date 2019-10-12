package com.cwzsmile.distributed.base.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author csh9016
 * @date 2019/8/8
 */
public class ClientHttpResponseWrapper implements ClientHttpResponse {
    private ClientHttpResponse response;
    private InputStream in;

    public ClientHttpResponseWrapper(ClientHttpResponse response, byte[] body) {
        this.response = response;
        in = new ByteArrayInputStream(body);
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return response.getStatusCode();
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return response.getRawStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return response.getStatusText();
    }

    @Override
    public void close() {
        try {
            in.close();
            response.close();
        } catch (IOException e) {
            //ignore
        }
    }

    @Override
    public InputStream getBody() throws IOException {
        return in;
    }

    @Override
    public HttpHeaders getHeaders() {
        return response.getHeaders();
    }
}
