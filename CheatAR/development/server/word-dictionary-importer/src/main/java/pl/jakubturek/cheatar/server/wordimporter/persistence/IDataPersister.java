package pl.jakubturek.cheatar.server.wordimporter.persistence;

import java.util.List;

public interface IDataPersister<T>
{
    public void persist(List<T> data);
}
