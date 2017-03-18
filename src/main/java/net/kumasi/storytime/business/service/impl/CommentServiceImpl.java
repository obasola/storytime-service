/*
 * Created on 16 Mar 2017 ( Time 18:41:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.kumasi.storytime.model.Comment;
import net.kumasi.storytime.model.jpa.CommentEntity;
import net.kumasi.storytime.business.service.CommentService;
import net.kumasi.storytime.business.service.mapping.CommentServiceMapper;
import net.kumasi.storytime.persistence.PersistenceServiceProvider;
import net.kumasi.storytime.persistence.services.CommentPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of CommentService
 */
@Component
public class CommentServiceImpl implements CommentService {

	private CommentPersistence commentPersistence;

	@Resource
	private CommentServiceMapper commentServiceMapper;
	
	public CommentServiceImpl() {
		commentPersistence = PersistenceServiceProvider.getService(CommentPersistence.class);
	}
		
	@Override
	public Comment findById(Integer idcomment) {
		CommentEntity entity = commentPersistence.load(idcomment);
		return commentServiceMapper.mapCommentEntityToComment(entity);
	}

	@Override
	public List<Comment> findAll() {
		List<CommentEntity> entities = commentPersistence.loadAll();
		List<Comment> beans = new ArrayList<Comment>();
		for(CommentEntity entity : entities) {
			beans.add(commentServiceMapper.mapCommentEntityToComment(entity));
		}
		return beans;
	}

	@Override
	public Comment save(Comment comment) {
		return update(comment) ;
	}

	@Override
	public Comment create(Comment comment) {
		if(comment.getIdcomment() != null && commentPersistence.load(comment.getIdcomment()) != null) {
			throw new IllegalStateException("already.exists");
		}
		CommentEntity commentEntity = new CommentEntity();
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentPersistence.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
	}

	@Override
	public Comment update(Comment comment) {
		CommentEntity commentEntity = commentPersistence.load(comment.getIdcomment());
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentPersistence.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
	}

	@Override
	public void delete(Integer idcomment) {
		commentPersistence.delete(idcomment);
	}

	public CommentPersistence getCommentPersistence() {
		return commentPersistence;
	}

	public void setCommentPersistence(CommentPersistence commentPersistence) {
		this.commentPersistence = commentPersistence;
	}

	public CommentServiceMapper getCommentServiceMapper() {
		return commentServiceMapper;
	}

	public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
		this.commentServiceMapper = commentServiceMapper;
	}

}
