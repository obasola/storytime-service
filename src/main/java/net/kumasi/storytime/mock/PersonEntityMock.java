
/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.mock;

import java.util.LinkedList;
import java.util.List;

import net.kumasi.storytime.model.jpa.PersonEntity;
import net.kumasi.storytime.mock.tool.MockValues;

public class PersonEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public PersonEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public PersonEntity createInstance( Integer idperson ) {
		PersonEntity entity = new PersonEntity();
		// Init Primary Key fields
		entity.setIdperson( idperson) ;
		// Init Data fields
		entity.setFirstName( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setLastName( mockValues.nextString(45) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setListOfPersonAssignment( TODO ) ; // List<PersonAssignment> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<PersonEntity> createList(int count) {
		List<PersonEntity> list = new LinkedList<PersonEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
