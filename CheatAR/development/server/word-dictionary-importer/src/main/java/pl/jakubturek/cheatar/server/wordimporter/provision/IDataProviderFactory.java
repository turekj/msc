package pl.jakubturek.cheatar.server.wordimporter.provision;

public interface IDataProviderFactory
{
    public IStreamDataProvider<?> createDataProvider(Class<?> dataType);
}
