/*
 * Created on 16 Mar 2017 ( Time 18:41:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service;

import java.util.List;

import net.kumasi.storytime.model.PersonAssignment;

/**
 * Business Service Interface for entity PersonAssignment.
 */
public interface PersonAssignmentService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param personIdperson
	 * @param requirementIdrequirement
	 * @return entity
	 */
	PersonAssignment findById( Integer personIdperson, Integer requirementIdrequirement  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<PersonAssignment> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	PersonAssignment save(PersonAssignment entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	PersonAssignment update(PersonAssignment entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	PersonAssignment create(PersonAssignment entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param personIdperson
	 * @param requirementIdrequirement
	 */
	void delete( Integer personIdperson, Integer requirementIdrequirement );


}
