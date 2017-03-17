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
import net.kumasi.storytime.model.RequirementSpecificationType;
import net.kumasi.storytime.test.RequirementSpecificationTypeFactoryForTest;

//--- Services 
import net.kumasi.storytime.business.service.RequirementSpecificationTypeService;


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
public class RequirementSpecificationTypeControllerTest {
	
	@InjectMocks
	private RequirementSpecificationTypeController requirementSpecificationTypeController;
	@Mock
	private RequirementSpecificationTypeService requirementSpecificationTypeService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private RequirementSpecificationTypeFactoryForTest requirementSpecificationTypeFactoryForTest = new RequirementSpecificationTypeFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<RequirementSpecificationType> list = new ArrayList<RequirementSpecificationType>();
		when(requirementSpecificationTypeService.findAll()).thenReturn(list);
		
		// When
		String viewName = requirementSpecificationTypeController.list(model);
		
		// Then
		assertEquals("requirementSpecificationType/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = requirementSpecificationTypeController.formForCreate(model);
		
		// Then
		assertEquals("requirementSpecificationType/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((RequirementSpecificationType)modelMap.get("requirementSpecificationType")).getRequirementIdrequirement());
		assertNull(((RequirementSpecificationType)modelMap.get("requirementSpecificationType")).getSpecificationTypeIdrequirementType());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/requirementSpecificationType/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		Integer requirementIdrequirement = requirementSpecificationType.getRequirementIdrequirement();
		Integer specificationTypeIdrequirementType = requirementSpecificationType.getSpecificationTypeIdrequirementType();
		when(requirementSpecificationTypeService.findById(requirementIdrequirement, specificationTypeIdrequirementType)).thenReturn(requirementSpecificationType);
		
		// When
		String viewName = requirementSpecificationTypeController.formForUpdate(model, requirementIdrequirement, specificationTypeIdrequirementType);
		
		// Then
		assertEquals("requirementSpecificationType/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSpecificationType, (RequirementSpecificationType) modelMap.get("requirementSpecificationType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/requirementSpecificationType/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		RequirementSpecificationType requirementSpecificationTypeCreated = new RequirementSpecificationType();
		when(requirementSpecificationTypeService.create(requirementSpecificationType)).thenReturn(requirementSpecificationTypeCreated); 
		
		// When
		String viewName = requirementSpecificationTypeController.create(requirementSpecificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/requirementSpecificationType/form/"+requirementSpecificationType.getRequirementIdrequirement()+"/"+requirementSpecificationType.getSpecificationTypeIdrequirementType(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSpecificationTypeCreated, (RequirementSpecificationType) modelMap.get("requirementSpecificationType"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = requirementSpecificationTypeController.create(requirementSpecificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirementSpecificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSpecificationType, (RequirementSpecificationType) modelMap.get("requirementSpecificationType"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/requirementSpecificationType/create", modelMap.get("saveAction"));
		
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

		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		
		Exception exception = new RuntimeException("test exception");
		when(requirementSpecificationTypeService.create(requirementSpecificationType)).thenThrow(exception);
		
		// When
		String viewName = requirementSpecificationTypeController.create(requirementSpecificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirementSpecificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSpecificationType, (RequirementSpecificationType) modelMap.get("requirementSpecificationType"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/requirementSpecificationType/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "requirementSpecificationType.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		Integer requirementIdrequirement = requirementSpecificationType.getRequirementIdrequirement();
		Integer specificationTypeIdrequirementType = requirementSpecificationType.getSpecificationTypeIdrequirementType();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		RequirementSpecificationType requirementSpecificationTypeSaved = new RequirementSpecificationType();
		requirementSpecificationTypeSaved.setRequirementIdrequirement(requirementIdrequirement);
		requirementSpecificationTypeSaved.setSpecificationTypeIdrequirementType(specificationTypeIdrequirementType);
		when(requirementSpecificationTypeService.update(requirementSpecificationType)).thenReturn(requirementSpecificationTypeSaved); 
		
		// When
		String viewName = requirementSpecificationTypeController.update(requirementSpecificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/requirementSpecificationType/form/"+requirementSpecificationType.getRequirementIdrequirement()+"/"+requirementSpecificationType.getSpecificationTypeIdrequirementType(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSpecificationTypeSaved, (RequirementSpecificationType) modelMap.get("requirementSpecificationType"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = requirementSpecificationTypeController.update(requirementSpecificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirementSpecificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSpecificationType, (RequirementSpecificationType) modelMap.get("requirementSpecificationType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/requirementSpecificationType/update", modelMap.get("saveAction"));
		
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

		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		
		Exception exception = new RuntimeException("test exception");
		when(requirementSpecificationTypeService.update(requirementSpecificationType)).thenThrow(exception);
		
		// When
		String viewName = requirementSpecificationTypeController.update(requirementSpecificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("requirementSpecificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(requirementSpecificationType, (RequirementSpecificationType) modelMap.get("requirementSpecificationType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/requirementSpecificationType/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "requirementSpecificationType.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		Integer requirementIdrequirement = requirementSpecificationType.getRequirementIdrequirement();
		Integer specificationTypeIdrequirementType = requirementSpecificationType.getSpecificationTypeIdrequirementType();
		
		// When
		String viewName = requirementSpecificationTypeController.delete(redirectAttributes, requirementIdrequirement, specificationTypeIdrequirementType);
		
		// Then
		verify(requirementSpecificationTypeService).delete(requirementIdrequirement, specificationTypeIdrequirementType);
		assertEquals("redirect:/requirementSpecificationType", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		RequirementSpecificationType requirementSpecificationType = requirementSpecificationTypeFactoryForTest.newRequirementSpecificationType();
		Integer requirementIdrequirement = requirementSpecificationType.getRequirementIdrequirement();
		Integer specificationTypeIdrequirementType = requirementSpecificationType.getSpecificationTypeIdrequirementType();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(requirementSpecificationTypeService).delete(requirementIdrequirement, specificationTypeIdrequirementType);
		
		// When
		String viewName = requirementSpecificationTypeController.delete(redirectAttributes, requirementIdrequirement, specificationTypeIdrequirementType);
		
		// Then
		verify(requirementSpecificationTypeService).delete(requirementIdrequirement, specificationTypeIdrequirementType);
		assertEquals("redirect:/requirementSpecificationType", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "requirementSpecificationType.error.delete", exception);
	}
	
	
}
