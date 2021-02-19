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

import fr.istic.kanban.dao.SectionDao;
import fr.istic.kanban.dto.SectionDto;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.User;
 

public class SectionServiceTest {

	private SectionService sectionService = mock(SectionService.class);
	
	private SectionDao sectionDao = mock(SectionDao.class);
	
	@Before public void initMocks() { 
		MockitoAnnotations.initMocks(this); 
	}
	 
	@Test
    public void getAllTest()
    {

        List<Section> list = new ArrayList<Section>();
        List<SectionDto> listDto = new ArrayList<SectionDto>();
        
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
		
        Kanban kanban=new Kanban();
        kanban.setId(2L);
        kanban.setNom("Google");
        kanban.setAdmin(user); 
		
		Section sectionOne =new Section(); 
		sectionOne.setLibelle("Nouveau");
		sectionOne.setPosition(1);
		sectionOne.setKanban(kanban);


		Section sectionTwo =new Section(); 
		sectionTwo.setLibelle("En cours");
		sectionTwo.setPosition(2);
		sectionTwo.setKanban(kanban);

		Section sectionTree =new Section(); 
		sectionTree.setLibelle("Terminé");
		sectionTree.setPosition(3);
		sectionTree.setKanban(kanban);

		list.add(sectionOne);
        list.add(sectionTwo); 
        list.add(sectionTree); 
         
        SectionDto sectionOneDto=new SectionDto(sectionOne.getId(),sectionOne.getLibelle(),sectionOne.getPosition(),sectionOne.getKanban());

        SectionDto sectionTwoDto=new SectionDto(sectionTwo.getId(),sectionTwo.getLibelle(),sectionTwo.getPosition(),sectionTwo.getKanban());

        SectionDto sectionTreeDto=new SectionDto(sectionTree.getId(),sectionTree.getLibelle(),sectionTree.getPosition(),sectionTree.getKanban());

        listDto.add(sectionOneDto);
        listDto.add(sectionTwoDto);
        listDto.add(sectionTreeDto);
        
        when(sectionDao.findAll()).thenReturn(list);
        
        when(sectionService.findAll()).thenReturn(listDto);
         
        //test
        List<SectionDto> sectionDtoList = sectionService.findAll();
         
        assertEquals(3, sectionDtoList.size());
        verify(sectionService, times(1)).findAll();
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
		
		Section section =new Section(); 
		section.setLibelle("Nouveau Test");
		section.setPosition(1);
		section.setKanban(kanban);

        SectionDto sectionDto=new SectionDto(section.getId(),section.getLibelle(),section.getPosition(),section.getKanban());

        when(sectionService.getById(2L)).thenReturn(sectionDto);
         
        //test
        SectionDto sectionDtoResult = sectionService.getById(2L); 
        assertEquals(section.getLibelle(), sectionDtoResult.getLibelle());
        assertEquals(section.getPosition(), sectionDtoResult.getPosition()); 
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
		
		Section section =new Section(); 
		section.setLibelle("Nouveau Test");
		section.setPosition(1);
		section.setKanban(kanban);

        SectionDto sectionDto=new SectionDto(section.getId(),section.getLibelle(),section.getPosition(),section.getKanban());
        
        sectionService.save(sectionDto); 
        verify(sectionService, times(1)).save(sectionDto);
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
		
		Section section =new Section(); 
		section.setLibelle("Nouveau Test");
		section.setPosition(1);
		section.setKanban(kanban);

        SectionDto sectionDto=new SectionDto(section.getId(),section.getLibelle(),section.getPosition(),section.getKanban()); 
       
         when(sectionService.getById(sectionDto.getId())).thenReturn(sectionDto).thenReturn(null);
        //test
        sectionService.deleteById(sectionDto.getId()); 
        verify(sectionService, times(1)).deleteById(sectionDto.getId());
    }
}
