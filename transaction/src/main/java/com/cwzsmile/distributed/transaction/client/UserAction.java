package com.cwzsmile.distributed.transaction.client;

import feign.Param;
import feign.RequestLine;

/**
 * @author csh9016
 * @date 2021/2/1
 */
public interface UserAction {

    @RequestLine("GET /login/{username}/{password}")
    public String loginAction(@Param("username") String username, @Param("password") String password);

    @RequestLine("GET /user/{userid}")
    public String getById(@Param("userid") Integer userid);
}
