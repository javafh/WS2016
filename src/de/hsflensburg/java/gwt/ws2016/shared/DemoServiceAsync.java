package de.hsflensburg.java.gwt.ws2016.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**********************************
 * The asynchronous interface of the application service which is invoked by
 * client code.
 */
public interface DemoServiceAsync
{
	void login(String sUserName, String sPassword, AsyncCallback<Void> rCallback);
}
