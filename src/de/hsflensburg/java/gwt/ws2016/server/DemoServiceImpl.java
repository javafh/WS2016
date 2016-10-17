package de.hsflensburg.java.gwt.ws2016.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hsflensburg.java.gwt.ws2016.shared.DemoService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DemoServiceImpl extends RemoteServiceServlet implements DemoService
{
	@Override
	public String sendText(String sText) throws IllegalArgumentException
	{
		String sServerInfo = getServletContext().getServerInfo();
		String sUserAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		sText = escapeHtml(sText);
		sUserAgent = escapeHtml(sUserAgent);

		return "Hello! This server is running " + sServerInfo
				+ ".<p>It looks like you are using:<br>" + sUserAgent
				+ "<p> The text you sent is:<br>" + sText;
	}

	/**
	 * Escape an HTML string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 *
	 * @param sText the HTML string to escape
	 *
	 * @return the escaped string
	 */
	private String escapeHtml(String sText)
	{
		if (sText != null)
		{
			sText = sText.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;");
		}
		return sText;
	}
}
