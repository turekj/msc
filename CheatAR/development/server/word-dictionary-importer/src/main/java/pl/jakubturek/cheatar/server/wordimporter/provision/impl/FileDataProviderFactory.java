package pl.jakubturek.cheatar.server.wordimporter.provision.impl;

import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.exception.NoSuchDataProviderRegistered;
import pl.jakubturek.cheatar.server.wordimporter.model.WordEntityWrapper;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProvider;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProviderFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class FileDataProviderFactory implements IDataProviderFactory
{
    private final String filename;
    private Map<Class<?>, IDataProvider<?>> dataProviders = new LinkedHashMap<Class<?>, IDataProvider<?>>();

    public FileDataProviderFactory(String filename)
    {
        this.filename = filename;

        registerDataProviders();
    }

    private void registerDataProviders()
    {
        registerWordDataProvider();
    }

    private void registerWordDataProvider()
    {
        dataProviders.put(Word.class, new FileDataProvider<Word>(filename)
        {
            @Override
            protected Word createEntity(String message)
            {
                return new WordEntityWrapper(message);
            }
        });
    }

    @Override
    public IDataProvider createDataProvider(Class<?> dataType)
    {
        if (dataProviders.containsKey(dataType))
        {
            return dataProviders.get(dataType);
        }

        throw new NoSuchDataProviderRegistered(dataType.getName());
    }
}
