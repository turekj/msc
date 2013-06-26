package pl.jakubturek.cheatar.server.dal.dao.exception;

public class DataAccessException extends RuntimeException
{
    public DataAccessException()
    {
        super();
    }

    public DataAccessException(String message)
    {
        super(message);
    }

    public DataAccessException(Throwable throwable)
    {
        super(throwable);
    }
}
