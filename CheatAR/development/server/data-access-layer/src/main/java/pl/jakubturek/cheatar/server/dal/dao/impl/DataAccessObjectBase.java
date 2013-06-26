package pl.jakubturek.cheatar.server.dal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.jakubturek.cheatar.server.dal.dao.IDataAccessObject;
import pl.jakubturek.cheatar.server.dal.util.SessionFactoryBuilder;

public abstract class DataAccessObjectBase<T> implements IDataAccessObject<T>
{
    private final Class<? extends T> entityClass;
    private SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactoryInstance();
    private Session session;

    public DataAccessObjectBase(Class<? extends T> entityClass)
    {
        this.entityClass = entityClass;
    }

    protected Class<? extends T> getEntityClass()
    {
        return entityClass;
    }

    protected void beginTransaction()
    {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    protected Session getSession()
    {
        return session;
    }

    protected void commitTransaction()
    {
        session.getTransaction().commit();
        session.close();
    }
}
