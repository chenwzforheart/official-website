package com.cwzsmile.distributed.mock;

/**
 * @author csh9016
 * @date 2020/9/25
 */
public class EmployeeController {

    public String getEmail(final String userName) {
        EmployeeService employeeService = new EmployeeService();
        String email = employeeService.findEmailByUserName(userName);
        return email;
    }
}
