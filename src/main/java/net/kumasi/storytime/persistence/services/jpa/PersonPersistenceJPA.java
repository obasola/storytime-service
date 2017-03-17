/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package net.kumasi.storytime.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import net.kumasi.storytime.model.jpa.PersonEntity;
import net.kumasi.storytime.persistence.commons.jpa.GenericJpaService;
import net.kumasi.storytime.persistence.commons.jpa.JpaOperation;
import net.kumasi.storytime.persistence.services.PersonPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Person" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class PersonPersistenceJPA extends GenericJpaService<PersonEntity, Integer> implements PersonPersistence {

	/**
	 * Constructor
	 */
	public PersonPersistenceJPA() {
		super(PersonEntity.class);
	}

	@Override
	public PersonEntity load( Integer idperson ) {
		return super.load( idperson );
	}

	@Override
	public boolean delete( Integer idperson ) {
		return super.delete( idperson );
	}

	@Override
	public boolean delete(PersonEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getIdperson() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("PersonEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
