package net.kumasi.storytime.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//--- Entities
import net.kumasi.storytime.model.PersonAssignment;
import net.kumasi.storytime.model.Person;
import net.kumasi.storytime.model.Requirement;
import net.kumasi.storytime.test.PersonAssignmentFactoryForTest;
import net.kumasi.storytime.test.PersonFactoryForTest;
import net.kumasi.storytime.test.RequirementFactoryForTest;

//--- Services 
import net.kumasi.storytime.business.service.PersonAssignmentService;
import net.kumasi.storytime.business.service.PersonService;
import net.kumasi.storytime.business.service.RequirementService;

//--- List Items 
import net.kumasi.storytime.web.listitem.PersonListItem;
import net.kumasi.storytime.web.listitem.RequirementListItem;

import net.kumasi.storytime.web.common.Message;
import net.kumasi.storytime.web.common.MessageHelper;
import net.kumasi.storytime.web.common.MessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
public class PersonAssignmentControllerTest {
	
	@InjectMocks
	private PersonAssignmentController personAssignmentController;
	@Mock
	private PersonAssignmentService personAssignmentService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private PersonService personService; // Injected by Spring
	@Mock
	private RequirementService requirementService; // Injected by Spring

	private PersonAssignmentFactoryForTest personAssignmentFactoryForTest = new PersonAssignmentFactoryForTest();
	private PersonFactoryForTest personFactoryForTest = new PersonFactoryForTest();
	private RequirementFactoryForTest requirementFactoryForTest = new RequirementFactoryForTest();

	List<Person> persons = new ArrayList<Person>();
	List<Requirement> requirements = new ArrayList<Requirement>();

	private void givenPopulateModel() {
		Person person1 = personFactoryForTest.newPerson();
		Person person2 = personFactoryForTest.newPerson();
		List<Person> persons = new ArrayList<Person>();
		persons.add(person1);
		persons.add(person2);
		when(personService.findAll()).thenReturn(persons);

		Requirement requirement1 = requirementFactoryForTest.newRequirement();
		Requirement requirement2 = requirementFactoryForTest.newRequirement();
		List<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(requirement1);
		requirements.add(requirement2);
		when(requirementService.findAll()).thenReturn(requirements);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<PersonAssignment> list = new ArrayList<PersonAssignment>();
		when(personAssignmentService.findAll()).thenReturn(list);
		
		// When
		String viewName = personAssignmentController.list(model);
		
		// Then
		assertEquals("personAssignment/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = personAssignmentController.formForCreate(model);
		
		// Then
		assertEquals("personAssignment/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((PersonAssignment)modelMap.get("personAssignment")).getPersonIdperson());
		assertNull(((PersonAssignment)modelMap.get("personAssignment")).getRequirementIdrequirement());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/personAssignment/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<PersonListItem> personListItems = (List<PersonListItem>) modelMap.get("listOfPersonItems");
		assertEquals(2, personListItems.size());
		
		@SuppressWarnings("unchecked")
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		Integer personIdperson = personAssignment.getPersonIdperson();
		Integer requirementIdrequirement = personAssignment.getRequirementIdrequirement();
		when(personAssignmentService.findById(personIdperson, requirementIdrequirement)).thenReturn(personAssignment);
		
		// When
		String viewName = personAssignmentController.formForUpdate(model, personIdperson, requirementIdrequirement);
		
		// Then
		assertEquals("personAssignment/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(personAssignment, (PersonAssignment) modelMap.get("personAssignment"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/personAssignment/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		PersonAssignment personAssignmentCreated = new PersonAssignment();
		when(personAssignmentService.create(personAssignment)).thenReturn(personAssignmentCreated); 
		
		// When
		String viewName = personAssignmentController.create(personAssignment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/personAssignment/form/"+personAssignment.getPersonIdperson()+"/"+personAssignment.getRequirementIdrequirement(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(personAssignmentCreated, (PersonAssignment) modelMap.get("personAssignment"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = personAssignmentController.create(personAssignment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("personAssignment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(personAssignment, (PersonAssignment) modelMap.get("personAssignment"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/personAssignment/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<PersonListItem> personListItems = (List<PersonListItem>) modelMap.get("listOfPersonItems");
		assertEquals(2, personListItems.size());
		
		@SuppressWarnings("unchecked")
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		
		Exception exception = new RuntimeException("test exception");
		when(personAssignmentService.create(personAssignment)).thenThrow(exception);
		
		// When
		String viewName = personAssignmentController.create(personAssignment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("personAssignment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(personAssignment, (PersonAssignment) modelMap.get("personAssignment"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/personAssignment/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "personAssignment.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<PersonListItem> personListItems = (List<PersonListItem>) modelMap.get("listOfPersonItems");
		assertEquals(2, personListItems.size());
		
		@SuppressWarnings("unchecked")
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		Integer personIdperson = personAssignment.getPersonIdperson();
		Integer requirementIdrequirement = personAssignment.getRequirementIdrequirement();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		PersonAssignment personAssignmentSaved = new PersonAssignment();
		personAssignmentSaved.setPersonIdperson(personIdperson);
		personAssignmentSaved.setRequirementIdrequirement(requirementIdrequirement);
		when(personAssignmentService.update(personAssignment)).thenReturn(personAssignmentSaved); 
		
		// When
		String viewName = personAssignmentController.update(personAssignment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/personAssignment/form/"+personAssignment.getPersonIdperson()+"/"+personAssignment.getRequirementIdrequirement(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(personAssignmentSaved, (PersonAssignment) modelMap.get("personAssignment"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = personAssignmentController.update(personAssignment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("personAssignment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(personAssignment, (PersonAssignment) modelMap.get("personAssignment"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/personAssignment/update", modelMap.get("saveAction"));
		
	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		
		Exception exception = new RuntimeException("test exception");
		when(personAssignmentService.update(personAssignment)).thenThrow(exception);
		
		// When
		String viewName = personAssignmentController.update(personAssignment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("personAssignment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(personAssignment, (PersonAssignment) modelMap.get("personAssignment"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/personAssignment/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "personAssignment.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		Integer personIdperson = personAssignment.getPersonIdperson();
		Integer requirementIdrequirement = personAssignment.getRequirementIdrequirement();
		
		// When
		String viewName = personAssignmentController.delete(redirectAttributes, personIdperson, requirementIdrequirement);
		
		// Then
		verify(personAssignmentService).delete(personIdperson, requirementIdrequirement);
		assertEquals("redirect:/personAssignment", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		PersonAssignment personAssignment = personAssignmentFactoryForTest.newPersonAssignment();
		Integer personIdperson = personAssignment.getPersonIdperson();
		Integer requirementIdrequirement = personAssignment.getRequirementIdrequirement();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(personAssignmentService).delete(personIdperson, requirementIdrequirement);
		
		// When
		String viewName = personAssignmentController.delete(redirectAttributes, personIdperson, requirementIdrequirement);
		
		// Then
		verify(personAssignmentService).delete(personIdperson, requirementIdrequirement);
		assertEquals("redirect:/personAssignment", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "personAssignment.error.delete", exception);
	}
	
	
}
