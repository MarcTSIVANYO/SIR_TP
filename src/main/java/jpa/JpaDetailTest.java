package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.CriteriaQuery;

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

public class JpaDetailTest {

	/**
	 * @param args
	 */

	private EntityManager manager;

	public JpaDetailTest(EntityManager manager) {
		this.manager = manager;
	}
	public static void main(String[] args) {
		EntityManagerFactory factory =   
	 			 Persistence.createEntityManagerFactory("mysql");
			EntityManager manager = factory.createEntityManager();
			JpaDetailTest test = new JpaDetailTest(manager);
			
			 /*CriteriaBuilder cb = factory.getCriteriaBuilder(); 
			  CriteriaQuery<Tag> q = cb.createQuery(Tag.class);
			  Root<Tag> c = q.from(Tag.class);
			  q.select(c);*/
			   
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			try {
				//test.listUser(); 
				//test.listFiche();
				test.listKanban();
			} catch (Exception e) {
				e.printStackTrace();
			}
			tx.commit();

				
	   	 manager.close();
			System.out.println(".. done");
		 
	}
	
	private void listKanban() {
		KanbanDao kanbanDao=new KanbanDao();
		List<Kanban> kanbans=kanbanDao.findAll();
		if(!kanbans.isEmpty()) {
			System.out.println("Liste des kanbans  ");
			for(Kanban kanban : kanbans) { 
				System.out.println("Kanban : " + kanban.toString());
				 for( Section associatedSection : kanban.getSections() ) {
		                System.out.println( associatedSection.toString() );
		            }
			}  
		}else {
			System.out.println("O Kanban dans la base  ");
		}
	} 
	
	private void listFiche() {
		FicheDao ficheDao=new FicheDao();
		List<Fiche> fiches=ficheDao.findAll();
		if(!fiches.isEmpty()) {
			System.out.println("Liste des fiches  ");
			for(Fiche fiche : fiches) { 
				System.out.println("Fiche : " + fiche.toString());
				 for( Tag associatedTag : fiche.getTags() ) {
		                System.out.println( associatedTag.toString() );
		            }
			}  
		}else {
			System.out.println("O Fiche dans la base  ");
		}
		
	}
	
	private void listUser() { 
		UserDao userDao=new UserDao();
		List<User> users=userDao.findAll();
		if(!users.isEmpty()) {
			System.out.println("Liste des utilisateurs ");
			for(User user : users) { 
				System.out.println("User : " + user.toString());
			}  
		}else {
			System.out.println("O users dans la base  ");
		}
	}
	
	 


}