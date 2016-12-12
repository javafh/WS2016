package de.hsflensburg.java.gwt.ws2016.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hsflensburg.java.gwt.ws2016.server.database.Database;
import de.hsflensburg.java.gwt.ws2016.shared.AuthenticationException;
import de.hsflensburg.java.gwt.ws2016.shared.DemoService;

/**********************************
 * The server implementation of this application.
 */
@SuppressWarnings("serial")
public class DemoServiceImpl extends RemoteServiceServlet implements DemoService
{

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
	public void destroy()
	{
		Database.shutdownAll();

		super.destroy();
	}

	@Override
	public void init(ServletConfig rConfig) throws ServletException
	{
		super.init(rConfig);

		UserInfo aTestUserInfo = new UserInfo();

		aTestUserInfo.updatePassword("admin");

		Database<UserInfo> aUserDatabase = Database.getFor(UserInfo.class);

		aUserDatabase.store("admin", aTestUserInfo);
	}

	@Override
	public void login(String sUserName, String sPassword)
			throws AuthenticationException
	{
		Database<UserInfo> aUserDatabase = Database.getFor(UserInfo.class);

		UserInfo rUserInfo = aUserDatabase.retrieve(sUserName);

		if (rUserInfo != null && rUserInfo.isPasswordValid(sPassword))
		{
		}
		else
		{
			throw new AuthenticationException("AuthenticationFailed");
		}
	}
}
