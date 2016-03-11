package com.gergo.takacs.rest;

import org.junit.Test;

public class ApplicationIntegrationTest {

	@Test
	public void testMainIfConfigurationIsOk() {
		System.setProperty("spring.profiles.active", "cache");
		String[] args = new String[0];
		Application.main(args);
	}

}
