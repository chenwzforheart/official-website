package com.cwzsmile.distributed.mock;

/**
 * @author csh9016
 * @date 2020/9/24
 */
public final class EmployeeDao {

    public enum Dialect{
        MYSQL,ORACLE
    }

    public EmployeeDao() {
    }

    public EmployeeDao(boolean lazy,Dialect dialect) {
        throw new UnsupportedOperationException();
    }

    public int getTotal() {
        throw new UnsupportedOperationException();
    }

    public void addEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public void saveEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public void updateEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public long getCount(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public boolean insertEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }
}
