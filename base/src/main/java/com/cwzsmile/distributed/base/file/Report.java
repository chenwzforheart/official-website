package com.cwzsmile.distributed.base.file;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author csh9016
 * @date 2019/10/18
 */
@Data
public class Report {
    @ExcelProperty(index = 0)
    private String a;
    @ExcelProperty(index = 1)
    private String b;
    @ExcelProperty(index = 2)
    private String c;
    @ExcelProperty(index = 3)
    private String d;
    @ExcelProperty(index = 4)
    private String e;
}
