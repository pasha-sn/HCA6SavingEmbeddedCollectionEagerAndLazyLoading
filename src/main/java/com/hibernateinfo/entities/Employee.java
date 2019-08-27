package com.hibernateinfo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.hibernateinfo.model.Address;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
@Entity
@Table(name="employee_table")
//Update the given value not all fields.  
//https://stackoverflow.com/questions/41633250/how-dynamic-update-true-works-internally-in-hibernate
//https://www.mkyong.com/hibernate/hibernate-dynamic-update-attribute-example/
@DynamicUpdate   
public class Employee 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employee_id")
	private Integer employeeId;
	
	@Column(name="employee_name",length=100, nullable=false )
	private String employeeName;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="date_of_join")
	private Date doj;
	
	//@ElementCollection is mainly for mapping non-entities (embedded or basic) 
	//The relation is (only) managed by the entity in which the relation is defined.
	//Table contains id reference to the owning entity plus basic or embedded attributes.
	//@OneToMany can also be managed by the other entity
	//Join table or column(s) typically contains id references only.
	//https://stackoverflow.com/questions/8969059/difference-between-onetomany-and-elementcollection
	@ElementCollection
	@JoinTable(
	        name = "address_table",
	        joinColumns = @JoinColumn(name="employee_id")
	        )
	//Create sequence generator
	//strategy increment or seqhilo could be used with oracle database 
	@GenericGenerator(name = "sequence-gen", strategy = "increment")
	/**
	 * @author Pasha
	 * 
	 * You can use your modified sequenceGenerator instead of genericGenerator
	 * @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence-gen") 
	 * @SequenceGenerator(
	 * 						name = "sequence-gen", 
	 * 						initialValue=18, 
	 * 						allocationSize=3, 
	 * 						sequenceName="ID_SEQ_INIT_18_INCR_3" 
	 * 						)
	 */	
    //Add primary key to address_table
	@CollectionId(
			columns = { @Column(name="address_id") }, 
			generator = "sequence-gen", 
			type = @Type(type = "int")
			)
	private List<Address> addressList = new ArrayList<>();
	
	@Column(name="salary")
	private Double salary;
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", salary=" + salary + "]";
	}	
}