package fr.istic.kanban.service;

import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import fr.istic.kanban.dao.FicheDao;
import fr.istic.kanban.dto.FicheDto;
import fr.istic.kanban.dto.UserDto;
import fr.istic.kanban.entity.Fiche;
import fr.istic.kanban.entity.User;

public class FicheServiceTest {
private FicheService ficheService = mock(FicheService.class);
	
	private FicheDao ficheDao = mock(FicheDao.class);
	
	private Long sectionID;
	
	@Before public void initMocks() { 
		MockitoAnnotations.initMocks(this); 
		sectionID=2L;
	}
	 
	@Test
    public void getAllTest()
    {
        List<Fiche> list = new ArrayList<Fiche>();
        List<FicheDto> listDto = new ArrayList<FicheDto>();
        
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
		
        Fiche ficheOne=new Fiche();
        ficheOne.setId(2L);
        ficheOne.setLibelle("Google");
        ficheOne.setLieu("Rennes");
        ficheOne.setNote("Test");
        ficheOne.setOwner(user);
		
		Fiche ficheTwo=new Fiche();
		ficheTwo.setId(3L); 
		ficheTwo.setLibelle("Facebook");
        ficheTwo.setLieu("Angers");
        ficheTwo.setNote("Test");
        ficheTwo.setOwner(user);
         
        list.add(ficheOne);
        list.add(ficheTwo); 
         
        FicheDto ficheOneDto=new FicheDto(ficheOne.getId(),ficheOne.getLibelle(),ficheOne.getLieu(), ficheOne.getUrl(), ficheOne.getDateButoire(), ficheOne.getUrl(),this.sectionID, new UserDto(ficheOne.getOwner().getEmail(), ficheOne.getOwner().getName()));
        FicheDto ficheTwoDto=new FicheDto(ficheTwo.getId(),ficheTwo.getLibelle(),ficheTwo.getLieu(), ficheTwo.getUrl(), ficheTwo.getDateButoire(), ficheTwo.getUrl(),this.sectionID, new UserDto(ficheTwo.getOwner().getEmail(), ficheTwo.getOwner().getName()));
        listDto.add(ficheOneDto);
        listDto.add(ficheTwoDto);
        
        when(ficheDao.findAll()).thenReturn(list);
        
        when(ficheService.findAll()).thenReturn(listDto); 
        //test
        List<FicheDto> ficheDtoList = ficheService.findAll(); 
        assertEquals(2, ficheDtoList.size());
        verify(ficheService, times(1)).findAll();
    }
	
	@Test
    public void getByIdTest()
    {
 
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
		
        Fiche fiche=new Fiche();fiche.setId(2L);
        fiche.setLibelle("Google");
        fiche.setLieu("Rennes");
        fiche.setNote("Test");
        fiche.setOwner(user); 
        FicheDto ficheDto=new FicheDto(fiche.getId(),fiche.getLibelle(),fiche.getLieu(), fiche.getUrl(), fiche.getDateButoire(), fiche.getUrl(),this.sectionID, new UserDto(fiche.getOwner().getEmail(), fiche.getOwner().getName()));
       
        when(ficheService.getById(2L)).thenReturn(ficheDto); 
        //test
        FicheDto ficheDtoResult = ficheService.getById(2L); 
        assertEquals(fiche.getLibelle(), ficheDtoResult.getLibelle());
        assertEquals(user.getName(), ficheDtoResult.getOwner().getName());
        assertEquals(user.getEmail(), ficheDtoResult.getOwner().getEmail());
    }
	
	 @Test
	public void saveTest()
    {
		 User user =new User();
		 user.setEmail("marctsivanyo@gmail.com");
		 user.setName("Marc TSIVANYO");
		
        Fiche fiche=new Fiche();
        fiche.setId(2L);
        fiche.setLibelle("Google");
        fiche.setLieu("Rennes");
        fiche.setNote("Test");
        fiche.setOwner(user); 
        FicheDto ficheDto=new FicheDto(fiche.getId(),fiche.getLibelle(),fiche.getLieu(), fiche.getUrl(), fiche.getDateButoire(), fiche.getUrl(), this.sectionID, new UserDto(fiche.getOwner().getEmail(), fiche.getOwner().getName()));
        
        ficheService.save(ficheDto); 
        verify(ficheService, times(1)).save(ficheDto);
    }
	 
	 @Test
	public void deleteTest()
    {
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
		
        Fiche fiche=new Fiche();
        fiche.setId(2L);
        fiche.setLibelle("Google");
        fiche.setLieu("Rennes");
        fiche.setNote("Test");
        fiche.setOwner(user); 
        FicheDto ficheDto=new FicheDto(fiche.getId(),fiche.getLibelle(),fiche.getLieu(), fiche.getUrl(), fiche.getDateButoire(), fiche.getUrl(), this.sectionID, new UserDto(fiche.getOwner().getEmail(), fiche.getOwner().getName()));
        
         when(ficheService.getById(ficheDto.getId())).thenReturn(ficheDto).thenReturn(null);
        //test
        ficheService.deleteById(ficheDto.getId()); 
        verify(ficheService, times(1)).deleteById(ficheDto.getId());
    }
}
