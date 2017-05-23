package com.mulesoft.training;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	@Rule
	public DynamicPort dynamicPort = new DynamicPort("http.port");

	@Test
	public void mavenFlowReturnsHelloMaven() throws Exception {
		System.out.println("$$$$$$$$$$$$$$$ DynamicPort = " + dynamicPort.getNumber());
		runFlowAndExpect("mavenFlow", "Hello Maven");
	}

	@Test
	public void retrieveFlightsAddsAppropriateHeader() throws Exception {
		System.out.println("$$$$$$$$$$$$$$$ DynamicPort = " + dynamicPort.getNumber());
		MuleEvent event = runFlow("retrieveFlightsFlow");
		String contentType = event.getMessage().getOutboundProperty("Content-Type");
		assertEquals("application/json", contentType);
	}

	@Override
	protected String getConfigFile() {
		return "maven-project.xml";
	}

}
