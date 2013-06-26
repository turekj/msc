package pl.jakubturek.cheatar.server.dal.exception;

public class BadArgumentsException extends RuntimeException
{
    public BadArgumentsException()
    {
        super();
    }

    public BadArgumentsException(String message)
    {
        super(message);
    }

    public BadArgumentsException(Throwable throwable)
    {
        super(throwable);
    }
}
