package com.cwzsmile.distributed.base.http;

import com.google.common.io.Files;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author csh9016
 * @date 2019/9/20
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\wenzheng.chen\\Desktop\\request_id.json");
        File file = new File("C:\\Users\\wenzheng.chen\\Desktop\\request_id.json");
        List<String> gbk = Files.readLines(file, Charset.forName("UTF-8"));
        System.out.println(gbk.size());
        ExecutorService es = Executors.newFixedThreadPool(200);
        for (int i = 0; i < gbk.size(); i++) {
            String mm = gbk.get(i);
            es.execute(new Runnable() {
                @Override
                public void run() {
                    String msgId = mm;
                    String resp = RestClient.client().postForObject(
                            "",
                            null,
                            String.class,
                            msgId, msgId);

                }
            });
        }
        es.shutdown();
    }
}
