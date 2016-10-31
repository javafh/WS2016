package de.hsflensburg.java.gwt.ws2016.server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DemoServiceImplTest
{

	@Test
	public void testEscapeHtml()
	{
		assertEquals("&amp;", DemoServiceImpl.escapeHtml("&"));
		assertEquals("&lt;", DemoServiceImpl.escapeHtml("<"));
		assertEquals("&gt;", DemoServiceImpl.escapeHtml(">"));
		assertEquals("&lt;&amp;test&gt;",
				DemoServiceImpl.escapeHtml("<&test>"));
	}

}
