package com.cwzsmile.distributed.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author csh9016
 * @date 2019/11/8
 * Validated,Valid必须加在VO参数之前
 * 抛出BindException异常,BindingResult会吃掉异常
 */
@RestController
@RequestMapping("/next")
public class NextController {

    @RequestMapping("/one")
    public String home(@Validated NextVO nextVO) {
        return "one" + nextVO.toString();
    }

    @RequestMapping("/two")
    public String two(@Valid NextVO nextVO, BindingResult result) {
        return "two" + nextVO.toString();
    }
}
