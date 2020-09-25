package com.cwzsmile.distributed.mock;

/**
 * @author csh9016
 * @date 2020/9/24
 */
public class EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeService() {
    }

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public String findEmailByUserName(String username) {
        throw new UnsupportedOperationException();
    }

    public int getTotalEmployee() {
        return employeeDao.getTotal();
    }

    public int getTotalEmployeeNest() {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.getTotal();
    }

    public int getTotalEmployeeStatic() {
        return EmployeeUtils.getEmployeeCount();
    }


    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    public void createEmployeeFinal(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    public void createEmployeeFinalCons(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao(false,EmployeeDao.Dialect.MYSQL);
        employeeDao.insertEmployee(employee);
    }

    public void crateEmployeeNest(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.addEmployee(employee);
    }

    public void crateEmployeeStatic(Employee employee) {
        EmployeeUtils.persistenceEmployee(employee);
    }

    public void saveOrUpdate(Employee employee) {
        final EmployeeDao employeeDao = new EmployeeDao();
        long count = employeeDao.getCount(employee);
        if (count > 0) {
            employeeDao.updateEmployee(employee);
        }else {
            employeeDao.saveEmployee(employee);
        }
    }
}
