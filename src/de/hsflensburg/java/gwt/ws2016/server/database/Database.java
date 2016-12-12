package de.hsflensburg.java.gwt.ws2016.server.database;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Database<T>
{
	@SuppressWarnings("unchecked")
	private static Class<? extends Database<?>> aDefaultDatabaseType = (Class<? extends Database<?>>) MapDatabase.class;

	private static Map<Class<?>, Database<?>> aDatabases = new HashMap<>();

	@SuppressWarnings("unchecked")
	public static <T> Database<T> getFor(Class<T> rDatatype)
	{
		Database<T> aDatabase = (Database<T>) aDatabases.get(rDatatype);

		if (aDatabase == null)
		{
			try
			{
				aDatabase = (Database<T>) aDefaultDatabaseType.newInstance();

				aDatabase.init(rDatatype);
				aDatabases.put(rDatatype, aDatabase);
			}
			catch (Exception e)
			{
				throw new IllegalStateException(e);
			}
		}
		return aDatabase;
	}

	public static void shutdownAll()
	{
		Iterator<Database<?>> rDbIterator = aDatabases.values().iterator();

		while (rDbIterator.hasNext())
		{
			Database<?> rDatabase = rDbIterator.next();

			rDatabase.shutdown();
			rDbIterator.remove();
		}

	}

	public abstract T retrieve(String sId) throws DatabaseException;

	public abstract void store(String sId, T rData) throws DatabaseException;

	protected abstract void init(Class<?> rDatatype) throws DatabaseException;

	protected abstract void shutdown();

}
