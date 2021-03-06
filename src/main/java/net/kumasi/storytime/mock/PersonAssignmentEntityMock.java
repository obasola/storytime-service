
/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.mock;

import java.util.LinkedList;
import java.util.List;

import net.kumasi.storytime.model.jpa.PersonAssignmentEntity;
import net.kumasi.storytime.mock.tool.MockValues;

public class PersonAssignmentEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public PersonAssignmentEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger(), mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public PersonAssignmentEntity createInstance( Integer personIdperson, Integer requirementIdrequirement ) {
		PersonAssignmentEntity entity = new PersonAssignmentEntity();
		// Init Primary Key fields
		entity.setPersonIdperson( personIdperson) ;
		entity.setRequirementIdrequirement( requirementIdrequirement) ;
		// Init Data fields
		entity.setStatus( mockValues.nextInteger() ) ; // java.lang.Integer 
		// Init Link fields (if any)
		// setPerson( TODO ) ; // Person 
		// setRequirement( TODO ) ; // Requirement 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<PersonAssignmentEntity> createList(int count) {
		List<PersonAssignmentEntity> list = new LinkedList<PersonAssignmentEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
