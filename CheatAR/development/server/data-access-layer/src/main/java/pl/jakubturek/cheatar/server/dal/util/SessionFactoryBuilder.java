package pl.jakubturek.cheatar.server.dal.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import pl.jakubturek.cheatar.server.dal.exception.SessionFactoryInitializationFailed;
import pl.jakubturek.cheatar.server.dal.mapping.CharactersType;

public class SessionFactoryBuilder
{
    private static final ServiceRegistry serviceRegistry = configureServiceRegistry();
    private static final SessionFactory sessionFactory = configureSessionFactory();

    private static ServiceRegistry configureServiceRegistry()
    {
        try
        {
            Configuration configuration = new Configuration().configure();

            return new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        }
        catch (HibernateException e)
        {
            throw new SessionFactoryInitializationFailed(e);
        }
    }

    private static SessionFactory configureSessionFactory()
    {
        try
        {
            return new Configuration().configure().buildSessionFactory(serviceRegistry);
        }
        catch (HibernateException e)
        {
            throw new SessionFactoryInitializationFailed(e);
        }
    }

    public static SessionFactory getSessionFactoryInstance()
    {
        return sessionFactory;
    }
}
