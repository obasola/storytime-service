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
import net.kumasi.storytime.model.StatusCode;
import net.kumasi.storytime.test.StatusCodeFactoryForTest;

//--- Services 
import net.kumasi.storytime.business.service.StatusCodeService;


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
public class StatusCodeControllerTest {
	
	@InjectMocks
	private StatusCodeController statusCodeController;
	@Mock
	private StatusCodeService statusCodeService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private StatusCodeFactoryForTest statusCodeFactoryForTest = new StatusCodeFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<StatusCode> list = new ArrayList<StatusCode>();
		when(statusCodeService.findAll()).thenReturn(list);
		
		// When
		String viewName = statusCodeController.list(model);
		
		// Then
		assertEquals("statusCode/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = statusCodeController.formForCreate(model);
		
		// Then
		assertEquals("statusCode/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((StatusCode)modelMap.get("statusCode")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/statusCode/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		Integer id = statusCode.getId();
		when(statusCodeService.findById(id)).thenReturn(statusCode);
		
		// When
		String viewName = statusCodeController.formForUpdate(model, id);
		
		// Then
		assertEquals("statusCode/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(statusCode, (StatusCode) modelMap.get("statusCode"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/statusCode/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		StatusCode statusCodeCreated = new StatusCode();
		when(statusCodeService.create(statusCode)).thenReturn(statusCodeCreated); 
		
		// When
		String viewName = statusCodeController.create(statusCode, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/statusCode/form/"+statusCode.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(statusCodeCreated, (StatusCode) modelMap.get("statusCode"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = statusCodeController.create(statusCode, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("statusCode/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(statusCode, (StatusCode) modelMap.get("statusCode"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/statusCode/create", modelMap.get("saveAction"));
		
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

		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		
		Exception exception = new RuntimeException("test exception");
		when(statusCodeService.create(statusCode)).thenThrow(exception);
		
		// When
		String viewName = statusCodeController.create(statusCode, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("statusCode/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(statusCode, (StatusCode) modelMap.get("statusCode"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/statusCode/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "statusCode.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		Integer id = statusCode.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		StatusCode statusCodeSaved = new StatusCode();
		statusCodeSaved.setId(id);
		when(statusCodeService.update(statusCode)).thenReturn(statusCodeSaved); 
		
		// When
		String viewName = statusCodeController.update(statusCode, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/statusCode/form/"+statusCode.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(statusCodeSaved, (StatusCode) modelMap.get("statusCode"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = statusCodeController.update(statusCode, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("statusCode/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(statusCode, (StatusCode) modelMap.get("statusCode"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/statusCode/update", modelMap.get("saveAction"));
		
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

		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		
		Exception exception = new RuntimeException("test exception");
		when(statusCodeService.update(statusCode)).thenThrow(exception);
		
		// When
		String viewName = statusCodeController.update(statusCode, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("statusCode/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(statusCode, (StatusCode) modelMap.get("statusCode"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/statusCode/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "statusCode.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		Integer id = statusCode.getId();
		
		// When
		String viewName = statusCodeController.delete(redirectAttributes, id);
		
		// Then
		verify(statusCodeService).delete(id);
		assertEquals("redirect:/statusCode", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		StatusCode statusCode = statusCodeFactoryForTest.newStatusCode();
		Integer id = statusCode.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(statusCodeService).delete(id);
		
		// When
		String viewName = statusCodeController.delete(redirectAttributes, id);
		
		// Then
		verify(statusCodeService).delete(id);
		assertEquals("redirect:/statusCode", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "statusCode.error.delete", exception);
	}
	
	
}
