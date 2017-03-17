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
import net.kumasi.storytime.model.Comment;
import net.kumasi.storytime.model.Requirement;
import net.kumasi.storytime.test.CommentFactoryForTest;
import net.kumasi.storytime.test.RequirementFactoryForTest;

//--- Services 
import net.kumasi.storytime.business.service.CommentService;
import net.kumasi.storytime.business.service.RequirementService;

//--- List Items 
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
public class CommentControllerTest {
	
	@InjectMocks
	private CommentController commentController;
	@Mock
	private CommentService commentService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private RequirementService requirementService; // Injected by Spring

	private CommentFactoryForTest commentFactoryForTest = new CommentFactoryForTest();
	private RequirementFactoryForTest requirementFactoryForTest = new RequirementFactoryForTest();

	List<Requirement> requirements = new ArrayList<Requirement>();

	private void givenPopulateModel() {
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
		
		List<Comment> list = new ArrayList<Comment>();
		when(commentService.findAll()).thenReturn(list);
		
		// When
		String viewName = commentController.list(model);
		
		// Then
		assertEquals("comment/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = commentController.formForCreate(model);
		
		// Then
		assertEquals("comment/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Comment)modelMap.get("comment")).getIdcomment());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/comment/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Comment comment = commentFactoryForTest.newComment();
		Integer idcomment = comment.getIdcomment();
		when(commentService.findById(idcomment)).thenReturn(comment);
		
		// When
		String viewName = commentController.formForUpdate(model, idcomment);
		
		// Then
		assertEquals("comment/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(comment, (Comment) modelMap.get("comment"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/comment/update", modelMap.get("saveAction"));
		
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Comment comment = commentFactoryForTest.newComment();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Comment commentCreated = new Comment();
		when(commentService.create(comment)).thenReturn(commentCreated); 
		
		// When
		String viewName = commentController.create(comment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/comment/form/"+comment.getIdcomment(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(commentCreated, (Comment) modelMap.get("comment"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Comment comment = commentFactoryForTest.newComment();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = commentController.create(comment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("comment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(comment, (Comment) modelMap.get("comment"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/comment/create", modelMap.get("saveAction"));
		
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

		Comment comment = commentFactoryForTest.newComment();
		
		Exception exception = new RuntimeException("test exception");
		when(commentService.create(comment)).thenThrow(exception);
		
		// When
		String viewName = commentController.create(comment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("comment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(comment, (Comment) modelMap.get("comment"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/comment/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "comment.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Comment comment = commentFactoryForTest.newComment();
		Integer idcomment = comment.getIdcomment();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Comment commentSaved = new Comment();
		commentSaved.setIdcomment(idcomment);
		when(commentService.update(comment)).thenReturn(commentSaved); 
		
		// When
		String viewName = commentController.update(comment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/comment/form/"+comment.getIdcomment(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(commentSaved, (Comment) modelMap.get("comment"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Comment comment = commentFactoryForTest.newComment();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = commentController.update(comment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("comment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(comment, (Comment) modelMap.get("comment"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/comment/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
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

		Comment comment = commentFactoryForTest.newComment();
		
		Exception exception = new RuntimeException("test exception");
		when(commentService.update(comment)).thenThrow(exception);
		
		// When
		String viewName = commentController.update(comment, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("comment/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(comment, (Comment) modelMap.get("comment"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/comment/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "comment.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<RequirementListItem> requirementListItems = (List<RequirementListItem>) modelMap.get("listOfRequirementItems");
		assertEquals(2, requirementListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Comment comment = commentFactoryForTest.newComment();
		Integer idcomment = comment.getIdcomment();
		
		// When
		String viewName = commentController.delete(redirectAttributes, idcomment);
		
		// Then
		verify(commentService).delete(idcomment);
		assertEquals("redirect:/comment", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Comment comment = commentFactoryForTest.newComment();
		Integer idcomment = comment.getIdcomment();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(commentService).delete(idcomment);
		
		// When
		String viewName = commentController.delete(redirectAttributes, idcomment);
		
		// Then
		verify(commentService).delete(idcomment);
		assertEquals("redirect:/comment", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "comment.error.delete", exception);
	}
	
	
}
