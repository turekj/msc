package pl.jakubturek.cheatar.server.dal.exception;

public class SessionFactoryInitializationFailed extends RuntimeException
{
    public SessionFactoryInitializationFailed()
    {
        super();
    }

    public SessionFactoryInitializationFailed(String message)
    {
        super(message);
    }

    public SessionFactoryInitializationFailed(Throwable throwable)
    {
        super(throwable);
    }
}
