/*
 * Created on 16 Mar 2017 ( Time 18:41:35 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.persistence.services.fake;

import java.util.List;
import java.util.Map;

import net.kumasi.storytime.model.jpa.RequirementEntity;
import net.kumasi.storytime.persistence.commons.fake.GenericFakeService;
import net.kumasi.storytime.persistence.services.RequirementPersistence;

/**
 * Fake persistence service implementation ( entity "Requirement" )
 *
 * @author Telosys Tools Generator
 */
public class RequirementPersistenceFAKE extends GenericFakeService<RequirementEntity> implements RequirementPersistence {

	public RequirementPersistenceFAKE () {
		super(RequirementEntity.class);
	}
	
	protected RequirementEntity buildEntity(int index) {
		RequirementEntity entity = new RequirementEntity();
		// Init fields with mock values
		entity.setIdrequirement( nextInteger() ) ;
		entity.setTitle( nextString() ) ;
		entity.setDescription( nextString() ) ;
		entity.setAcceptanceCriteria( nextString() ) ;
		entity.setCreatedOn( nextDate() ) ;
		entity.setCreatedBy( nextInteger() ) ;
		entity.setStartDate( nextDate() ) ;
		entity.setEndDate( nextDate() ) ;
		entity.setStatus( nextInteger() ) ;
		entity.setPriority( nextInteger() ) ;
		return entity ;
	}
	
	
	public boolean delete(RequirementEntity entity) {
		log("delete ( RequirementEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer idrequirement ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(RequirementEntity entity) {
		log("insert ( RequirementEntity : " + entity + ")" ) ;
	}

	public RequirementEntity load( Integer idrequirement ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<RequirementEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<RequirementEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<RequirementEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public RequirementEntity save(RequirementEntity entity) {
		log("insert ( RequirementEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<RequirementEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
