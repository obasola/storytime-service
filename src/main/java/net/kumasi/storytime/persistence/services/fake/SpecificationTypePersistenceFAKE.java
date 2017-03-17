/*
 * Created on 16 Mar 2017 ( Time 18:41:35 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.persistence.services.fake;

import java.util.List;
import java.util.Map;

import net.kumasi.storytime.model.jpa.SpecificationTypeEntity;
import net.kumasi.storytime.persistence.commons.fake.GenericFakeService;
import net.kumasi.storytime.persistence.services.SpecificationTypePersistence;

/**
 * Fake persistence service implementation ( entity "SpecificationType" )
 *
 * @author Telosys Tools Generator
 */
public class SpecificationTypePersistenceFAKE extends GenericFakeService<SpecificationTypeEntity> implements SpecificationTypePersistence {

	public SpecificationTypePersistenceFAKE () {
		super(SpecificationTypeEntity.class);
	}
	
	protected SpecificationTypeEntity buildEntity(int index) {
		SpecificationTypeEntity entity = new SpecificationTypeEntity();
		// Init fields with mock values
		entity.setIdrequirementType( nextInteger() ) ;
		entity.setName( nextString() ) ;
		entity.setDescription( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(SpecificationTypeEntity entity) {
		log("delete ( SpecificationTypeEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer idrequirementType ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(SpecificationTypeEntity entity) {
		log("insert ( SpecificationTypeEntity : " + entity + ")" ) ;
	}

	public SpecificationTypeEntity load( Integer idrequirementType ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<SpecificationTypeEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<SpecificationTypeEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<SpecificationTypeEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public SpecificationTypeEntity save(SpecificationTypeEntity entity) {
		log("insert ( SpecificationTypeEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<SpecificationTypeEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
