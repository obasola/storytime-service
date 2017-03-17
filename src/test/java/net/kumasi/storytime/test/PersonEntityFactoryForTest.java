package net.kumasi.storytime.test;

import net.kumasi.storytime.model.jpa.PersonEntity;

public class PersonEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public PersonEntity newPersonEntity() {

		Integer idperson = mockValues.nextInteger();

		PersonEntity personEntity = new PersonEntity();
		personEntity.setIdperson(idperson);
		return personEntity;
	}
	
}
