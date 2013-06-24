package pl.jakubturek.cheatar.server.wordimporter.provision.exception;

public class NoSuchDataProviderRegistered extends RuntimeException
{
    public NoSuchDataProviderRegistered()
    {
        super();
    }

    public NoSuchDataProviderRegistered(String message)
    {
        super(message);
    }

    public NoSuchDataProviderRegistered(Throwable throwable)
    {
        super(throwable);
    }
}
