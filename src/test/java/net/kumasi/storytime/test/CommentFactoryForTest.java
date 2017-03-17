package net.kumasi.storytime.test;

import net.kumasi.storytime.model.Comment;

public class CommentFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Comment newComment() {

		Integer idcomment = mockValues.nextInteger();

		Comment comment = new Comment();
		comment.setIdcomment(idcomment);
		return comment;
	}
	
}
