package net.kumasi.storytime.test;

import net.kumasi.storytime.model.jpa.StatusCodeEntity;

public class StatusCodeEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public StatusCodeEntity newStatusCodeEntity() {

		Integer id = mockValues.nextInteger();

		StatusCodeEntity statusCodeEntity = new StatusCodeEntity();
		statusCodeEntity.setId(id);
		return statusCodeEntity;
	}
	
}
