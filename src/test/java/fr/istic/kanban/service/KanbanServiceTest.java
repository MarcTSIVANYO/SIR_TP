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

import fr.istic.kanban.dao.KanbanDao;
import fr.istic.kanban.dto.KanbanDto;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.User;
import fr.istic.kanban.service.KanbanService;

public class KanbanServiceTest {

	private KanbanService kanbanService = mock(KanbanService.class);
	
	private KanbanDao kanbanDao = mock(KanbanDao.class);
	
	@Before public void initMocks() { 
		MockitoAnnotations.initMocks(this); 
	}
	 
	@Test
    public void getAllTest()
    {

        List<Kanban> list = new ArrayList<Kanban>();
        List<KanbanDto> listDto = new ArrayList<KanbanDto>();
        
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
		
        Kanban kanbanOne=new Kanban();
        kanbanOne.setId(2L);
        kanbanOne.setNom("Google");
        kanbanOne.setAdmin(user); 
		
		Kanban kanbanTwo=new Kanban();
		kanbanTwo.setId(3L);
		kanbanTwo.setNom("Facebook"); 
		kanbanTwo.setAdmin(user); 
         
        list.add(kanbanOne);
        list.add(kanbanTwo); 
         
        KanbanDto kanbanOneDto=new KanbanDto(kanbanOne.getId(),kanbanOne.getNom(),kanbanOne.getAdmin());
        KanbanDto kanbanTwoDto=new KanbanDto(kanbanTwo.getId(),kanbanTwo.getNom(),kanbanTwo.getAdmin());
        listDto.add(kanbanOneDto);
        listDto.add(kanbanTwoDto);
        
        when(kanbanDao.findAll()).thenReturn(list);
        
        when(kanbanService.findAll()).thenReturn(listDto);
         
        //test
        List<KanbanDto> kanbanDtoList = kanbanService.findAll();
         
        assertEquals(2, kanbanDtoList.size());
        verify(kanbanService, times(1)).findAll();
    }
	
	@Test
    public void getByIdTest()
    {
 
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
		
        Kanban kanban=new Kanban();
        kanban.setId(2L);
        kanban.setNom("Google");
        kanban.setAdmin(user);  
        KanbanDto kanbanDto=new KanbanDto(kanban.getId(),kanban.getNom(),kanban.getAdmin()); 
       
        when(kanbanService.getById(2L)).thenReturn(kanbanDto);
         
        //test
        KanbanDto kanbanDtoResult = kanbanService.getById(2L); 
        assertEquals("Google", kanbanDtoResult.getNom());
        assertEquals("Marc TSIVANYO", kanbanDtoResult.getAdmin().getName());
        assertEquals("marctsivanyo@gmail.com", kanbanDtoResult.getAdmin().getEmail());
    }
	
	 @Test
	public void saveTest()
    {
		 User user =new User();
		 user.setEmail("marctsivanyo@gmail.com");
		 user.setName("Marc TSIVANYO");
		
        Kanban kanban=new Kanban();
        kanban.setId(2L);
        kanban.setNom("Google");
        kanban.setAdmin(user);  
        KanbanDto kanbanDto=new KanbanDto(kanban.getId(),kanban.getNom(),kanban.getAdmin()); 
        
        kanbanService.save(kanbanDto); 
        verify(kanbanService, times(1)).save(kanbanDto);
    }
	 
	 @Test
	public void deleteTest()
    {
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
		
        Kanban kanban=new Kanban();
        kanban.setId(2L);
        kanban.setNom("Google");
        kanban.setAdmin(user);  
        KanbanDto kanbanDto=new KanbanDto(kanban.getId(),kanban.getNom(),kanban.getAdmin()); 
       
         when(kanbanService.getById(kanbanDto.getId())).thenReturn(kanbanDto).thenReturn(null);
        //test
        kanbanService.deleteById(kanbanDto.getId()); 
        verify(kanbanService, times(1)).deleteById(kanbanDto.getId());
    }
}
