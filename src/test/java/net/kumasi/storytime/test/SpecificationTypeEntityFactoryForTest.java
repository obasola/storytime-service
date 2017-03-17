package net.kumasi.storytime.test;

import net.kumasi.storytime.model.jpa.SpecificationTypeEntity;

public class SpecificationTypeEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public SpecificationTypeEntity newSpecificationTypeEntity() {

		Integer idrequirementType = mockValues.nextInteger();

		SpecificationTypeEntity specificationTypeEntity = new SpecificationTypeEntity();
		specificationTypeEntity.setIdrequirementType(idrequirementType);
		return specificationTypeEntity;
	}
	
}
