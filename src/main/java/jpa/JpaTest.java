package jpa;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import fr.istic.kanban.dao.FicheDao;
import fr.istic.kanban.dao.KanbanDao;
import fr.istic.kanban.dao.SectionDao;
import fr.istic.kanban.dao.TagDao;
import fr.istic.kanban.dao.UserDao;
import fr.istic.kanban.entity.Fiche;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.Tag;
import fr.istic.kanban.entity.User;

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
		
		//User user = (User) manager.createQuery("Select u from User u where u.email='marctsivanyo@gmail.com'", User.class).getResultList();
		
		
		
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");  
		manager.persist(user);

		Tag coffee=new Tag("Coffee",null);
		Tag tea=new Tag("Tea",null);
		Tag web=new Tag("Web",null);
		Tag formation=new Tag("Formation",null);
		Tag business=new Tag("Businness",null); 
		manager.persist(business);
		 
			Kanban kan=new Kanban();
			kan.setNom("Google");
			kan.setAdmin(user); 
			
			Kanban kan2=new Kanban();
			kan2.setNom("Facebook"); 
			kan2.setAdmin(user); 

			manager.persist(kan);
			manager.persist(kan2);
			
			Section sectionOne = new Section("En attente",1,kan); 
			
			Section sectionTwo =new Section(); 
			sectionTwo.setLibelle("En cours");
			sectionTwo.setPosition(2); 
			sectionTwo.setKanban(kan);

			Section sectionTree =new Section(); 
			sectionTree.setLibelle("Réalisé");
			sectionTree.setPosition(3); 
			sectionTwo.setKanban(kan2);  
			
			manager.persist(sectionOne);
			manager.persist(sectionTwo);
			manager.persist(sectionTree);
			 
			ArrayList<Tag> tags = new ArrayList() {{
				 add(coffee);
				 add(tea); 
				 add(formation); 
				}};
				for(Tag tag : tags) manager.persist(tag);

				Fiche fiche = new Fiche();
				fiche.setLibelle("Wait answer to request at Manager");
				fiche.setLieu("At work");
				fiche.setOwner(user);//User has to be persisted before persisting the Fiche
				fiche.setNote("Prioritaire");
				fiche.setSection(sectionOne);
				fiche.setUrl("https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#:~:text=All%20ManyToMany%20relationships%20require%20a,object's%20primary%20key%20(%20inverseJoinColumns%20).");
				fiche.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				fiche.setTags(tags);//Here the problem?
				

				manager.persist(fiche);
				
				/*Fiche thisFiche = manager.find( Fiche.class, 1L	 );            
	            System.out.println( "Tags associés à une fiche" );
	            for( Tag associatedTag : thisFiche.getTags() ) {
	                System.out.println( associatedTag );
	            }*/
 
	}

	private void listEmployees() {
		List<Kanban> resultList = manager.createQuery("Select a From Kanban a", Kanban.class).getResultList();
		System.out.println("num of Kanbans:" + resultList.size());
		for (Kanban next : resultList) {
			System.out.println("next kanban: " + next);
		}
	}
}

 