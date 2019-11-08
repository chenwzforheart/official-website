package com.cwzsmile.distributed.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author csh9016
 * @date 2019/11/7
 * Validated必须加在类型上，才能验证方法参数
 * 抛出ConstraintViolationException异常
 */
@RestController
@RequestMapping("/home")
@Validated
public class HomeController {

    @RequestMapping("/one")

    public String home(@NotNull String appId,@NotNull String code) {
        return "one" + appId;
    }
}



