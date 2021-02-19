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

public class TagService {
	private final TagDao tagDao = new TagDao(); 
	
	/*
	 * Enregistrer une entité
	 */
	public void save(TagDto tagDto) { 
        Tag tag=new Tag(tagDto.getName());
        tagDao.save(tag);
    }	
	
	/*
	 * Recupérer la liste de l'entité
	 */
	public List<TagDto> findAll() { 
        List<Tag> tags=tagDao.findAll(); 
		List<TagDto> tagsDto = new ArrayList<>();  
		tags.forEach(tag-> tagsDto.add(new TagDto(tag.getId(),tag.getName())) ); 
		return tagsDto; 
    }	 

	/*
	 * Recupérer une valeur à partir d'un id
	 * NotFundException
	 */
	public TagDto getById(Long id) {
		CustomException.isValid(id); 
		TagDto tagDto; 
		try {
			Tag tag=tagDao.findOne(id);
			if(tag==null) { 
				throw new NotFoundException("Aucun resultat pour l'élement avec l'identifiant "+id);
			}
			tagDto=new TagDto(tag.getId(),tag.getName());
        }catch (Exception e){
            System.err.println("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'élement avec l'identifiant "+id);
        } 
		return tagDto; 
	}

	/*
	 * Recupérer une valeur à partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public TagDto update(Long id, TagDto tagDto) {
		CustomException.isValid(id);  
		try {
			Tag tag=tagDao.findOne(id);
			if(tag==null) { 
				throw new NotFoundException("Aucun resultat pour l'élement avec l'identifiant "+id);
			}
			tag.setName(tagDto.getName()); 
			tagDao.update(tag);
			tagDto.setId(id);
        }catch (Exception e){
            System.err.println("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        } 
		return tagDto; 
	}
	/*
	 * Supprimer une entité
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(Long id) {
		CustomException.isValid(id);  
		tagDao.deleteById(id);
	}
	
}
