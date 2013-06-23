package pl.jakubturek.cheatar.server.wordimporter.provision.impl;

import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.exception.NoSuchDataProviderRegistered;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProvider;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProviderFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.providers.WordStreamDataProvider;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class StreamDataProviderFactory implements IDataProviderFactory
{
    private final InputStream inputStream;
    private Map<Class<?>, IDataProvider<?>> dataProviders = new LinkedHashMap<Class<?>, IDataProvider<?>>();

    public StreamDataProviderFactory(InputStream inputStream)
    {
        this.inputStream = inputStream;

        registerDataProviders();
    }

    private void registerDataProviders()
    {
        dataProviders.put(Word.class, new WordStreamDataProvider(inputStream));
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
