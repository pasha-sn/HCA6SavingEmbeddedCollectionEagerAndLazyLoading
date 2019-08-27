package com.hibernateinfo.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.model.Address;
import com.hibernateinfo.service.EmployeeService;
import com.hibernateinfo.service.impl.EmployeeServiceImpl;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest1CreateEmployeeWithEmbeddedCollection {

	public static void main(String[] args) 
	{
		EmployeeService employeeService = new EmployeeServiceImpl();
		createEmployee(employeeService);	
		
	}
	
	private static void createEmployee(EmployeeService employeeService) {
		employeeService.createEmployee(getEmployee());
	}
	private static Employee getEmployee(){
		Employee employee= new Employee();
		employee.setEmployeeName("Pasha Sadi");
		employee.setEmail("pasha.sn@gmail.com");
		employee.setSalary(65000.00);
		employee.setDoj(new Date());
		
		Address address1 = new Address();
		address1.setStreet("Peel");
		address1.setCity("Montreal");
		address1.setState("Quebec");
		address1.setPostalcode(19317l);
		
		Address address2 = new Address();		
		address2.setStreet("Rue de la Montagne");
		address2.setCity("Montreal");
		address2.setState("Quebec");
		address2.setPostalcode(19318l);
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(address1);
		addressList.add(address2);
		
		employee.setAddressList(addressList);
		
		return employee;
	}	
}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
Hibernate: 
    
    create table address_table (
       employee_id number(10,0) not null,
        city_name varchar2(50 char),
        postal_code number(19,0),
        state_name varchar2(255 char),
        street_name varchar2(50 char),
        address_id number(10,0) not null,
        primary key (address_id)
    )
Hibernate: 
    
    create table employee_table (
       employee_id number(10,0) not null,
        date_of_join timestamp,
        email varchar2(255 char),
        employee_name varchar2(100 char) not null,
        salary double precision,
        primary key (employee_id)
    )
Hibernate: 
    
    alter table employee_table 
       drop constraint UK_2casspobvavvi9s23312f9mhm
Hibernate: 
    
    alter table employee_table 
       add constraint UK_2casspobvavvi9s23312f9mhm unique (email)
Hibernate: 
    
    alter table address_table 
       add constraint FKff2mf17w32xkm3emotdt6of59 
       foreign key (employee_id) 
       references employee_table
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Employee is created with Id:: 4
Hibernate: 
    insert 
    into
        employee_table
        (date_of_join, email, employee_name, salary, employee_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    select
        max(address_id) 
    from
        address_table
Hibernate: 
    insert 
    into
        address_table
        (employee_id, address_id, city_name, postal_code, state_name, street_name) 
    values
        (?, ?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (employee_id, address_id, city_name, postal_code, state_name, street_name) 
    values
        (?, ?, ?, ?, ?, ?)
*/