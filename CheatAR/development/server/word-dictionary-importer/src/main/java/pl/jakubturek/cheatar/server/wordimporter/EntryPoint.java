package pl.jakubturek.cheatar.server.wordimporter;

import pl.jakubturek.cheatar.server.dal.dao.IDataAccessObjectFactory;
import pl.jakubturek.cheatar.server.dal.dao.impl.DataAccessObjectFactory;
import pl.jakubturek.cheatar.server.dal.exception.IllegalArgumentsException;
import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.creation.IFromStringFactory;
import pl.jakubturek.cheatar.server.wordimporter.creation.impl.FromStringFactory;
import pl.jakubturek.cheatar.server.wordimporter.importing.IDataImporter;
import pl.jakubturek.cheatar.server.wordimporter.importing.impl.DataImporter;
import pl.jakubturek.cheatar.server.wordimporter.persistence.IDataPersisterFactory;
import pl.jakubturek.cheatar.server.wordimporter.persistence.impl.DataPersisterFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProviderFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.impl.StreamDataProviderFactory;

public class EntryPoint
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentsException("You have to specify imported resources filename");
        }

        IFromStringFactory fromStringFactory = new FromStringFactory();
        IDataProviderFactory dataProviderFactory = new StreamDataProviderFactory(fromStringFactory);
        IDataAccessObjectFactory dataAccessObjectFactory = new DataAccessObjectFactory();
        IDataPersisterFactory dataPersisterFactory = new DataPersisterFactory(dataAccessObjectFactory);
        IDataImporter dataImporter = new DataImporter<Word>(Word.class, dataProviderFactory, dataPersisterFactory);

        dataImporter.importData(EntryPoint.class.getResourceAsStream(args[0]));
    }
}
