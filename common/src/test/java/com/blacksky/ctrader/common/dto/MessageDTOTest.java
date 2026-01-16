package com.blacksky.ctrader.common.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageDTOTest {

	@Test
	public void testGettersAndSetters() {
		MessageDTO m = new MessageDTO();
		m.setTime("2026-01-01T00:00:00Z");
		m.setVersion("1.2.3");
		m.setHostname("host-1");

		assertEquals("2026-01-01T00:00:00Z", m.getTime());
		assertEquals("1.2.3", m.getVersion());
		assertEquals("host-1", m.getHostname());
		assertTrue(m.toString().contains("host-1"));
	}
}
