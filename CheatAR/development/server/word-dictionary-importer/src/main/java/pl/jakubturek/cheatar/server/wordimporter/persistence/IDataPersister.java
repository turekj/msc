package pl.jakubturek.cheatar.server.wordimporter.persistence;

import pl.jakubturek.cheatar.server.wordimporter.exception.DataPersisterException;

import java.util.Collection;

public interface IDataPersister<T>
{
    public void persist(Collection<T> data) throws DataPersisterException;
}
