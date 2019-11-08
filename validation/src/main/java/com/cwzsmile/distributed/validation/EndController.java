package com.cwzsmile.distributed.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author csh9016
 * @date 2019/11/8
 * RequestBody和@RequestPart导致MethodArgumentNotValidException(
 * body参数绑定错误会抛出该异常)
 */
@RestController
@RequestMapping("/end")
public class EndController {

    @RequestMapping("/one")

    public String home(@Valid @RequestBody NextVO nextVO) {
        return "one" + nextVO.toString();
    }
}
