package com.cwzsmile.distributed.quartz;

import org.springframework.stereotype.Service;

/**
 * @author csh9016
 * @date 2019/9/6
 */
@Service
public class DemoService {

    public void get() {
        System.out.println("---------time run--------");
    }
}
