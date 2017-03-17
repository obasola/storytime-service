/*
 * Created on 16 Mar 2017 ( Time 18:41:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service;

import java.util.List;

import net.kumasi.storytime.model.Comment;

/**
 * Business Service Interface for entity Comment.
 */
public interface CommentService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param idcomment
	 * @return entity
	 */
	Comment findById( Integer idcomment  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Comment> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Comment save(Comment entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Comment update(Comment entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Comment create(Comment entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param idcomment
	 */
	void delete( Integer idcomment );


}
