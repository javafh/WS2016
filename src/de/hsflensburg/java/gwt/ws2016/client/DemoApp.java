package de.hsflensburg.java.gwt.ws2016.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import de.hsflensburg.java.gwt.ws2016.shared.DemoService;
import de.hsflensburg.java.gwt.ws2016.shared.DemoServiceAsync;

/**********************************
 * This class defines the main entry point of this application.
 */
public class DemoApp implements EntryPoint
{
	/**
	 * Create the remote service for communication with the server.
	 */
	private final DemoServiceAsync aDemoService = GWT.create(DemoService.class);

	/**********************************
	 * The starting method of the GWT {@link EntryPoint}, comparable to a main
	 * method in other application types.
	 */
	@Override
	public void onModuleLoad()
	{
	}
}
