package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import dao.FicheDao;
import dao.KanbanDao;
import dao.SectionDao;
import dao.TagDao;
import dao.UserDao;
import entity.Kanban;
import entity.Section;
import entity.Tag;
import entity.User;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory =   
 			 Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createKanbans();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listEmployees();
			
   	 manager.close();
		System.out.println(".. done");
	}
	
	
	private void createKanbans() {
		int numOfKanbans = manager.createQuery("Select a From Section a", Section.class).getResultList().size();

		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");  
		manager.persist(user);
		
		if (numOfKanbans == 0) {
			Section sectionOne = new Section("En attente",1); 
			
			Section sectionTwo =new Section(); 
			sectionTwo.setLibelle("En cours");
			sectionTwo.setPosition(2); 

			Section sectionTree =new Section(); 
			sectionTree.setLibelle("Réalisé");
			sectionTree.setPosition(3); 
			
			
			manager.persist(sectionOne);
			manager.persist(sectionTwo);
			manager.persist(sectionTree);
			
			ArrayList sections = new ArrayList() {{
				 add(sectionOne);
				 add(sectionTwo);
				 add(sectionTree); 
				}};
				
			ArrayList sectionsTwo = new ArrayList() {{
				 add(sectionOne);
				 add(sectionTwo); 
				}};

				
				Kanban kan=new Kanban();
				kan.setNom("Marc");
				kan.setAdmin(user);
				kan.setSections(sections); 
				
				Kanban kan2=new Kanban();
				kan2.setNom("Jeux");
				//kan2.setSections(sectionsTwo); 
				kan2.setAdmin(user); 
				
			manager.persist(kan);
			manager.persist(kan2);

		}
	}

	private void listEmployees() {
		List<Kanban> resultList = manager.createQuery("Select a From Kanban a", Kanban.class).getResultList();
		System.out.println("num of Kanbans:" + resultList.size());
		for (Kanban next : resultList) {
			System.out.println("next kanban: " + next);
		}
	}
}

 