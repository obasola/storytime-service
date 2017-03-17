/*
 * Created on 16 Mar 2017 ( Time 18:41:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.kumasi.storytime.model.StatusCode;
import net.kumasi.storytime.model.jpa.StatusCodeEntity;
import java.util.List;
import net.kumasi.storytime.business.service.StatusCodeService;
import net.kumasi.storytime.business.service.mapping.StatusCodeServiceMapper;
import net.kumasi.storytime.persistence.PersistenceServiceProvider;
import net.kumasi.storytime.persistence.services.StatusCodePersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of StatusCodeService
 */
@Component
public class StatusCodeServiceImpl implements StatusCodeService {

	private StatusCodePersistence statusCodePersistence;

	@Resource
	private StatusCodeServiceMapper statusCodeServiceMapper;
	
	public StatusCodeServiceImpl() {
		statusCodePersistence = PersistenceServiceProvider.getService(StatusCodePersistence.class);
	}
		
	@Override
	public StatusCode findById(Integer id) {
		StatusCodeEntity entity = statusCodePersistence.load(id);
		return statusCodeServiceMapper.mapStatusCodeEntityToStatusCode(entity);
	}

	@Override
	public List<StatusCode> findAll() {
		List<StatusCodeEntity> entities = statusCodePersistence.loadAll();
		List<StatusCode> beans = new ArrayList<StatusCode>();
		for(StatusCodeEntity entity : entities) {
			beans.add(statusCodeServiceMapper.mapStatusCodeEntityToStatusCode(entity));
		}
		return beans;
	}

	@Override
	public StatusCode save(StatusCode statusCode) {
		return update(statusCode) ;
	}

	@Override
	public StatusCode create(StatusCode statusCode) {
		if(statusCodePersistence.load(statusCode.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		StatusCodeEntity statusCodeEntity = new StatusCodeEntity();
		statusCodeServiceMapper.mapStatusCodeToStatusCodeEntity(statusCode, statusCodeEntity);
		StatusCodeEntity statusCodeEntitySaved = statusCodePersistence.save(statusCodeEntity);
		return statusCodeServiceMapper.mapStatusCodeEntityToStatusCode(statusCodeEntitySaved);
	}

	@Override
	public StatusCode update(StatusCode statusCode) {
		StatusCodeEntity statusCodeEntity = statusCodePersistence.load(statusCode.getId());
		statusCodeServiceMapper.mapStatusCodeToStatusCodeEntity(statusCode, statusCodeEntity);
		StatusCodeEntity statusCodeEntitySaved = statusCodePersistence.save(statusCodeEntity);
		return statusCodeServiceMapper.mapStatusCodeEntityToStatusCode(statusCodeEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		statusCodePersistence.delete(id);
	}

	public StatusCodePersistence getStatusCodePersistence() {
		return statusCodePersistence;
	}

	public void setStatusCodePersistence(StatusCodePersistence statusCodePersistence) {
		this.statusCodePersistence = statusCodePersistence;
	}

	public StatusCodeServiceMapper getStatusCodeServiceMapper() {
		return statusCodeServiceMapper;
	}

	public void setStatusCodeServiceMapper(StatusCodeServiceMapper statusCodeServiceMapper) {
		this.statusCodeServiceMapper = statusCodeServiceMapper;
	}

}