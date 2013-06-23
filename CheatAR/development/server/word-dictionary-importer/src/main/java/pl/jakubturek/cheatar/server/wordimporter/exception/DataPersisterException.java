package pl.jakubturek.cheatar.server.wordimporter.exception;

public class DataPersisterException extends RuntimeException
{
    public DataPersisterException()
    {
        super();
    }

    public DataPersisterException(String message)
    {
        super(message);
    }

    public DataPersisterException(Throwable throwable)
    {
        super(throwable);
    }
}
