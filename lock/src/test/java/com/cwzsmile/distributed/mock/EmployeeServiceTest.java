package com.cwzsmile.distributed.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * @author csh9016
 * @date 2020/9/24
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
//@PrepareForTest(EmployeeUtils.class)
public class EmployeeServiceTest {

    @Test
    public void getTotalEmployeeWithMock() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.when(employeeDao.getTotal()).thenReturn(10);

        final EmployeeService employeeService = new EmployeeService(employeeDao);
        int total = employeeService.getTotalEmployee();
        assertEquals(10, total);
    }

    @Test
    public void getTotalEmployee() {
        final EmployeeDao employeeDao = new EmployeeDao();
        final EmployeeService employeeService = new EmployeeService(employeeDao);
        int total = employeeService.getTotalEmployee();
        assertEquals(10, total);
    }

    @Test
    public void crateEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        Employee employee = new Employee();
        PowerMockito.doNothing().when(employeeDao).addEmployee(employee);

        final EmployeeService employeeService = new EmployeeService(employeeDao);
        //employeeService.crateEmployee(employee);
        //检验方法是否调用
        Mockito.verify(employeeDao).addEmployee(employee);
    }

    @Test
    public void getTotalEmployeeNest() {
        final EmployeeService employeeService = new EmployeeService();
        int total = employeeService.getTotalEmployeeNest();
        assertEquals(10, total);
    }

    @Test
    public void getTotalEmployeeNestWithMock() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PowerMockito.when(employeeDao.getTotal()).thenReturn(10);

        final EmployeeService employeeService = new EmployeeService();
        int total = employeeService.getTotalEmployeeNest();
        assertEquals(10, total);
    }

    @Test
    public void crateEmployeeNest() {
        EmployeeService employeeService = new EmployeeService();
        employeeService.crateEmployeeNest(new Employee());
    }

    @Test
    public void crateEmployeeNestWithMock() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            //录制@PrepareForTest(EmployeeService.class)，内的对象创建行为
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmployeeService employeeService = new EmployeeService();
        Employee employee = new Employee();
        employeeService.crateEmployeeNest(employee);
        Mockito.verify(employeeDao).addEmployee(employee);
    }

    @Test
    public void getTotalEmployeeStatic() {
        final EmployeeService employeeService = new EmployeeService();
        int total = employeeService.getTotalEmployeeStatic();
        assertEquals(10, total);
    }

    @Test
    public void getTotalEmployeeStaticWithMock() {
        PowerMockito.mockStatic(EmployeeUtils.class);
        PowerMockito.when(EmployeeUtils.getEmployeeCount()).thenReturn(10);

        final EmployeeService employeeService = new EmployeeService();
        int total = employeeService.getTotalEmployeeStatic();
        assertEquals(10, total);
    }

    @Test
    public void crateEmployeeStatic() {
        EmployeeService employeeService = new EmployeeService();
        employeeService.crateEmployeeStatic(new Employee());
    }

    @Test
    public void crateEmployeeStaticWithMock() {
        PowerMockito.mockStatic(EmployeeUtils.class);
        Employee employee = new Employee();
        PowerMockito.doNothing().when(EmployeeUtils.class);

        EmployeeService employeeService = new EmployeeService();
        employeeService.crateEmployeeStatic(employee);
        PowerMockito.verifyStatic();
    }

    @Test
    public void saveOrUpdateEq0() {
        try {
            EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
            try {
                //录制@PrepareForTest(EmployeeService.class)，内的对象创建行为
                PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Employee employee = new Employee();
            PowerMockito.when(employeeDao.getCount(employee)).thenReturn(0L);
            EmployeeService employeeService = new EmployeeService();
            employeeService.saveOrUpdate(employee);
            Mockito.verify(employeeDao).saveEmployee(employee);
            Mockito.verify(employeeDao,Mockito.never()).updateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void saveOrUpdateGt0() {
        try {
            EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
            try {
                //录制@PrepareForTest(EmployeeService.class)，内的对象创建行为
                PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Employee employee = new Employee();
            PowerMockito.when(employeeDao.getCount(employee)).thenReturn(1L);
            EmployeeService employeeService = new EmployeeService();
            employeeService.saveOrUpdate(employee);
            Mockito.verify(employeeDao).updateEmployee(employee);
            Mockito.verify(employeeDao,Mockito.never()).saveEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
