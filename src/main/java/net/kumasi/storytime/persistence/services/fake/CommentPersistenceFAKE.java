/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.persistence.services.fake;

import java.util.List;
import java.util.Map;

import net.kumasi.storytime.model.jpa.CommentEntity;
import net.kumasi.storytime.persistence.commons.fake.GenericFakeService;
import net.kumasi.storytime.persistence.services.CommentPersistence;

/**
 * Fake persistence service implementation ( entity "Comment" )
 *
 * @author Telosys Tools Generator
 */
public class CommentPersistenceFAKE extends GenericFakeService<CommentEntity> implements CommentPersistence {

	public CommentPersistenceFAKE () {
		super(CommentEntity.class);
	}
	
	protected CommentEntity buildEntity(int index) {
		CommentEntity entity = new CommentEntity();
		// Init fields with mock values
		entity.setIdcomment( nextInteger() ) ;
		entity.setDescription( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(CommentEntity entity) {
		log("delete ( CommentEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer idcomment ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(CommentEntity entity) {
		log("insert ( CommentEntity : " + entity + ")" ) ;
	}

	public CommentEntity load( Integer idcomment ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<CommentEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<CommentEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<CommentEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public CommentEntity save(CommentEntity entity) {
		log("insert ( CommentEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<CommentEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
