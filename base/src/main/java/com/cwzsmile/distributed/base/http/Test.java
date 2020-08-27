package com.cwzsmile.distributed.base.http;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author csh9016
 * @date 2019/9/20
 */
public class Test {

    public static void main(String[] args) {
        final String url = "http://hippo.cwz.com/backend/api/sqlOptimization/execute/sql?databaseSchemaid=2626&code=13k30wk";

        String header = "Accept: application/json, text/plain, */*\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9,en;q=0.8\n" +
                "Connection: keep-alive\n" +
                "Content-Length: 44\n" +
                "Content-Type: text/plain;charset=UTF-8\n" +
                "Cookie: experimentation_subject_id=Ijk4MTFkM2U0LWVhMjYtNDZhOS1hY2M4LWNmMmZhN2RkZTIwMiI%3D--033cc1b53ad5ffc16b27f5edb0b175416aa4e075; JSESSIONID=5623A3FF9598F13388536F2C6652E498\n" +
                "Host: hippo.baozun.com\n" +
                "Origin: http://hippo.baozun.com\n" +
                "Referer: http://hippo.baozun.com/\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3941.4 Safari/537.36";
        HttpHeaders requestHeaders = new HttpHeaders();
        Splitter.on("\n")
                .trimResults()
                .omitEmptyStrings()
                .split(header).forEach(s -> {
            requestHeaders.addAll(StringUtils.substringBefore(s, ":"), Lists.newArrayList(StringUtils.substringAfter(s, ":").trim().split(";")));/*
            System.out.println(StringUtils.substringBefore(s, ":"));
            System.out.println(Lists.newArrayList(StringUtils.substringAfter(s, ":").trim().split(";")));*/
        });
        //存入数据库
        // 创建表的SQL语句
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/net_openapi?characterEncoding=UTF-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        for (int i = 30; i <= 31; i++) {
            System.out.println(String.format("2006%02d", i));
            dayWork(url, requestHeaders, jdbcTemplate,i);
        }
    }

    public static int countMessageLength(String content) {
        int len = content.length();
        if (len <= 70) {
            return 1;
        } else {
            return (len + 66) / 67;
        }
    }

    public static void dayWork(String url, HttpHeaders requestHeaders, JdbcTemplate jdbc, int day) {
        String sql = String.format("select sub_port,signature,sum(count_content * count_customer) ,sum(count_customer) dd\n" +
                        "from t_msg_sms_request_dtl_202006\n" +
                        "where sms_request_id >= '%s'  and sms_request_id < '%s' and sms_request_id!= 'N/A' \n" +
                        " GROUP BY sub_port,signature ",
                String.format("2006%02d", day),
                String.format("2006%02d", day+1));

        //System.out.println(sql);
        String insert = "insert into tongji(day,sub_port,signature,total,person) values ";
        HttpEntity<String> requestEntity = new HttpEntity<String>(sql, requestHeaders);
        DbResponse<DataResult> body = RestClient.client().exchange(url, HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<DbResponse<DataResult>>() {} ).getBody();


        Lists.partition(body.getData().get(0).getData(), 100).forEach(s -> {
            StringBuilder insertList = new StringBuilder();
            s.forEach(ss -> {
                if (!"sub_port".equals(ss.get(0))) {
                    insertList.append(String.format("('%s','%s','%s','%s','%s')",String.format("2006%02d", day), ss.get(0), ss.get(1), ss.get(2), ss.get(3)));
                    insertList.append(",");
                }
            });
            insertList.deleteCharAt(insertList.length() - 1);
            System.out.println(insertList);
            jdbc.execute(insert + insertList.toString());
        });
    }


    public static void main1(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\wenzheng.chen\\Desktop\\request_id.json");
        File file = new File("C:\\Users\\wenzheng.chen\\Desktop\\request_id.json");
        List<String> gbk = Files.readLines(file, Charset.forName("UTF-8"));
        System.out.println(gbk.size());
        ExecutorService es = Executors.newFixedThreadPool(200);
        for (int i = 0; i < gbk.size(); i++) {
            String mm = gbk.get(i);
            es.execute(new Runnable() {
                @Override
                public void run() {
                    String msgId = mm;
                    String resp = RestClient.client().postForObject(
                            "",
                            null,
                            String.class,
                            msgId, msgId);

                }
            });
        }
        es.shutdown();
    }
}
