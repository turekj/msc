package pl.jakubturek.cheatar.server.wordimporter.creation.impl;

import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.creation.IFromStringCreator;
import pl.jakubturek.cheatar.server.wordimporter.creation.IFromStringFactory;
import pl.jakubturek.cheatar.server.wordimporter.creation.creators.WordFromStringCreator;
import pl.jakubturek.cheatar.server.wordimporter.creation.exception.NoSuchCreatorRegistered;

import java.util.LinkedHashMap;
import java.util.Map;

public class FromStringFactory implements IFromStringFactory
{
    private Map<Class<?>, IFromStringCreator<?>> creators = new LinkedHashMap<Class<?>, IFromStringCreator<?>>();

    public FromStringFactory()
    {
        registerCreators();
    }

    private void registerCreators()
    {
        creators.put(Word.class, new WordFromStringCreator());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T create(Class<? extends T> dataType, String objectRepresentation)
    {
        if (creators.containsKey(dataType))
        {
            return (T) creators.get(dataType).create(objectRepresentation);
        }

        throw new NoSuchCreatorRegistered(dataType.getName());
    }
}
