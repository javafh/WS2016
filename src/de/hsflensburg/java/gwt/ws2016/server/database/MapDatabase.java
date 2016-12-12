package de.hsflensburg.java.gwt.ws2016.server.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MapDatabase<T> extends Database<T>
{
	private Class<?> rDatatype;

	private Map<String, T> aStorageMap = new HashMap<>();

	@Override
	public T retrieve(String sId)
	{
		return aStorageMap.get(sId);
	}

	@Override
	public void store(String sId, T rData)
	{
		aStorageMap.put(sId, rData);
		saveDatabase();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void init(Class<?> rDatatype) throws DatabaseException
	{
		this.rDatatype = rDatatype;
		try
		{
			File rDatabaseFile = getDatabaseFile(rDatatype);

			if (rDatabaseFile.exists())
			{
				try (ObjectInputStream aDatabaseStream = new ObjectInputStream(
						new FileInputStream(rDatabaseFile)))
				{
					aStorageMap = (Map<String, T>) aDatabaseStream.readObject();
				}

			}

		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}
	}

	@Override
	protected void shutdown()
	{
		saveDatabase();
	}

	private File getDatabaseFile(Class<?> rDatatype)
	{
		return new File(rDatatype.getSimpleName() + ".db");
	}

	private void saveDatabase()
	{
		File rDatabaseFile = getDatabaseFile(rDatatype);
		try
		{
			try (ObjectOutputStream aDatabaseOutput = new ObjectOutputStream(
					new FileOutputStream(rDatabaseFile)))
			{
				aDatabaseOutput.writeObject(aStorageMap);
			}
		}
		catch (Exception e)
		{
			throw new DatabaseException(
					"Could not save database file " + rDatabaseFile, e);
		}
	}

}