package de.hsflensburg.java.gwt.ws2016.server;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hsflensburg.java.gwt.ws2016.shared.AuthenticationException;
import de.hsflensburg.java.gwt.ws2016.shared.DemoService;

/**********************************
 * The server implementation of this application.
 */
@SuppressWarnings("serial")
public class DemoServiceImpl extends RemoteServiceServlet implements DemoService
{
	private Map<String, UserInfo> aUserDatabase = new HashMap<>();

	/**********************************
	 * Escape an HTML string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 *
	 * @param sText the HTML string to escape
	 *
	 * @return the escaped string
	 */
	public static String escapeHtml(String sText)
	{
		if (sText != null)
		{
			sText = sText.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;");
		}
		return sText;
	}

	@Override
	public void login(String sUserName, String sPassword)
			throws AuthenticationException
	{
		if ("test".equals(sUserName) && "test".equals(sPassword))
		{
		}
		else
		{
			throw new AuthenticationException("AuthenticationFailed");
		}
	}
}
