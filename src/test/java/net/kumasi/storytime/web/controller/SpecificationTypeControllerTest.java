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
import net.kumasi.storytime.model.SpecificationType;
import net.kumasi.storytime.test.SpecificationTypeFactoryForTest;

//--- Services 
import net.kumasi.storytime.business.service.SpecificationTypeService;


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
public class SpecificationTypeControllerTest {
	
	@InjectMocks
	private SpecificationTypeController specificationTypeController;
	@Mock
	private SpecificationTypeService specificationTypeService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private SpecificationTypeFactoryForTest specificationTypeFactoryForTest = new SpecificationTypeFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<SpecificationType> list = new ArrayList<SpecificationType>();
		when(specificationTypeService.findAll()).thenReturn(list);
		
		// When
		String viewName = specificationTypeController.list(model);
		
		// Then
		assertEquals("specificationType/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = specificationTypeController.formForCreate(model);
		
		// Then
		assertEquals("specificationType/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((SpecificationType)modelMap.get("specificationType")).getIdrequirementType());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/specificationType/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		Integer idrequirementType = specificationType.getIdrequirementType();
		when(specificationTypeService.findById(idrequirementType)).thenReturn(specificationType);
		
		// When
		String viewName = specificationTypeController.formForUpdate(model, idrequirementType);
		
		// Then
		assertEquals("specificationType/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(specificationType, (SpecificationType) modelMap.get("specificationType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/specificationType/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		SpecificationType specificationTypeCreated = new SpecificationType();
		when(specificationTypeService.create(specificationType)).thenReturn(specificationTypeCreated); 
		
		// When
		String viewName = specificationTypeController.create(specificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/specificationType/form/"+specificationType.getIdrequirementType(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(specificationTypeCreated, (SpecificationType) modelMap.get("specificationType"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = specificationTypeController.create(specificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("specificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(specificationType, (SpecificationType) modelMap.get("specificationType"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/specificationType/create", modelMap.get("saveAction"));
		
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

		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		
		Exception exception = new RuntimeException("test exception");
		when(specificationTypeService.create(specificationType)).thenThrow(exception);
		
		// When
		String viewName = specificationTypeController.create(specificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("specificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(specificationType, (SpecificationType) modelMap.get("specificationType"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/specificationType/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "specificationType.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		Integer idrequirementType = specificationType.getIdrequirementType();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		SpecificationType specificationTypeSaved = new SpecificationType();
		specificationTypeSaved.setIdrequirementType(idrequirementType);
		when(specificationTypeService.update(specificationType)).thenReturn(specificationTypeSaved); 
		
		// When
		String viewName = specificationTypeController.update(specificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/specificationType/form/"+specificationType.getIdrequirementType(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(specificationTypeSaved, (SpecificationType) modelMap.get("specificationType"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = specificationTypeController.update(specificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("specificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(specificationType, (SpecificationType) modelMap.get("specificationType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/specificationType/update", modelMap.get("saveAction"));
		
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

		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		
		Exception exception = new RuntimeException("test exception");
		when(specificationTypeService.update(specificationType)).thenThrow(exception);
		
		// When
		String viewName = specificationTypeController.update(specificationType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("specificationType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(specificationType, (SpecificationType) modelMap.get("specificationType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/specificationType/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "specificationType.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		Integer idrequirementType = specificationType.getIdrequirementType();
		
		// When
		String viewName = specificationTypeController.delete(redirectAttributes, idrequirementType);
		
		// Then
		verify(specificationTypeService).delete(idrequirementType);
		assertEquals("redirect:/specificationType", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		SpecificationType specificationType = specificationTypeFactoryForTest.newSpecificationType();
		Integer idrequirementType = specificationType.getIdrequirementType();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(specificationTypeService).delete(idrequirementType);
		
		// When
		String viewName = specificationTypeController.delete(redirectAttributes, idrequirementType);
		
		// Then
		verify(specificationTypeService).delete(idrequirementType);
		assertEquals("redirect:/specificationType", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "specificationType.error.delete", exception);
	}
	
	
}
