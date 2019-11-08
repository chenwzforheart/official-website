package com.cwzsmile.distributed.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author csh9016
 * @date 2019/11/8
 * Service参数校验必须@Validated在类型上,对应参数上需要加注解，
 * pojo嵌套认证需要@Valid(@Validated是没有作用的)
 */
@Service
@Validated
public class ToService {


    public void check(@NotNull String appId, @Valid NextVO nextVO) {

    }
}
