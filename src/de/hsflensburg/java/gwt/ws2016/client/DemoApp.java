package de.hsflensburg.java.gwt.ws2016.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hsflensburg.java.gwt.ws2016.shared.DemoService;
import de.hsflensburg.java.gwt.ws2016.shared.DemoServiceAsync;

/**********************************
 * This class defines the main entry point of this application.
 */
public class DemoApp implements EntryPoint, AsyncCallback<Void>
{
	/**
	 * Create the remote service for communication with the server.
	 */
	private final DemoServiceAsync aDemoService = GWT.create(DemoService.class);

	@Override
	public void onFailure(Throwable rCaught)
	{
		RootPanel.get().remove(0);
		RootPanel.get().add(new Label("Login fehlgeschlagen"));
	}

	/**********************************
	 * The starting method of the GWT {@link EntryPoint}, comparable to a main
	 * method in other application types.
	 */
	@Override
	public void onModuleLoad()
	{
		LoginPanel aLoginPanel = new LoginPanel((sUser,
				sPassword) -> aDemoService.login(sUser, sPassword, this));

		RootPanel.get().add(aLoginPanel);
	}

	@Override
	public void onSuccess(Void rResult)
	{
		RootPanel.get().remove(0);
		RootPanel.get().add(new Label("Login erfolgreich"));
	}
}
