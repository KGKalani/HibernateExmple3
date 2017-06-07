package main.java.com.service;


import main.java.com.model.Employee;

import java.util.List;

/**
 * Created by gayathri on 6/7/17.
 */
public interface EmployeeService {

    public void configuration();

    public void save(Employee employee);

    public List<Employee> fetchData();
}
