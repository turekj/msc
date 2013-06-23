package pl.jakubturek.cheatar.server.wordimporter.persistence;

import java.util.Collection;

public interface IDataPersister<T>
{
    public void persist(Collection<T> data);
}
