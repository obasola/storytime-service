package net.kumasi.storytime.test;

import net.kumasi.storytime.model.PersonAssignment;

public class PersonAssignmentFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public PersonAssignment newPersonAssignment() {

		Integer personIdperson = mockValues.nextInteger();
		Integer requirementIdrequirement = mockValues.nextInteger();

		PersonAssignment personAssignment = new PersonAssignment();
		personAssignment.setPersonIdperson(personIdperson);
		personAssignment.setRequirementIdrequirement(requirementIdrequirement);
		return personAssignment;
	}
	
}
