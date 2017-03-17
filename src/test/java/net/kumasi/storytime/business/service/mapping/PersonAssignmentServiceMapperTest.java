/*
 * Created on 16 Mar 2017 ( Time 18:41:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import net.kumasi.storytime.model.PersonAssignment;
import net.kumasi.storytime.model.jpa.PersonAssignmentEntity;
import net.kumasi.storytime.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class PersonAssignmentServiceMapperTest {

	private PersonAssignmentServiceMapper personAssignmentServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		personAssignmentServiceMapper = new PersonAssignmentServiceMapper();
		personAssignmentServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'PersonAssignmentEntity' to 'PersonAssignment'
	 * @param personAssignmentEntity
	 */
	@Test
	public void testMapPersonAssignmentEntityToPersonAssignment() {
		// Given
		PersonAssignmentEntity personAssignmentEntity = new PersonAssignmentEntity();
		personAssignmentEntity.setStatus(mockValues.nextInteger());
		
		// When
		PersonAssignment personAssignment = personAssignmentServiceMapper.mapPersonAssignmentEntityToPersonAssignment(personAssignmentEntity);
		
		// Then
		assertEquals(personAssignmentEntity.getStatus(), personAssignment.getStatus());
	}
	
	/**
	 * Test : Mapping from 'PersonAssignment' to 'PersonAssignmentEntity'
	 */
	@Test
	public void testMapPersonAssignmentToPersonAssignmentEntity() {
		// Given
		PersonAssignment personAssignment = new PersonAssignment();
		personAssignment.setStatus(mockValues.nextInteger());

		PersonAssignmentEntity personAssignmentEntity = new PersonAssignmentEntity();
		
		// When
		personAssignmentServiceMapper.mapPersonAssignmentToPersonAssignmentEntity(personAssignment, personAssignmentEntity);
		
		// Then
		assertEquals(personAssignment.getStatus(), personAssignmentEntity.getStatus());
	}

}