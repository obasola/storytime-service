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
import net.kumasi.storytime.model.Requirement;
import net.kumasi.storytime.model.SpecificationType;
import net.kumasi.storytime.model.StatusCode;
import net.kumasi.storytime.test.RequirementFactoryForTest;
import net.kumasi.storytime.test.SpecificationTypeFactoryForTest;
import net.kumasi.storytime.test.StatusCodeFactoryForTest;

//--- Services 
import net.kumasi.storytime.business.service.RequirementService;
import net.kumasi.storytime.business.service.SpecificationTypeService;
import net.kumasi.storytime.business.service.StatusCodeService;

//--- List Items 
import net.kumasi.storytime.web.listitem.SpecificationTypeListItem;
import net.kumasi.storytime.web.listitem.StatusCodeListItem;

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
public class RequirementControllerTest {
	
	@InjectMocks
	private RequirementController requirementController;
	@Mock
	private RequirementService requirementService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private SpecificationTypeService specificationTypeService; // Injected by Spring
	@Mock
	private StatusCodeService statusCodeService; // Injected by Spring

	private RequirementFactoryForTest requirementFactoryForTest = new RequirementFactoryForTest();
	private SpecificationTypeFactoryForTest specificationTypeFactoryForTest = new SpecificationTypeFactoryForTest();
	private StatusCodeFactoryForTest statusCodeFactoryForTest = new StatusCodeFactoryForTest();

	List<SpecificationType> specificationTypes = new ArrayList<SpecificationType>();
	List<StatusCode> statusCodes = new ArrayList<StatusCode>();

	private void givenPopulateModel() {
		SpecificationType specificationType1 = specificationTypeFactoryForTest.newSpecificationType();
		SpecificationType specificationType2 = specificationTypeFactoryForTest.newSpecificationType();
		List<SpecificationType> specificationTypes = new ArrayList<SpecificationType>();
		specificationTypes.add(specificationType1);
		specificationTypes.add(specificationType2);
		when(specificationTypeService.findAll()).thenReturn(specificationTypes);

		StatusCode statusCode1 = statusCodeFactoryForTest.newStatusCode();
		StatusCode statusCode2 = statusCodeFactoryForTest.newStatusCode();
		List<StatusCode> statusCodes = new ArrayList<StatusCode>();
		statusCodes.add(statusCode1);
		statusCodes.add(statusCode2);
		when(statusCodeService.findAll()).thenReturn(statusCodes);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Requirement> list = new ArrayList<Requirement>();
		when(requirementService.findAll()).thenReturn(list);
		
		// When
		String viewName = requirementController.list(model);
		
		// Then
		assertEquals("requirement/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = requirementController.formForCreate(model);
		
		// Then
		assertEquals("requirement/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Requirement)modelMap.get("requirement")).getIdrequirement());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/requirement/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<SpecificationTypeListItem> specificationTypeListItems = (List<SpecificationTypeListItem>) modelMap.get("listOfSpecificationTypeItems");
		assertEquals(2, specificationTypeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<StatusCodeListItem> statusCodeListItems = (List<StatusCodeListItem>) modelMap.get("listOfStatusCodeItems");
		assertEquals(2, statusCodeListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Requirement requirement = requirementFactoryForTest.newRequirement();
		Integer idrequirement = requirement.getIdrequirement();
		when(requirementService.findById(idrequirement)).thenReturn(requirement);
		
		// When
		String viewName = requirementController.formForUpdate(model, idrequirement);
		
		// Then
		assertEquals("requirement/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirement, (Requirement) modelMap.get("requirement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/requirement/update", modelMap.get("saveAction"));
		
		List<StatusCodeListItem> statusCodeListItems = (List<StatusCodeListItem>) modelMap.get("listOfStatusCodeItems");
		assertEquals(2, statusCodeListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Requirement requirement = requirementFactoryForTest.newRequirement();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Requirement requirementCreated = new Requirement();
		when(requirementService.create(requirement)).thenReturn(requirementCreated); 
		
		// When
		String viewName = requirementController.create(requirement, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/requirement/form/"+requirement.getIdrequirement(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementCreated, (Requirement) modelMap.get("requirement"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Requirement requirement = requirementFactoryForTest.newRequirement();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = requirementController.create(requirement, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirement/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirement, (Requirement) modelMap.get("requirement"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/requirement/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<SpecificationTypeListItem> specificationTypeListItems = (List<SpecificationTypeListItem>) modelMap.get("listOfSpecificationTypeItems");
		assertEquals(2, specificationTypeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<StatusCodeListItem> statusCodeListItems = (List<StatusCodeListItem>) modelMap.get("listOfStatusCodeItems");
		assertEquals(2, statusCodeListItems.size());
		
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

		Requirement requirement = requirementFactoryForTest.newRequirement();
		
		Exception exception = new RuntimeException("test exception");
		when(requirementService.create(requirement)).thenThrow(exception);
		
		// When
		String viewName = requirementController.create(requirement, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirement/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirement, (Requirement) modelMap.get("requirement"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/requirement/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "requirement.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<SpecificationTypeListItem> specificationTypeListItems = (List<SpecificationTypeListItem>) modelMap.get("listOfSpecificationTypeItems");
		assertEquals(2, specificationTypeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<StatusCodeListItem> statusCodeListItems = (List<StatusCodeListItem>) modelMap.get("listOfStatusCodeItems");
		assertEquals(2, statusCodeListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Requirement requirement = requirementFactoryForTest.newRequirement();
		Integer idrequirement = requirement.getIdrequirement();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Requirement requirementSaved = new Requirement();
		requirementSaved.setIdrequirement(idrequirement);
		when(requirementService.update(requirement)).thenReturn(requirementSaved); 
		
		// When
		String viewName = requirementController.update(requirement, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/requirement/form/"+requirement.getIdrequirement(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSaved, (Requirement) modelMap.get("requirement"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Requirement requirement = requirementFactoryForTest.newRequirement();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = requirementController.update(requirement, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirement/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirement, (Requirement) modelMap.get("requirement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/requirement/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<StatusCodeListItem> statusCodeListItems = (List<StatusCodeListItem>) modelMap.get("listOfStatusCodeItems");
		assertEquals(2, statusCodeListItems.size());
		
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

		Requirement requirement = requirementFactoryForTest.newRequirement();
		
		Exception exception = new RuntimeException("test exception");
		when(requirementService.update(requirement)).thenThrow(exception);
		
		// When
		String viewName = requirementController.update(requirement, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirement/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirement, (Requirement) modelMap.get("requirement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/requirement/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "requirement.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<StatusCodeListItem> statusCodeListItems = (List<StatusCodeListItem>) modelMap.get("listOfStatusCodeItems");
		assertEquals(2, statusCodeListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Requirement requirement = requirementFactoryForTest.newRequirement();
		Integer idrequirement = requirement.getIdrequirement();
		
		// When
		String viewName = requirementController.delete(redirectAttributes, idrequirement);
		
		// Then
		verify(requirementService).delete(idrequirement);
		assertEquals("redirect:/requirement", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Requirement requirement = requirementFactoryForTest.newRequirement();
		Integer idrequirement = requirement.getIdrequirement();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(requirementService).delete(idrequirement);
		
		// When
		String viewName = requirementController.delete(redirectAttributes, idrequirement);
		
		// Then
		verify(requirementService).delete(idrequirement);
		assertEquals("redirect:/requirement", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "requirement.error.delete", exception);
	}
	
	
}
