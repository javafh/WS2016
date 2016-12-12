package de.hsflensburg.java.gwt.ws2016.server;

import java.io.Serializable;

public class UserInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

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
