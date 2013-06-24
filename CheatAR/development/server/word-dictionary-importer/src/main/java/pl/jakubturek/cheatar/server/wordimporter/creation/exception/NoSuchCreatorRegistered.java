package pl.jakubturek.cheatar.server.wordimporter.creation.exception;

public class NoSuchCreatorRegistered extends RuntimeException
{
    public NoSuchCreatorRegistered()
    {
        super();
    }

    public NoSuchCreatorRegistered(String message)
    {
        super(message);
    }

    public NoSuchCreatorRegistered(Throwable throwable)
    {
        super(throwable);
    }
}
