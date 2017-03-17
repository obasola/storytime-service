package net.kumasi.storytime.test;

import net.kumasi.storytime.model.SpecificationType;

public class SpecificationTypeFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public SpecificationType newSpecificationType() {

		Integer idrequirementType = mockValues.nextInteger();

		SpecificationType specificationType = new SpecificationType();
		specificationType.setIdrequirementType(idrequirementType);
		return specificationType;
	}
	
}
