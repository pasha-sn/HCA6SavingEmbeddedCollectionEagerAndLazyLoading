package com.hibernateinfo.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil 
{
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	//static block because we want this code to be executed once(sessionFactory object is very expensive)
	//static block is common for all clients
	static 
	{
		try {
			if (sessionFactory == null) {
				//create standardServiceRegistry
				standardServiceRegistry = new StandardServiceRegistryBuilder()
						.configure()
						.build();
				//create MetadataSources
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				//create Metadata
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				//create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} 
		} catch (Exception e) {
			if(standardServiceRegistry !=null)
			{
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
		
	}
	//Utility method to return sessionFactory
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;		
	}
}
