package com.cwzsmile.distributed.base.http;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
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
        final String url = "http://hippo.baozun.com/backend/api/sqlOptimization/execute/sql?databaseSchemaid=2626&code=6u2vd5";

        String header = "Host: hippo.baozun.com\n" +
                "Connection: keep-alive\n" +
                "Content-Length: 147\n" +
                "Accept: application/json, text/plain, */*\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3941.4 Safari/537.36\n" +
                "Content-Type: text/plain;charset=UTF-8\n" +
                "Origin: http://hippo.baozun.com\n" +
                "Referer: http://hippo.baozun.com/\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9,en;q=0.8\n" +
                "Cookie: s_nr=1578970086615-New; AMCV_9E1005A551ED61CA0A490D45%40AdobeOrg=1585540135%7CMCMID%7C06391800604438846931300746062890698815%7CMCAAMLH-1579574916%7C11%7CMCAAMB-1579574916%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1578977317s%7CNONE%7CMCAID%7CNONE%7CMCCIDH%7C-906997846%7CvVersion%7C4.4.0; Hm_lvt_977549620e7343d16692268282cad421=1584063531; _ga=GA1.2.2003662489.1584063532; JSESSIONID=88CDA82750F35ACE2AA983C3CBE5C453";

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
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/net_openapi?characterEncoding=UTF-8&amp;useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        for (int i = 1; i <= 31; i++) {
            System.out.println(String.format("201908%02d", i));
            dayWork(url, requestHeaders, jdbcTemplate, String.format("201908%02d", i));
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

    public static void dayWork(String url, HttpHeaders requestHeaders, JdbcTemplate jdbc, String day) {
        String sql = String.format("select a.body,a.create_time,b.reciver from sms_send_log_%s a\n" +
                "left join sms_send_reciver_log_%s b on b.ssl_id = a.id where a.customer_id =44;", day, day);

        String insert = "insert into sms_detail(content,mobile,create_time,length) values ";
        HttpEntity<String> requestEntity = new HttpEntity<String>(sql, requestHeaders);
        List<List<String>> body = RestClient.client().exchange(url, HttpMethod.POST, requestEntity, List.class).getBody();

        if (body.size() == 1) {
            System.out.println(day + "无数据");
            return;
        }

        Lists.partition(body, 100).forEach(s -> {
            StringBuilder insertList = new StringBuilder();
            s.forEach(ss -> {
                if (!"a.body".equals(ss.get(0))) {
                    insertList.append(String.format("('%s','%s','%s',%d)", ss.get(0), ss.get(2), ss.get(1), countMessageLength(ss.get(0))));
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
