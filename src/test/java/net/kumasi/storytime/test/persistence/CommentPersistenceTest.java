/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.test.persistence;


import net.kumasi.storytime.model.jpa.CommentEntity;
import net.kumasi.storytime.mock.CommentEntityMock;
import net.kumasi.storytime.persistence.PersistenceServiceProvider;
import net.kumasi.storytime.persistence.services.CommentPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Comment persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class CommentPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		CommentPersistence service = PersistenceServiceProvider.getService(CommentPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Comment persistence : delete + load ..." );
		
		CommentPersistence service = PersistenceServiceProvider.getService(CommentPersistence.class);
		
		CommentEntityMock mock = new CommentEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(CommentPersistence service, CommentEntityMock mock, Integer idcomment ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		CommentEntity entity = service.load( idcomment );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( idcomment ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . Requirement
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( idcomment );
			System.out.println("   deleted = " + deleted);
		}		
	}
}
