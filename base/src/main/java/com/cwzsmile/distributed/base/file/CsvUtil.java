package com.cwzsmile.distributed.base.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author csh9016
 * @date 2019/9/16
 */
public class CsvUtil {

    public static final String path = "C:\\Users\\wenzheng.chen\\Desktop\\nike";
    static Pattern compile = Pattern.compile("(订单|退换货|要求|收据号)([0-9a-zA-Z]{4,})");

    public static void main(String[] args) throws IOException {
        Path in = Paths.get(CsvUtil.path + "/5035-7.csv");
        Path out = Paths.get(CsvUtil.path + "/5035-7-1.csv");
        if (!Files.exists(in)) {
            Files.createFile(out);
        }
        BufferedReader inReader = Files.newBufferedReader(in, Charset.forName("GBK"));
        BufferedWriter outReader = Files.newBufferedWriter(out, Charset.forName("GBK"));
        String red = "";
        int index = 0;
        while (true) {
            red = inReader.readLine();
            if (Objects.isNull(red)) {
                break;
            }
            outReader.write(String.format("\"%s\",%s", extractWord(red), red));
            outReader.newLine();
            if (index % 100 == 0) {
                outReader.flush();
            }
            //System.out.println((++index) + "::" + extractWord(red));
        }
    }


    public static String extractWord(String base) {
        Matcher matcher = compile.matcher(base);
        if (matcher.find()) {
            return matcher.group(2);
        } else {
            return "";
        }
    }
}
