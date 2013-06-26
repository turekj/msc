package pl.jakubturek.cheatar.server.dal.dao;

public interface IDataAccessObjectFactory
{
    public <T> IDataAccessObject<?> getDataAccessObject(Class<? extends T> dataClass);
}
