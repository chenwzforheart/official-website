package com.cwzsmile.distributed.base.http;

import lombok.Data;

import java.util.List;

/**
 * @author csh9016
 * @date 2020/4/15
 */
@Data
public class DbResponse<T> {

    private Integer statu;
    private String msg;
    private List<T> data;
}
