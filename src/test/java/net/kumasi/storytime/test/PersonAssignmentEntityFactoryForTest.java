package net.kumasi.storytime.test;

import net.kumasi.storytime.model.jpa.PersonAssignmentEntity;

public class PersonAssignmentEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public PersonAssignmentEntity newPersonAssignmentEntity() {

		Integer personIdperson = mockValues.nextInteger();
		Integer requirementIdrequirement = mockValues.nextInteger();

		PersonAssignmentEntity personAssignmentEntity = new PersonAssignmentEntity();
		personAssignmentEntity.setPersonIdperson(personIdperson);
		personAssignmentEntity.setRequirementIdrequirement(requirementIdrequirement);
		return personAssignmentEntity;
	}
	
}
