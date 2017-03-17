package net.kumasi.storytime.test;

import net.kumasi.storytime.model.jpa.RequirementEntity;

public class RequirementEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public RequirementEntity newRequirementEntity() {

		Integer idrequirement = mockValues.nextInteger();

		RequirementEntity requirementEntity = new RequirementEntity();
		requirementEntity.setIdrequirement(idrequirement);
		return requirementEntity;
	}
	
}
