package com.cwzsmile.distributed.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author csh9016
 * @date 2020/9/25
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FileService.class})
public class FileServiceTest {

    @Test
    public void write() {
        FileService fileService = PowerMockito.mock(FileService.class);
        fileService.write("liudehua");
    }

    @Test
    public void writeSpy() {
        FileService fileService = PowerMockito.spy(new FileService());
        fileService.write("liudehua");
        File file = new File(System.getProperty("user.dir") + "/wangwenjun.txt");
        assertTrue(file.exists());

        try {
            PowerMockito.doNothing().when(fileService,"checkExist","wangwenjun");
            boolean result = fileService.exist("wangwenjun");
            assertTrue(result);
            PowerMockito.verifyPrivate(fileService).invoke("checkExist", "wangwenjun");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


}
