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
import fr.istic.kanban.dao.TagDao;
import fr.istic.kanban.dto.TagDto;
import fr.istic.kanban.entity.Tag;
import fr.istic.kanban.service.TagService;
public class TagServiceTest {

	private TagService tagService = mock(TagService.class);
	
	private TagDao tagDao = mock(TagDao.class);
	
	@Before public void initMocks() { 
		MockitoAnnotations.initMocks(this); 
	}
	 
	@Test
   public void getAllTest()
   {

       List<Tag> list = new ArrayList<Tag>();
       List<TagDto> listDto = new ArrayList<TagDto>();
        
       Tag tagOne=new Tag();
       tagOne.setId(2L);
       tagOne.setName("Sciences"); 
		
		Tag tagTwo=new Tag();
		tagTwo.setId(3L);
		tagTwo.setName("Littératures");  
        
       list.add(tagOne);
       list.add(tagTwo); 
        
       TagDto tagOneDto=new TagDto(tagOne.getId(),tagOne.getName());
       TagDto tagTwoDto=new TagDto(tagTwo.getId(),tagTwo.getName());
       listDto.add(tagOneDto);
       listDto.add(tagTwoDto);
       
       when(tagDao.findAll()).thenReturn(list);
       
       when(tagService.findAll()).thenReturn(listDto);
        
       //test
       List<TagDto> tagDtoList = tagService.findAll();
        
       assertEquals(2, tagDtoList.size());
       verify(tagService, times(1)).findAll();
   }
	
	@Test
   public void getByIdTest()
   { 
       Tag tag=new Tag();
       tag.setId(2L);
       tag.setName("Economie"); 
       TagDto tagDto=new TagDto(tag.getId(),tag.getName()); 
      
       when(tagService.getById(2L)).thenReturn(tagDto);
        
       //test
       TagDto tagDtoResult = tagService.getById(2L); 
       assertEquals("Economie", tagDtoResult.getName()); 
   }
	
	 @Test
	public void saveTest()
   { 
       Tag tag=new Tag();
       tag.setId(2L);
       tag.setName("Gestion"); 
       TagDto tagDto=new TagDto(tag.getId(),tag.getName()); 
       
       tagService.save(tagDto); 
       verify(tagService, times(1)).save(tagDto);
   }
	 
	 @Test
	public void deleteTest()
   { 
       Tag tag=new Tag();
       tag.setId(2L);
       tag.setName("Google");
         
       TagDto tagDto=new TagDto(tag.getId(),tag.getName()); 
      
        when(tagService.getById(tagDto.getId())).thenReturn(tagDto).thenReturn(null);
       //test
       tagService.deleteById(tagDto.getId()); 
       verify(tagService, times(1)).deleteById(tagDto.getId());
   }
}
