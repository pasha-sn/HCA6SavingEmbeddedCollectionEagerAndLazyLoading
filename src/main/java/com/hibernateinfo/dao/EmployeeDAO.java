package com.hibernateinfo.dao;

import com.hibernateinfo.entities.Employee;

public interface EmployeeDAO 
{
	public abstract void addEmployee(Employee employee);
	public abstract Employee fetchEmployeeById(int employeeId);
	public abstract void updateEmployeeById(int employeeId, Double newSal);
	public abstract void deleteEmployeeById(Integer employeeId);
}
