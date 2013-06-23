package pl.jakubturek.cheatar.server.wordimporter.provision;

import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProvider;

public interface IDataProviderFactory
{
    public IDataProvider createDataProvider(Class<?> dataType);
}
