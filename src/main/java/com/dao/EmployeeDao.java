package main.java.com.dao;

import main.java.com.model.Employee;

import java.util.List;

/**
 * Created by gayathri on 6/7/17.
 */
public interface EmployeeDao {

    public void configuration();

    public void save(Employee employee);

    public List<Employee> fetchData();
}
