package de.hsflensburg.java.gwt.ws2016.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The asynchronous server interface.
 */
public interface DemoServiceAsync
{
	void sendText(String sText, AsyncCallback<String> rCallback)
			throws IllegalArgumentException;
}
