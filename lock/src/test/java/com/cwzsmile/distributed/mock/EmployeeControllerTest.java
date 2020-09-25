package com.cwzsmile.distributed.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * @author csh9016
 * @date 2020/9/25
 * new 是mock外层类，static 和 final 需要加上当前类
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EmployeeController.class})
public class EmployeeControllerTest {

    @Test
    public void getEmail() {

        EmployeeService employeeService = PowerMockito.mock(EmployeeService.class);
        //ArgumentMatcher 替代when ... thenReturn
        PowerMockito.when(employeeService.findEmailByUserName(Mockito.argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object argument) {
                String arg = (String) argument;
                if (arg.startsWith("wangwenjun") || arg.startsWith("wwj")) {
                    return true;
                } else {
                    throw new RuntimeException();
                }
            }
        }))).thenReturn("wangwenjun@gmail.com");

        try {
            PowerMockito.whenNew(EmployeeService.class).withAnyArguments().thenReturn(employeeService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmployeeController employeeController = new EmployeeController();
        String email = employeeController.getEmail("wangwenjun");
        assertEquals("wangwenjun@gmail.com", email);
    }

    @Test
    public void getEmailWithAns() {
        EmployeeService employeeService = PowerMockito.mock(EmployeeService.class);
        PowerMockito.when(employeeService.findEmailByUserName(Mockito.anyString())).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                /**
                 invocation.callRealMethod();
                 invocation.getMock();
                 invocation.getMethod();
                 invocation.getArgumentAt(1, Object.class);
                 */
                String arg = (String) invocation.getArguments()[0];
                if ("wangwenjun".equals(arg)) {
                    return "wangwenjun@gmail.com";
                } else if ("liudehua".equals(arg)) {
                    return "andy@gmail.com";
                }
                throw new NullPointerException();
            }
        });
        try {
            PowerMockito.whenNew(EmployeeService.class).withAnyArguments().thenReturn(employeeService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmployeeController employeeController = new EmployeeController();
        String email = employeeController.getEmail("wangwenjun");
        assertEquals("wangwenjun@gmail.com", email);
        email = employeeController.getEmail("liudehua");
        assertEquals("andy@gmail.com", email);
        try {
            email = employeeController.getEmail("JackChen");
            fail("should not process to here.");
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }

    }
}
