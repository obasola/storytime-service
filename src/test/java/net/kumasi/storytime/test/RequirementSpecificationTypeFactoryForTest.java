package net.kumasi.storytime.test;

import net.kumasi.storytime.model.RequirementSpecificationType;

public class RequirementSpecificationTypeFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public RequirementSpecificationType newRequirementSpecificationType() {

		Integer requirementIdrequirement = mockValues.nextInteger();
		Integer specificationTypeIdrequirementType = mockValues.nextInteger();

		RequirementSpecificationType requirementSpecificationType = new RequirementSpecificationType();
		requirementSpecificationType.setRequirementIdrequirement(requirementIdrequirement);
		requirementSpecificationType.setSpecificationTypeIdrequirementType(specificationTypeIdrequirementType);
		return requirementSpecificationType;
	}
	
}
