package com.hibernateinfo.client;

import org.hibernate.Session;
import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest2GetEmployeeWithEmbeddedCollection {

	public static void main(String[] args) 
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = session.get(Employee.class, 5);
			System.out.println("***************************************************");
			System.out.println("***************************************************");
			System.out.println("Employee lazy FETCH Default Setting: " + employee);
			System.out.println("***************************************************");
			System.out.println("***************************************************");
			System.out.println("Hibernate will trigger SQL statement when we ask for addressList");
			System.out.println("***************************************************");
			System.out.println("***************************************************");
			
			if(employee !=null) {
				employee.getAddressList().forEach(System.out::println);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
Hibernate: 
    select
        employee0_.employee_id as employee_id1_1_0_,
        employee0_.date_of_join as date_of_join2_1_0_,
        employee0_.email as email3_1_0_,
        employee0_.employee_name as employee_name4_1_0_,
        employee0_.salary as salary5_1_0_ 
    from
        employee_table employee0_ 
    where
        employee0_.employee_id=?
***************************************************
***************************************************
Employee lazy FETCH Default Setting: Employee [employeeId=5, employeeName=Pasha Sadi, email=pasha.sn@gmail.com, doj=2019-01-11 12:00:00.000, salary=65000.0]
***************************************************
***************************************************
Hibernate will trigger SQL statement when we ask for addressList
***************************************************
***************************************************
Hibernate: 
    select
        addresslis0_.employee_id as employee_id1_0_0_,
        addresslis0_.city_name as city_name2_0_0_,
        addresslis0_.postal_code as postal_code3_0_0_,
        addresslis0_.state_name as state_name4_0_0_,
        addresslis0_.street_name as street_name5_0_0_,
        addresslis0_.address_id as address_id6_0_ 
    from
        address_table addresslis0_ 
    where
        addresslis0_.employee_id=?
Address [street=Peel, city=Montreal, state=Quebec, postalcode=19317]
Address [street=Rue de la Montagne, city=Montreal, state=Quebec, postalcode=19318]
*****************************************************
*****************************************************
@Entity
@Table(name="employee_table")
@DynamicUpdate   
public class Employee 
{
.
.
.
@ElementCollection(fetch=FetchType.EAGER)
private List<Address> addressList = new ArrayList<>();

Getters And Setters
}
*****************************************************
*****************************************************
*Hibernate: 
    select
        employee0_.employee_id as employee_id1_1_0_,
        employee0_.date_of_join as date_of_join2_1_0_,
        employee0_.email as email3_1_0_,
        employee0_.employee_name as employee_name4_1_0_,
        employee0_.salary as salary5_1_0_,
        addresslis1_.employee_id as employee_id1_0_1_,
        addresslis1_.city_name as city_name2_0_1_,
        addresslis1_.postal_code as postal_code3_0_1_,
        addresslis1_.state_name as state_name4_0_1_,
        addresslis1_.street_name as street_name5_0_1_,
        addresslis1_.address_id as address_id6_1_ 
    from
        employee_table employee0_ 
    left outer join
        address_table addresslis1_ 
            on employee0_.employee_id=addresslis1_.employee_id 
    where
        employee0_.employee_id=?
***************************************************
***************************************************
Employee EAGER FETCH Setting: Employee [employeeId=5, employeeName=Pasha Sadi, email=pasha.sn@gmail.com, doj=2019-01-11 12:00:00.000, salary=65000.0]
***************************************************
***************************************************
Hibernate will trigger SQL statement for addressList EAGERLY
***************************************************
***************************************************
Address [street=Peel, city=Montreal, state=Quebec, postalcode=19317]
Address [street=Rue de la Montagne, city=Montreal, state=Quebec, postalcode=19318]

*/