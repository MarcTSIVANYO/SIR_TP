package fr.istic.kanban.service;  
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before; 
import org.mockito.MockitoAnnotations;

import fr.istic.kanban.dao.UserDao;
import fr.istic.kanban.dto.UserDto;
import fr.istic.kanban.entity.User; 
public class UserServiceTest {

	private UserService userService = mock(UserService.class);
	
	private UserDao userDao = mock(UserDao.class);
	
	@Before public void initMocks() { 
		MockitoAnnotations.initMocks(this); 
	}
	 
	@Test
   public void getAllTest()
   {

       List<User> list = new ArrayList<User>();
       List<UserDto> listDto = new ArrayList<UserDto>();
        
       User userOne=new User();
		userOne.setEmail("marctsivanyo@gmail.com");
		userOne.setName("Marc TSIVANYO");
		
		User userTwo=new User();
		userTwo.setEmail("laurentduppont@gmail.com");
		userTwo.setName("Laurant Duppont");  
        
       list.add(userOne);
       list.add(userTwo); 
        
       UserDto userOneDto=new UserDto(userOne.getEmail(),userOne.getName());
       UserDto userTwoDto=new UserDto(userTwo.getEmail(),userTwo.getName());
       listDto.add(userOneDto);
       listDto.add(userTwoDto);
       
       when(userDao.findAll()).thenReturn(list);
       
       when(userService.findAll()).thenReturn(listDto);
        
       //test
       List<UserDto> userDtoList = userService.findAll();
        
       assertEquals(2, userDtoList.size());
       verify(userService, times(1)).findAll();
   }
	
	@Test
   public void getByIdTest()
   { 
       User user=new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
       UserDto userDto=new UserDto(user.getEmail(),user.getName()); 
      
       when(userService.getById(user.getEmail())).thenReturn(userDto);
        
       //test
       UserDto userDtoResult = userService.getById(user.getEmail()); 
       assertEquals(user.getName(), userDtoResult.getName()); 
   }
	
	 @Test
	public void saveTest()
   { 
       User user=new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
       UserDto userDto=new UserDto(user.getEmail(),user.getName()); 
       
       userService.save(userDto); 
       verify(userService, times(1)).save(userDto);
   }
	 
	 @Test
	public void deleteTest()
   { 
       User user=new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");
         
       UserDto userDto=new UserDto(user.getEmail(),user.getName()); 
      
        when(userService.getById(userDto.getEmail())).thenReturn(userDto).thenReturn(null);
       //test
       userService.deleteById(userDto.getEmail()); 
       verify(userService, times(1)).deleteById(userDto.getEmail());
   }
}
