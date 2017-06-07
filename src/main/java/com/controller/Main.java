package main.java.com.controller;

import main.java.com.model.Address;
import main.java.com.model.Certificate;
import main.java.com.model.Employee;
import main.java.com.service.AddressService;
import main.java.com.service.EmployeeService;
import main.java.com.service.impl.AddressServiceImpl;
import main.java.com.service.impl.EmployeeServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gayathri on 6/7/17.
 * Hibernate Example Project For one-to-many Mapping
 * one employee has many certificates
 */
public class Main {
    public static void main(String[] args){
        EmployeeService employeeService = new EmployeeServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        List<Employee> employeeList;

        /*Create a address object*/
        Address address = new Address("Melbern","Nugegoda",
                "Colombo", "10100");

        /* Set up hibernate configurations*/
        addressService.configuration();

        /*Save new address*/
        addressService.save(address);

        /*Certificate set for employee1*/
        HashSet employee1CertificateSet = new HashSet();
        employee1CertificateSet.add(new Certificate("MCA"));
        employee1CertificateSet.add(new Certificate("MBA"));
        employee1CertificateSet.add(new Certificate("PMP"));

        /*Create a Employee Object*/
        Employee employee1 = new Employee("Wickramage","Sugath",
                21000, address);

        /*Add certificate set for employee1*/
        employee1.setCertificates(employee1CertificateSet);


         /*Certificate set for employee2*/
        HashSet employee2CertificateSet = new HashSet();
        employee2CertificateSet.add(new Certificate("BCA"));
        employee2CertificateSet.add(new Certificate("BA"));

        /*Create a Employee Objects*/
        Employee employee2 = new Employee("Prabath","Wimalasurandra",
                        21000, address);

        /*Add certificate set for employee2*/
        employee2.setCertificates(employee2CertificateSet);

        /* Set up hibernate configurations*/
        employeeService.configuration();

        /*Save new employees*/
        employeeService.save(employee1);
        employeeService.save(employee2);

         /*
        Fetching data
         */
        employeeList = employeeService.fetchData();
        System.out.println();
        for(Employee employee: employeeList){
            System.out.println("ID -: " +employee.getId());
            System.out.println("First Name -: " +employee.getFirstName());
            System.out.println("Last Name -: " +employee.getLastName());
            System.out.println("Salary -: " +employee.getSalary());

            Address employeeAddress = employee.getAddress();
            System.out.println("Address -:  ");
            System.out.println("\tStreet Name -: "+employeeAddress.getStreetName());
            System.out.println("\tCity Name -: "+employeeAddress.getCityName());
            System.out.println("\tState Name -: "+employeeAddress.getStateName());
            System.out.println("\tZip Code-: "+employeeAddress.getZipCode());

            Set<Certificate> certificates = employee.getCertificates();
            System.out.println("Certificates -:  ");

            for(Certificate certificate: certificates){
                System.out.println("\t"+certificate.getName());
            }


            System.out.println();
        }


    }
}
