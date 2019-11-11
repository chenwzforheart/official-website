package com.cwzsmile.distributed.performance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author csh9016
 * @date 2019/11/11
 */
@SpringBootApplication
public class PerformanceApplication {

    public static void main(String[] args) {
        new SpringApplication(PerformanceApplication.class).run(args);
    }
}
