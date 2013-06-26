package pl.jakubturek.cheatar.server.dal.dao.accessors;

import pl.jakubturek.cheatar.server.dal.dao.impl.DataAccessObjectBase;
import pl.jakubturek.cheatar.server.dal.specification.ISpecification;

import java.util.Iterator;
import java.util.List;

public class DefaultDataAccessObject<T> extends DataAccessObjectBase<T>
{
    private List<T> fetchedEntities;

    public DefaultDataAccessObject(Class<? extends T> entityClass)
    {
        super(entityClass);
    }

    @Override
    public void persist(T entity)
    {
        beginTransaction();
        persistEntity(entity);
        commitTransaction();
    }

    private void persistEntity(T entity)
    {
        getSession().save(entity);
    }

    @Override
    public void persistAll(List<T> entities)
    {
        beginTransaction();
        persistEntities(entities);
        commitTransaction();
    }

    private void persistEntities(List<T> entities)
    {
        for(T entity : entities)
        {
            persistEntity(entity);
        }
    }

    @Override
    public List<T> fetchAll()
    {
        beginTransaction();
        fetchAllEntities();
        commitTransaction();

        return fetchedEntities;
    }

    @SuppressWarnings("unchecked")
    private void fetchAllEntities()
    {
        fetchedEntities = getSession().createCriteria(getEntityClass()).list();
    }

    @Override
    public List<T> fetch(ISpecification<T> specification)
    {
        beginTransaction();
        fetchAllEntities();
        commitTransaction();
        filterOutEntities(specification);

        return fetchedEntities;
    }

    private void filterOutEntities(ISpecification<T> specification)
    {
        for (Iterator<T> i = fetchedEntities.iterator(); i.hasNext(); )
        {
            T entity = i.next();

            if (!specification.isSpecifiedBy(entity))
            {
                i.remove();
            }
        }
    }

    @Override
    public void delete(T entity)
    {
        beginTransaction();
        deleteEntity(entity);
        commitTransaction();
    }

    private void deleteEntity(T entity)
    {
        getSession().delete(entity);
    }

    @Override
    public void deleteAll(List<T> entities)
    {
        beginTransaction();
        deleteAllEntities(entities);
        commitTransaction();
    }

    private void deleteAllEntities(List<T> entities)
    {
        for (T entity : entities)
        {
            deleteEntity(entity);
        }
    }
}
