package net.kumasi.storytime.test;

import net.kumasi.storytime.model.StatusCode;

public class StatusCodeFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public StatusCode newStatusCode() {

		Integer id = mockValues.nextInteger();

		StatusCode statusCode = new StatusCode();
		statusCode.setId(id);
		return statusCode;
	}
	
}
