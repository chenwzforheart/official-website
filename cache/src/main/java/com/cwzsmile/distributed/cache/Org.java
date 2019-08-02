package com.cwzsmile.distributed.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * @author csh9016
 * @date 2019/8/1
 */
@Data
public class Org implements Serializable {

    private Integer orgId;

    private Integer status;
}
