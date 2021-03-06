/*
 * Created on 16 Mar 2017 ( Time 18:41:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import net.kumasi.storytime.model.SpecificationType;
import net.kumasi.storytime.model.jpa.SpecificationTypeEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class SpecificationTypeServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public SpecificationTypeServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'SpecificationTypeEntity' to 'SpecificationType'
	 * @param specificationTypeEntity
	 */
	public SpecificationType mapSpecificationTypeEntityToSpecificationType(SpecificationTypeEntity specificationTypeEntity) {
		if(specificationTypeEntity == null) {
			return null;
		}

		//--- Generic mapping 
		SpecificationType specificationType = map(specificationTypeEntity, SpecificationType.class);

		return specificationType;
	}
	
	/**
	 * Mapping from 'SpecificationType' to 'SpecificationTypeEntity'
	 * @param specificationType
	 * @param specificationTypeEntity
	 */
	public void mapSpecificationTypeToSpecificationTypeEntity(SpecificationType specificationType, SpecificationTypeEntity specificationTypeEntity) {
		if(specificationType == null) {
			return;
		}

		//--- Generic mapping 
		map(specificationType, specificationTypeEntity);

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