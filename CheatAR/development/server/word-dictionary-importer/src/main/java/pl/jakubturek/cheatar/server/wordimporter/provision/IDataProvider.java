package pl.jakubturek.cheatar.server.wordimporter.provision;

import java.util.Collection;

public interface IDataProvider<T>
{
    public Collection<T> provide();
}
