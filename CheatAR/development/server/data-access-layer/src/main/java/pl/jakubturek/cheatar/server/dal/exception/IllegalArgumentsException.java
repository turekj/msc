package pl.jakubturek.cheatar.server.dal.exception;

public class IllegalArgumentsException extends RuntimeException
{
    public IllegalArgumentsException()
    {
        super();
    }

    public IllegalArgumentsException(String message)
    {
        super(message);
    }

    public IllegalArgumentsException(Throwable throwable)
    {
        super(throwable);
    }
}
