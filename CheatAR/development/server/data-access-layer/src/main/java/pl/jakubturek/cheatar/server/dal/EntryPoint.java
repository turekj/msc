package pl.jakubturek.cheatar.server.dal;

import pl.jakubturek.cheatar.server.dal.exception.BadArgumentsException;
import pl.jakubturek.cheatar.server.dal.util.SQLGenerator;

public class EntryPoint
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new BadArgumentsException("You have to specify output filename");
        }

        new SQLGenerator().generateSQL(args[0]);
    }
}
