package com.cwzsmile.distributed.validation;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author csh9016
 * @date 2019/11/8
 */
@Data
public class NextVO {

    @NotNull
    private Integer id;

    private String name;

    private String mobile;

    @NotNull
    @DateTimeFormat(pattern = "yy-MM-dd") //不管用
    private Date createTime;
}
