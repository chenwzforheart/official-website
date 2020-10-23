package com.cwzsmile.distributed.base.file;

import com.alibaba.fastjson.JSONPath;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import java.util.Objects;

/**
 * @author csh9016
 * @date 2020/9/25
 */
public class JsonToCSV {

    private static final String path = "C:\\Users\\wenzheng.chen\\Desktop\\sql\\1";

    public static void main(String[] args) throws Exception {
        Path in = Paths.get(path + "\\monitor_biz_exc_hist.1.json");
        Path out = Paths.get(path + "\\monitor_biz_exc_hist.0.json.csv");
        if (!Files.exists(out)) {
            Files.createFile(out);
        }
        BufferedReader inReader = Files.newBufferedReader(in, Charset.forName("utf8"));
        BufferedWriter outReader = Files.newBufferedWriter(out, Charset.forName("GBK"), StandardOpenOption.APPEND);

        String red = "";
        int index = 0;
        while (true) {
            red = inReader.readLine();
            if (Objects.isNull(red)) {
                break;
            }
            System.out.println(JSONPath.read(red, "$.appCode"));
            System.out.println(JSONPath.read(red, "$.monitorCode"));
            System.out.println(JSONPath.read(red, "$.monitorName"));
            System.out.println(JSONPath.read(red, "$.excDataCount"));
            System.out.println(JSONPath.read(red, "$.remark"));
            System.out.println(JSONPath.read(red, "$.createTime"));

            Object alarm = JSONPath.read(red, "$.alarmInfoList");
            Object date = JSONPath.read(red, "$.createTime");
            outReader.write(String.format("\"%s\",%s,\"%s\",%s,\"%s\",%s",
                    JSONPath.read(red, "$.appCode"),
                    JSONPath.read(red, "$.monitorCode"),
                    JSONPath.read(red, "$.monitorName"),
                    JSONPath.read(red, "$.excDataCount"),
                    JSONPath.read(red, "$.remark"),
                    date == null ? "":DateFormatUtils.format(Long.parseLong(date.toString()),"yyyy-MM-dd HH:mm:ss",Locale.CHINA)
            ));
            outReader.newLine();
            if (index % 100 == 0) {
                outReader.flush();
            }
            index++;
            //System.out.println((++index) + "::" + extractWord(red));
        }
        outReader.flush();
    }
}
