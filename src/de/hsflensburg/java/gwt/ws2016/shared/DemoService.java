package de.hsflensburg.java.gwt.ws2016.shared;

import javax.naming.AuthenticationException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**********************************
 * The interface of the application service.
 */
@RemoteServiceRelativePath("demo")
public interface DemoService extends RemoteService
{
	public void login(String sUserName, String sPassword) throws AuthenticationException;
}
