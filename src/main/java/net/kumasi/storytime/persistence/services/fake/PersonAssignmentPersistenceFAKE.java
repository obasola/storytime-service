/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.persistence.services.fake;

import java.util.List;
import java.util.Map;

import net.kumasi.storytime.model.jpa.PersonAssignmentEntity;
import net.kumasi.storytime.persistence.commons.fake.GenericFakeService;
import net.kumasi.storytime.persistence.services.PersonAssignmentPersistence;

/**
 * Fake persistence service implementation ( entity "PersonAssignment" )
 *
 * @author Telosys Tools Generator
 */
public class PersonAssignmentPersistenceFAKE extends GenericFakeService<PersonAssignmentEntity> implements PersonAssignmentPersistence {

	public PersonAssignmentPersistenceFAKE () {
		super(PersonAssignmentEntity.class);
	}
	
	protected PersonAssignmentEntity buildEntity(int index) {
		PersonAssignmentEntity entity = new PersonAssignmentEntity();
		// Init fields with mock values
		entity.setStatus( nextInteger() ) ;
		return entity ;
	}
	
	
	public boolean delete(PersonAssignmentEntity entity) {
		log("delete ( PersonAssignmentEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer personIdperson, Integer requirementIdrequirement ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(PersonAssignmentEntity entity) {
		log("insert ( PersonAssignmentEntity : " + entity + ")" ) ;
	}

	public PersonAssignmentEntity load( Integer personIdperson, Integer requirementIdrequirement ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<PersonAssignmentEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<PersonAssignmentEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<PersonAssignmentEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public PersonAssignmentEntity save(PersonAssignmentEntity entity) {
		log("insert ( PersonAssignmentEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<PersonAssignmentEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
