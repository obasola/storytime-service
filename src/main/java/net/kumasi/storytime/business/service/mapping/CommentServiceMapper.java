/*
 * Created on 16 Mar 2017 ( Time 18:41:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import net.kumasi.storytime.model.Comment;
import net.kumasi.storytime.model.jpa.CommentEntity;
import net.kumasi.storytime.model.jpa.RequirementEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CommentServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CommentServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CommentEntity' to 'Comment'
	 * @param commentEntity
	 */
	public Comment mapCommentEntityToComment(CommentEntity commentEntity) {
		if(commentEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Comment comment = map(commentEntity, Comment.class);

		//--- Link mapping ( link to Requirement )
		if(commentEntity.getRequirement() != null) {
			comment.setRequirementIdrequirement(commentEntity.getRequirement().getIdrequirement());
		}
		return comment;
	}
	
	/**
	 * Mapping from 'Comment' to 'CommentEntity'
	 * @param comment
	 * @param commentEntity
	 */
	public void mapCommentToCommentEntity(Comment comment, CommentEntity commentEntity) {
		if(comment == null) {
			return;
		}

		//--- Generic mapping 
		map(comment, commentEntity);

		//--- Link mapping ( link : comment )
		if( hasLinkToRequirement(comment) ) {
			RequirementEntity requirement1 = new RequirementEntity();
			requirement1.setIdrequirement( comment.getRequirementIdrequirement() );
			commentEntity.setRequirement( requirement1 );
		} else {
			commentEntity.setRequirement( null );
		}

	}
	
	/**
	 * Verify that Requirement id is valid.
	 * @param Requirement Requirement
	 * @return boolean
	 */
	private boolean hasLinkToRequirement(Comment comment) {
		if(comment.getRequirementIdrequirement() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}