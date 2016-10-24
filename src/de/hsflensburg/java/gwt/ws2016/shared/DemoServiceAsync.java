package de.hsflensburg.java.gwt.ws2016.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**********************************
 * The asynchronous interface of the application service which is invoked by
 * client code.
 */
public interface DemoServiceAsync
{
	void sendText(String sText, AsyncCallback<String> rCallback)
			throws IllegalArgumentException;
}
