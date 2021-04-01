package jpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import fr.istic.kanban.dao.FicheDao;
import fr.istic.kanban.dao.KanbanDao;
import fr.istic.kanban.dao.SectionDao;
import fr.istic.kanban.dao.TagDao;
import fr.istic.kanban.dao.UserDao;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.Tag;
import fr.istic.kanban.entity.User;

public class JpaTest2 {


	public static void main(String[] args) {

		UserDao userDao=new UserDao();
		SectionDao sectionDao=new SectionDao();
		KanbanDao kanDao=new KanbanDao();
		FicheDao ficheDao=new FicheDao();
		TagDao 	tagDao=new  TagDao();
		 		
		try {
			User user =new User();
			user.setEmail("marctsivanyo@gmail.com");
			user.setName("Marc TSIVANYO");
			userDao.save(user);

			
			
			Section sectionOne =new Section(); 
			sectionOne.setLibelle("En attente");
			sectionOne.setPosition(1); 

			Section sectionTwo =new Section(); 
			sectionTwo.setLibelle("En cours");
			sectionTwo.setPosition(10); 

			Section sectionTree =new Section(); 
			sectionTree.setLibelle("R�alis�");
			sectionTree.setPosition(8); 

			sectionDao.save(sectionOne);
			sectionDao.save(sectionTwo);
			sectionDao.save(sectionTree);
			
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
			kanDao.save(kan);
			
			Kanban kan2=new Kanban();
			kan2.setNom("Jeux");
			kan2.setAdmin(user);
			//kan2.setSections(sections);
			kanDao.save(kan2);
			
			
			Tag tagOne =new Tag(); 
			tagOne.setName("Cours"); 
			
			Tag tag2 =new Tag(); 
			tag2.setName("Repetition"); 
			
			Tag tag3 =new Tag(); 
			tag3.setName("Jeux"); 
			
			Tag tag4 =new Tag(); 
			tag4.setName("Decisions"); 

			tagDao.save(tagOne);
			tagDao.save(tag2);
			tagDao.save(tag3);
			tagDao.save(tag4);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	 


}