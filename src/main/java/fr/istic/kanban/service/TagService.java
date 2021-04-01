package fr.istic.kanban.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException; 
import fr.istic.kanban.dao.TagDao;
import fr.istic.kanban.dto.TagDto;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.Tag; 
import fr.istic.kanban.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagService {
	private final TagDao tagDao = new TagDao();
	private static Logger LOGGER = LoggerFactory.getLogger(FicheService.class);
	
	/*
	 * Enregistrer une entit�
	 */
	public void save(TagDto tagDto) { 
        Tag tag=new Tag(tagDto.getName());
        tagDao.save(tag);
    }	
	
	/*
	 * Recup�rer la liste de l'entit�
	 */
	public List<TagDto> findAll() { 
        List<Tag> tags=tagDao.findAll(); 
		List<TagDto> tagsDto = new ArrayList<>();  
		tags.forEach(tag-> tagsDto.add(new TagDto(tag.getId(),tag.getName())) ); 
		return tagsDto; 
    }	 

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException
	 */
	public TagDto getById(Long id) {
		CustomException.isValid(id); 
		TagDto tagDto; 
		try {
			Tag tag=tagDao.findOne(id);
			if(tag==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			tagDto=new TagDto(tag.getId(),tag.getName());
        }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
        } 
		return tagDto; 
	}

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public TagDto update(Long id, TagDto tagDto) {
		CustomException.isValid(id);  
		try {
			Tag tag=tagDao.findOne(id);
			if(tag==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			tag.setName(tagDto.getName()); 
			tagDao.update(tag);
			tagDto.setId(id);
        }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        } 
		return tagDto; 
	}
	/*
	 * Supprimer une entit�
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(Long id) {
		CustomException.isValid(id);  
		tagDao.deleteById(id);
	}
	
}
