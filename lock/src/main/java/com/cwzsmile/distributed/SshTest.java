package com.cwzsmile.distributed;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;

/**
 * @author csh9016
 * @date 2020/9/7
 */
public class SshTest {

    private static final String user = "";
    private static final String host = "";
    private static final int port = 22;
    private static final String password = "";


    private static final int lport = 3305;
    private static final String rhost = "";
    private static final int rport = 3306;

    public static void main(String[] args) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            // step1：建立ssh 连接
            session.connect();

            //step2：设置SSH本地端口转发，本地转发到远程
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);

            //step3：查数据库
        } catch (Exception e) {
            if (null != session) {
                //关闭ssh连接
                session.disconnect();
            }
            e.printStackTrace();
        }
    }

    public static void sql() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.101:5555/mysql", "hadoop", "xxx");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select 1");
        System.out.println("输出结果");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }


}
