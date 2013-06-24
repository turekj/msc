package pl.jakubturek.cheatar.server.wordimporter.provision.impl;

import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.creation.IFromStringFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProviderFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.IStreamDataProvider;
import pl.jakubturek.cheatar.server.wordimporter.provision.exception.NoSuchDataProviderRegistered;

import java.util.LinkedHashMap;
import java.util.Map;

public class StreamDataProviderFactory implements IDataProviderFactory
{
    private final IFromStringFactory fromStringFactory;
    private Map<Class<?>, IStreamDataProvider<?>> dataProviders = new LinkedHashMap<Class<?>, IStreamDataProvider<?>>();

    public StreamDataProviderFactory(IFromStringFactory fromStringFactory)
    {
        this.fromStringFactory = fromStringFactory;

        registerDataProviders();
    }

    private void registerDataProviders()
    {
        dataProviders.put(Word.class, new StreamDataProvider<Word>(fromStringFactory));
    }

    @Override
    public IStreamDataProvider createDataProvider(Class<?> dataType)
    {
        if (dataProviders.containsKey(dataType))
        {
            return dataProviders.get(dataType);
        }

        throw new NoSuchDataProviderRegistered(dataType.getName());
    }
}
