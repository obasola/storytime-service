/*
 * Created on 16 Mar 2017 ( Time 18:41:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import net.kumasi.storytime.model.RequirementSpecificationType;
import net.kumasi.storytime.model.jpa.RequirementSpecificationTypeEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class RequirementSpecificationTypeServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public RequirementSpecificationTypeServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'RequirementSpecificationTypeEntity' to 'RequirementSpecificationType'
	 * @param requirementSpecificationTypeEntity
	 */
	public RequirementSpecificationType mapRequirementSpecificationTypeEntityToRequirementSpecificationType(RequirementSpecificationTypeEntity requirementSpecificationTypeEntity) {
		if(requirementSpecificationTypeEntity == null) {
			return null;
		}

		//--- Generic mapping 
		RequirementSpecificationType requirementSpecificationType = map(requirementSpecificationTypeEntity, RequirementSpecificationType.class);

		return requirementSpecificationType;
	}
	
	/**
	 * Mapping from 'RequirementSpecificationType' to 'RequirementSpecificationTypeEntity'
	 * @param requirementSpecificationType
	 * @param requirementSpecificationTypeEntity
	 */
	public void mapRequirementSpecificationTypeToRequirementSpecificationTypeEntity(RequirementSpecificationType requirementSpecificationType, RequirementSpecificationTypeEntity requirementSpecificationTypeEntity) {
		if(requirementSpecificationType == null) {
			return;
		}

		//--- Generic mapping 
		map(requirementSpecificationType, requirementSpecificationTypeEntity);

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