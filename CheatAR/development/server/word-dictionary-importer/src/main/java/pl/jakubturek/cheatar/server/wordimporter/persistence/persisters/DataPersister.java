package pl.jakubturek.cheatar.server.wordimporter.persistence.persisters;

import pl.jakubturek.cheatar.server.dal.dao.IDataAccessObject;
import pl.jakubturek.cheatar.server.dal.dao.IDataAccessObjectFactory;
import pl.jakubturek.cheatar.server.wordimporter.persistence.IDataPersister;

import java.util.List;

public class DataPersister<T> implements IDataPersister<T>
{
    private final Class<? extends T> entityClass;
    private final IDataAccessObjectFactory dataAccessObjectFactory;

    public DataPersister(Class<? extends T> entityClass, IDataAccessObjectFactory dataAccessObjectFactory)
    {
        this.entityClass = entityClass;
        this.dataAccessObjectFactory = dataAccessObjectFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void persist(List<T> data)
    {
        IDataAccessObject<T> dataAccessObject =
                (IDataAccessObject<T>) dataAccessObjectFactory.getDataAccessObject(entityClass);
        dataAccessObject.persistAll(data);
    }
}
