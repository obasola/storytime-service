package net.kumasi.storytime.test;

import net.kumasi.storytime.model.Person;

public class PersonFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Person newPerson() {

		Integer idperson = mockValues.nextInteger();

		Person person = new Person();
		person.setIdperson(idperson);
		return person;
	}
	
}
