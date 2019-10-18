package com.cwzsmile.distributed.base.file;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author csh9016
 * @date 2019/10/18
 */
@Slf4j
public class DemoAnalysisEventListener extends AnalysisEventListener<Report> {

    private BufferedWriter bufferedWriter;

    private static final int BATCH_COUNT = 1000;
    private static AtomicInteger count = new AtomicInteger(0);
    List<Report> list = new ArrayList<Report>();

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void invoke(Report data, AnalysisContext analysisContext) {
        count.incrementAndGet();
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！" + count.get());
    }

    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        ExcelDemo.saveCsv(list,bufferedWriter);
        log.info("存储数据库成功！");
    }
}
