package pl.jakubturek.cheatar.server.wordimporter.persistence.impl;

import pl.jakubturek.cheatar.server.dal.dao.IDataAccessObjectFactory;
import pl.jakubturek.cheatar.server.wordimporter.persistence.IDataPersister;
import pl.jakubturek.cheatar.server.wordimporter.persistence.IDataPersisterFactory;
import pl.jakubturek.cheatar.server.wordimporter.persistence.persisters.DataPersister;

public class DataPersisterFactory implements IDataPersisterFactory
{
    private final IDataAccessObjectFactory dataAccessObjectFactory;

    public DataPersisterFactory(IDataAccessObjectFactory dataAccessObjectFactory)
    {
        this.dataAccessObjectFactory = dataAccessObjectFactory;
    }

    @Override
    public <T> IDataPersister<T> getDataPersister(Class<? extends T> entityClass)
    {
        return new DataPersister<T>(entityClass, dataAccessObjectFactory);
    }
}
