package com.cwzsmile.distributed.transaction.client;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.codec.StringDecoder;
import org.junit.Test;

/**
 * @author csh9016
 * @date 2021/2/1
 */
public class UserActionTest {
    @Test
    public void loginAction() {
        UserAction action = Feign.builder().decoder(new StringDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(UserAction.class, "http://localhost");
        action.loginAction("zhangsan", "zhangsan");
    }

    @Test
    public void getById() {
        UserAction action = Feign.builder().decoder(new StringDecoder())
                .target(UserAction.class, "http://localhost");
        action.getById(100);
    }
}
