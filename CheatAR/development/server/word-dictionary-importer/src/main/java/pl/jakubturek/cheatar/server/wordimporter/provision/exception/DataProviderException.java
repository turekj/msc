package pl.jakubturek.cheatar.server.wordimporter.provision.exception;

public class DataProviderException extends RuntimeException
{
    public DataProviderException()
    {
        super();
    }

    public DataProviderException(String message)
    {
        super(message);
    }

    public DataProviderException(Throwable throwable)
    {
        super(throwable);
    }
}
