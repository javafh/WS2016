package de.hsflensburg.java.gwt.ws2016.server.database;

public class DatabaseException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public DatabaseException()
	{
	}

	public DatabaseException(String rMessage)
	{
		super(rMessage);
	}

	public DatabaseException(String rMessage, Throwable rCause)
	{
		super(rMessage, rCause);
	}

	public DatabaseException(Throwable rCause)
	{
		super(rCause);
	}

}