package pl.jakubturek.cheatar.server.dal.dao.impl;

import pl.jakubturek.cheatar.server.dal.dao.IDataAccessObject;
import pl.jakubturek.cheatar.server.dal.dao.IDataAccessObjectFactory;
import pl.jakubturek.cheatar.server.dal.dao.accessors.DefaultDataAccessObject;

public class DataAccessObjectFactory implements IDataAccessObjectFactory
{
    @Override
    public <T> IDataAccessObject<?> getDataAccessObject(Class<? extends T> entityClass)
    {
        return new DefaultDataAccessObject<T>(entityClass);
    }
}
