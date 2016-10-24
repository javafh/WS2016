package de.hsflensburg.java.gwt.ws2016.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**********************************
 * The interface of the application service.
 */
@RemoteServiceRelativePath("demo")
public interface DemoService extends RemoteService
{
	String sendText(String sText) throws IllegalArgumentException;
}
