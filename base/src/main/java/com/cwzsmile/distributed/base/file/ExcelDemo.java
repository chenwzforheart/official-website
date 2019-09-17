package com.cwzsmile.distributed.base.file;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableTable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author csh9016
 * @date 2019/9/17
 */
public class ExcelDemo {

    public static void main(String[] args) {
        try {
            String path = "D:\\opt\\223013.xlsx";
            Path out = Paths.get("D:\\opt\\223013-1.csv");
            if (!Files.exists(out)) {
                Files.createFile(out);
            }
            BufferedWriter outReader = Files.newBufferedWriter(out, Charset.forName("GBK"));
            List<List<String>> data = FileUtil.readDataFromFile(path, 5);
            List<String> forward = null;
            for (int i = 0; i < data.size(); i++) {
                if (forward == null) {
                    forward = data.get(i);
                    continue;
                }

                List<String> now = data.get(i);
                if (now.get(1).equals(forward.get(1)) && now.get(2).equals(forward.get(2))) {
                    //前后相同，合并
                    String merge = "";
                    if (forward.get(3).charAt(0) == '【') {
                        merge = forward.get(3) + now.get(3);
                    } else {
                        merge = now.get(3) + forward.get(3);
                    }
                    //System.out.println(String.format("\"%s\",\"%s\",\"%s\",\"%s\"",forward.get(3),"2",forward.get(1),forward.get(2)));
                    outReader.write(String.format("\"%s\",\"%s\",\"%s\",\"%s\"", merge, "2", forward.get(1), forward.get(2)));
                    outReader.newLine();
                    outReader.flush();
                    forward = null;
                } else {
                    //前后不同，进一步
                    outReader.write(String.format("\"%s\",\"%s\",\"%s\",\"%s\"", forward.get(3), "1", forward.get(1), forward.get(2)));
                    outReader.newLine();
                    outReader.flush();
                    System.out.println(JSON.toJSONString(forward));
                    forward = now;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
