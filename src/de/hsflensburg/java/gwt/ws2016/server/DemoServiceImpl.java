package de.hsflensburg.java.gwt.ws2016.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

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
	public void destroy()
	{
		try (OutputStream aDatabaseFile = new FileOutputStream(
				"userdatabase.db"))
		{
			ObjectOutputStream aDatabaseStream = new ObjectOutputStream(
					aDatabaseFile);

			aDatabaseStream.writeObject(aUserDatabase);
		}
		catch (IOException e)
		{
			System.err.printf("ERROR: Could not store database (%s)\n",
					e.getMessage());
		}

		super.destroy();
	}

	@Override
	public void init(ServletConfig rConfig) throws ServletException
	{
		super.init(rConfig);

		UserInfo aTestUserInfo = new UserInfo();

		aTestUserInfo.updatePassword("admin");
		aUserDatabase.put("admin", aTestUserInfo);
	}

	@Override
	public void login(String sUserName, String sPassword)
			throws AuthenticationException
	{
		UserInfo rUserInfo = aUserDatabase.get(sUserName);

		if (rUserInfo != null && rUserInfo.isPasswordValid(sPassword))
		{
		}
		else
		{
			throw new AuthenticationException("AuthenticationFailed");
		}
	}
}
