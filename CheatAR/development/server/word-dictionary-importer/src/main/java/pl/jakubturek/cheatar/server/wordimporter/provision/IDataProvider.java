package pl.jakubturek.cheatar.server.wordimporter.provision;

import pl.jakubturek.cheatar.server.wordimporter.exception.DataProviderException;

import java.util.Collection;

public interface IDataProvider<T>
{
    public Collection<T> provide() throws DataProviderException;
}
