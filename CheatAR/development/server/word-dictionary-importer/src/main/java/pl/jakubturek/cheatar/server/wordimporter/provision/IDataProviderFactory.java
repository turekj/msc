package pl.jakubturek.cheatar.server.wordimporter.provision;

public interface IDataProviderFactory
{
    public IDataProvider createDataProvider(Class<?> dataType);
}
