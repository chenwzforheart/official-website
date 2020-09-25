package com.cwzsmile.distributed.mock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author csh9016
 * @date 2020/9/25
 */
public class FileService {
    public void write(final String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/wangwenjun.txt"));) {

            bw.write(text);
            bw.flush();
            System.out.println("content write successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean exist(String username) {
        checkExist(username);
        return true;
    }

    private void checkExist(String username) {
        throw new UnsupportedOperationException();
    }
}
