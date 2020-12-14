package com.cwzsmile.distributed.transaction;

import sun.reflect.CallerSensitive;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author csh9016
 * @date 2020/12/14
 */
public class DriverTest {

    private static String url = "jdbc:mysql://iiidid:8066/db?serverTimezone=UTC&useSSL=false";
    private static String user = "user";
    private static String password = "pass";
    private static String driverClass = "com.mysql.jdbc.Driver";


    public static void main(String[] args) throws Exception {

        URL resource = DriverTest.class.getClassLoader().getResource("classpath:/lib/mysql-connector-java-5.1.35.jar");
        MyClassloader loader = new MyClassloader(new URL[]{resource});
        Driver driver = (Driver) loader.loadClass(driverClass).newInstance();

        Driver driver1 = (Driver) DriverTest.class.getClassLoader().loadClass(driverClass).newInstance();

        testSwitch(driver);
        testSwitch(driver1);
    }

    public static void testSwitch(Driver driver) throws SQLException {
        java.util.Properties info = new java.util.Properties();
        if (user != null) {
            info.put("user", user);
        }
        if (password != null) {
            info.put("password", password);
        }
        Connection connect = driver.connect(url, info);
        if (connect != null) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    public static void testSwitchManager(Driver driver) throws SQLException {
        java.util.Properties info = new java.util.Properties();
        if (user != null) {
            info.put("user", user);
        }
        if (password != null) {
            info.put("password", password);
        }
        Connection connect = DriverManager.getConnection(url, info);
        if (connect != null) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }
}
