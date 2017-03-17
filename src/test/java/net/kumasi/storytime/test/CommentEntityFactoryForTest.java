package net.kumasi.storytime.test;

import net.kumasi.storytime.model.jpa.CommentEntity;

public class CommentEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public CommentEntity newCommentEntity() {

		Integer idcomment = mockValues.nextInteger();

		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setIdcomment(idcomment);
		return commentEntity;
	}
	
}
