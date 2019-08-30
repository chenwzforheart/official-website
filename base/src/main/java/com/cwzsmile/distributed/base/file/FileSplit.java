package com.cwzsmile.distributed.base.file;


import com.google.common.io.Files;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author csh9016
 * @date 2019/8/26
 */
public class FileSplit {

    public static void main1(String[] args) throws IOException {
        File file = new File("C:\\Users\\wenzheng.chen\\Desktop\\账单\\5-9544.csv");
        List<String> gbk = Files.readLines(file, Charset.forName("GBK"));
        System.out.println(gbk.size());
        if (gbk.size() == 0) {
            throw new IllegalArgumentException("数据为空");
        }
        int page = (gbk.size() - 1) / 200000;
        for (int i = 0; i <= page; i++) {
            Path path = null;
            path = java.nio.file.Files.createFile(Paths.get(
                    "C:\\Users\\wenzheng.chen\\Desktop\\账单\\阿迪达斯-5-9544_" + i + ".csv"));
            java.nio.file.Files.write(path, gbk.subList(i * 200000, i == page ? gbk.size() : (i + 1) * 200000), Charset.forName("GBK"));
        }
    }

    public static void main(String[] args) {
        BiConsumer fun1 = BeanUtils::copyProperties;
        Supplier fun2 = Object::new;
        Object a = new Object();
        Arrays.asList(a).stream().map(FileSplit::apply).collect(Collectors.toList());
    }

    private static Object apply(Object s) {
        Object o = new Object();
        BeanUtils.copyProperties(s, o);
        return o;
    }
}
