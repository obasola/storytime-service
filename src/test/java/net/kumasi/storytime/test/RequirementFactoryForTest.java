package net.kumasi.storytime.test;

import net.kumasi.storytime.model.Requirement;

public class RequirementFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Requirement newRequirement() {

		Integer idrequirement = mockValues.nextInteger();

		Requirement requirement = new Requirement();
		requirement.setIdrequirement(idrequirement);
		return requirement;
	}
	
}
