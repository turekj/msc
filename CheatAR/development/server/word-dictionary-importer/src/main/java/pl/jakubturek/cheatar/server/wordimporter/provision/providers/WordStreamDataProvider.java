package pl.jakubturek.cheatar.server.wordimporter.provision.providers;

import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.model.WordEntityWrapper;
import pl.jakubturek.cheatar.server.wordimporter.provision.impl.StreamDataProvider;

import java.io.InputStream;

public class WordStreamDataProvider extends StreamDataProvider<Word>
{
    public WordStreamDataProvider(InputStream inputStream)
    {
        super(inputStream);
    }

    @Override
    protected Word createEntity(String message)
    {
        return new WordEntityWrapper(message);
    }
}
