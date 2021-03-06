/*
 * Created on 16 Mar 2017 ( Time 18:41:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import net.kumasi.storytime.model.PersonAssignment;
import net.kumasi.storytime.model.jpa.PersonAssignmentEntity;
import net.kumasi.storytime.model.jpa.PersonAssignmentEntityKey;
import net.kumasi.storytime.business.service.mapping.PersonAssignmentServiceMapper;
import net.kumasi.storytime.persistence.services.jpa.PersonAssignmentPersistenceJPA;
import net.kumasi.storytime.test.PersonAssignmentFactoryForTest;
import net.kumasi.storytime.test.PersonAssignmentEntityFactoryForTest;
import net.kumasi.storytime.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of PersonAssignmentService
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonAssignmentServiceImplTest {

	@InjectMocks
	private PersonAssignmentServiceImpl personAssignmentService;
	@Mock
	private PersonAssignmentPersistenceJPA personAssignmentPersistenceJPA;
	@Mock
	private PersonAssignmentServiceMapper personAssignmentServiceMapper;
	
	private PersonAssignmentFactoryForTest personAssignmentFactoryForTest = new PersonAssignmentFactoryForTest();

	private PersonAssignmentEntityFactoryForTest personAssignmentEntityFactoryForTest = new PersonAssignmentEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Integer personIdperson = mockValues.nextInteger();
		Integer requirementIdrequirement = mockValues.nextInteger();
		
		PersonAssignmentEntityKey personAssignmentEntityKey = new PersonAssignmentEntityKey(personIdperson, requirementIdrequirement);
		personAssignmentEntityKey.setPersonIdperson(personIdperson);
		personAssignmentEntityKey.setRequirementIdrequirement(requirementIdrequirement);
		
		PersonAssignmentEntity personAssignmentEntity = personAssignmentPersistenceJPA.load(personAssignmentEntityKey);
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		when(personAssignmentServiceMapper.mapPersonAssignmentEntityToPersonAssignment(personAssignmentEntity)).thenReturn(personAssignment);

		// When
		PersonAssignment personAssignmentFound = personAssignmentService.findById(personIdperson, requirementIdrequirement);

		// Then
		assertEquals(personAssignment.getPersonIdperson(),personAssignmentFound.getPersonIdperson());
		assertEquals(personAssignment.getRequirementIdrequirement(),personAssignmentFound.getRequirementIdrequirement());
	}

	@Test
	public void findAll() {
		// Given
		List<PersonAssignmentEntity> personAssignmentEntitys = new ArrayList<PersonAssignmentEntity>();
		PersonAssignmentEntity personAssignmentEntity1 = personAssignmentEntityFactoryForTest.newPersonAssignmentEntity();
		personAssignmentEntitys.add(personAssignmentEntity1);
		PersonAssignmentEntity personAssignmentEntity2 = personAssignmentEntityFactoryForTest.newPersonAssignmentEntity();
		personAssignmentEntitys.add(personAssignmentEntity2);
		when(personAssignmentPersistenceJPA.loadAll()).thenReturn(personAssignmentEntitys);
		
		PersonAssignment personAssignment1 = personAssignmentFactoryForTest.newPersonAssignment();
		when(personAssignmentServiceMapper.mapPersonAssignmentEntityToPersonAssignment(personAssignmentEntity1)).thenReturn(personAssignment1);
		PersonAssignment personAssignment2 = personAssignmentFactoryForTest.newPersonAssignment();
		when(personAssignmentServiceMapper.mapPersonAssignmentEntityToPersonAssignment(personAssignmentEntity2)).thenReturn(personAssignment2);

		// When
		List<PersonAssignment> personAssignmentsFounds = personAssignmentService.findAll();

		// Then
		assertTrue(personAssignment1 == personAssignmentsFounds.get(0));
		assertTrue(personAssignment2 == personAssignmentsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();

		PersonAssignmentEntity personAssignmentEntity = personAssignmentEntityFactoryForTest.newPersonAssignmentEntity();
		when(personAssignmentPersistenceJPA.load(personAssignment.getPersonIdperson(), personAssignment.getRequirementIdrequirement())).thenReturn(null);
		
		personAssignmentEntity = new PersonAssignmentEntity();
		personAssignmentServiceMapper.mapPersonAssignmentToPersonAssignmentEntity(personAssignment, personAssignmentEntity);
		PersonAssignmentEntity personAssignmentEntitySaved = personAssignmentPersistenceJPA.save(personAssignmentEntity);
		
		PersonAssignment personAssignmentSaved = personAssignmentFactoryForTest.newPersonAssignment();
		when(personAssignmentServiceMapper.mapPersonAssignmentEntityToPersonAssignment(personAssignmentEntitySaved)).thenReturn(personAssignmentSaved);

		// When
		PersonAssignment personAssignmentResult = personAssignmentService.create(personAssignment);

		// Then
		assertTrue(personAssignmentResult == personAssignmentSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();

		PersonAssignmentEntity personAssignmentEntity = personAssignmentEntityFactoryForTest.newPersonAssignmentEntity();
		when(personAssignmentPersistenceJPA.load(personAssignment.getPersonIdperson(), personAssignment.getRequirementIdrequirement())).thenReturn(personAssignmentEntity);

		// When
		Exception exception = null;
		try {
			personAssignmentService.create(personAssignment);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();

		PersonAssignmentEntity personAssignmentEntity = personAssignmentEntityFactoryForTest.newPersonAssignmentEntity();
		when(personAssignmentPersistenceJPA.load(personAssignment.getPersonIdperson(), personAssignment.getRequirementIdrequirement())).thenReturn(personAssignmentEntity);
		
		PersonAssignmentEntity personAssignmentEntitySaved = personAssignmentEntityFactoryForTest.newPersonAssignmentEntity();
		when(personAssignmentPersistenceJPA.save(personAssignmentEntity)).thenReturn(personAssignmentEntitySaved);
		
		PersonAssignment personAssignmentSaved = personAssignmentFactoryForTest.newPersonAssignment();
		when(personAssignmentServiceMapper.mapPersonAssignmentEntityToPersonAssignment(personAssignmentEntitySaved)).thenReturn(personAssignmentSaved);

		// When
		PersonAssignment personAssignmentResult = personAssignmentService.update(personAssignment);

		// Then
		verify(personAssignmentServiceMapper).mapPersonAssignmentToPersonAssignmentEntity(personAssignment, personAssignmentEntity);
		assertTrue(personAssignmentResult == personAssignmentSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer personIdperson = mockValues.nextInteger();
		Integer requirementIdrequirement = mockValues.nextInteger();

		// When
		personAssignmentService.delete(personIdperson, requirementIdrequirement);

		// Then
		verify(personAssignmentPersistenceJPA).delete(personIdperson, requirementIdrequirement);
		
	}

}
