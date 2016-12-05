package de.hsflensburg.java.gwt.ws2016.server;

public class UserInfo
{
	private String sPasswordHash;

	public boolean isPasswordValid(String sPassword)
	{
		return BCrypt.checkpw(sPassword, sPasswordHash);
	}

	public void updatePassword(String sPassword)
	{
		sPasswordHash = BCrypt.hashpw(sPassword, BCrypt.gensalt());
	}
}
