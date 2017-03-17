package net.kumasi.storytime.test;

import net.kumasi.storytime.model.jpa.RequirementSpecificationTypeEntity;

public class RequirementSpecificationTypeEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public RequirementSpecificationTypeEntity newRequirementSpecificationTypeEntity() {

		Integer requirementIdrequirement = mockValues.nextInteger();
		Integer specificationTypeIdrequirementType = mockValues.nextInteger();

		RequirementSpecificationTypeEntity requirementSpecificationTypeEntity = new RequirementSpecificationTypeEntity();
		requirementSpecificationTypeEntity.setRequirementIdrequirement(requirementIdrequirement);
		requirementSpecificationTypeEntity.setSpecificationTypeIdrequirementType(specificationTypeIdrequirementType);
		return requirementSpecificationTypeEntity;
	}
	
}
