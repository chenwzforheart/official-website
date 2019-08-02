package com.cwzsmile.distributed.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author csh9016
 * @date 2019/8/1
 */
@SpringBootApplication
public class CachceApplication {

    public static void main(String[] args) {
        new SpringApplication(CachceApplication.class).run(args);
    }
}
