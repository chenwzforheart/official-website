package com.cwzsmile.distributed.base.file;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author csh9016
 * @date 2019/10/18
 */
public class ReadTest {

    @Test
    public void simpleRead() {
        BufferedWriter outReader = null;
        try {
            Path out = Paths.get("D:\\opt\\levis\\223032-7.csv");
            if (!Files.exists(out)) {
                Files.createFile(out);
            }
            outReader = Files.newBufferedWriter(out, Charset.forName("GBK"));
        } catch (IOException e) {
        }
        DemoAnalysisEventListener demoAnalysisEventListener = new DemoAnalysisEventListener();
        demoAnalysisEventListener.setBufferedWriter(outReader);
        EasyExcel.read("D:\\opt\\levis\\223032-7.xlsx", Report.class, demoAnalysisEventListener).sheet().doRead();
    }
}
