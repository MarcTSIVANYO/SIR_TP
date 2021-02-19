package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.kanban.entity.Kanban;

public class JpaInitTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManagerFactory factory =   
	 			 Persistence.createEntityManagerFactory("postgresAzure");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();


		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}
	 
}
