package com.cwzsmile.distributed.base.http;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * Created by csh9016 on 2019/5/31.
 */
public class FastJsonHttpMessageConverter5 extends FastJsonHttpMessageConverter4 {

    public FastJsonHttpMessageConverter5() {
        List<MediaType> mediaTypes = Lists.newArrayList(
                MediaType.valueOf("application/json;charset=UTF-8"),
                MediaType.valueOf("application/*+json;charset=UTF-8"),
                MediaType.valueOf("application/octet-stream"),
                MediaType.valueOf("text/json;charset=utf-8"));
        setSupportedMediaTypes(mediaTypes);
    }
}
