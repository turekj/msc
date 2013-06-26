package pl.jakubturek.cheatar.server.wordimporter.persistence;

public interface IDataPersisterFactory
{
    public <T> IDataPersister<T> getDataPersister(Class<? extends T> entityClass);
}
