/*
 * Created on 16 Mar 2017 ( Time 18:41:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import net.kumasi.storytime.model.Comment;
import net.kumasi.storytime.model.jpa.CommentEntity;
import net.kumasi.storytime.business.service.mapping.CommentServiceMapper;
import net.kumasi.storytime.persistence.services.jpa.CommentPersistenceJPA;
import net.kumasi.storytime.test.CommentFactoryForTest;
import net.kumasi.storytime.test.CommentEntityFactoryForTest;
import net.kumasi.storytime.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of CommentService
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

	@InjectMocks
	private CommentServiceImpl commentService;
	@Mock
	private CommentPersistenceJPA commentPersistenceJPA;
	@Mock
	private CommentServiceMapper commentServiceMapper;
	
	private CommentFactoryForTest commentFactoryForTest = new CommentFactoryForTest();

	private CommentEntityFactoryForTest commentEntityFactoryForTest = new CommentEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Integer idcomment = mockValues.nextInteger();
		
		CommentEntity commentEntity = commentPersistenceJPA.load(idcomment);
		
		Comment comment = commentFactoryForTest.newComment();
		when(commentServiceMapper.mapCommentEntityToComment(commentEntity)).thenReturn(comment);

		// When
		Comment commentFound = commentService.findById(idcomment);

		// Then
		assertEquals(comment.getIdcomment(),commentFound.getIdcomment());
	}

	@Test
	public void findAll() {
		// Given
		List<CommentEntity> commentEntitys = new ArrayList<CommentEntity>();
		CommentEntity commentEntity1 = commentEntityFactoryForTest.newCommentEntity();
		commentEntitys.add(commentEntity1);
		CommentEntity commentEntity2 = commentEntityFactoryForTest.newCommentEntity();
		commentEntitys.add(commentEntity2);
		when(commentPersistenceJPA.loadAll()).thenReturn(commentEntitys);
		
		Comment comment1 = commentFactoryForTest.newComment();
		when(commentServiceMapper.mapCommentEntityToComment(commentEntity1)).thenReturn(comment1);
		Comment comment2 = commentFactoryForTest.newComment();
		when(commentServiceMapper.mapCommentEntityToComment(commentEntity2)).thenReturn(comment2);

		// When
		List<Comment> commentsFounds = commentService.findAll();

		// Then
		assertTrue(comment1 == commentsFounds.get(0));
		assertTrue(comment2 == commentsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Comment comment = commentFactoryForTest.newComment();

		CommentEntity commentEntity = commentEntityFactoryForTest.newCommentEntity();
		when(commentPersistenceJPA.load(comment.getIdcomment())).thenReturn(null);
		
		commentEntity = new CommentEntity();
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentPersistenceJPA.save(commentEntity);
		
		Comment commentSaved = commentFactoryForTest.newComment();
		when(commentServiceMapper.mapCommentEntityToComment(commentEntitySaved)).thenReturn(commentSaved);

		// When
		Comment commentResult = commentService.create(comment);

		// Then
		assertTrue(commentResult == commentSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Comment comment = commentFactoryForTest.newComment();

		CommentEntity commentEntity = commentEntityFactoryForTest.newCommentEntity();
		when(commentPersistenceJPA.load(comment.getIdcomment())).thenReturn(commentEntity);

		// When
		Exception exception = null;
		try {
			commentService.create(comment);
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
		Comment comment = commentFactoryForTest.newComment();

		CommentEntity commentEntity = commentEntityFactoryForTest.newCommentEntity();
		when(commentPersistenceJPA.load(comment.getIdcomment())).thenReturn(commentEntity);
		
		CommentEntity commentEntitySaved = commentEntityFactoryForTest.newCommentEntity();
		when(commentPersistenceJPA.save(commentEntity)).thenReturn(commentEntitySaved);
		
		Comment commentSaved = commentFactoryForTest.newComment();
		when(commentServiceMapper.mapCommentEntityToComment(commentEntitySaved)).thenReturn(commentSaved);

		// When
		Comment commentResult = commentService.update(comment);

		// Then
		verify(commentServiceMapper).mapCommentToCommentEntity(comment, commentEntity);
		assertTrue(commentResult == commentSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer idcomment = mockValues.nextInteger();

		// When
		commentService.delete(idcomment);

		// Then
		verify(commentPersistenceJPA).delete(idcomment);
		
	}

}
