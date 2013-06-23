package pl.jakubturek.cheatar.server.wordimporter.model;

import pl.jakubturek.cheatar.server.dal.model.Word;

import java.util.Arrays;

public class WordEntityWrapper extends Word
{
    private final String word;

    public WordEntityWrapper(String word)
    {
        this.word = word;

        initializeEntity();
    }

    private void initializeEntity()
    {
        initializeWord();
        initializeHash();
    }

    private void initializeWord()
    {
        setWord(word);
    }

    private void initializeHash()
    {
        String hashedWord = sortWordAlphabetically();
        setHash(hashedWord);
    }

    private String sortWordAlphabetically()
    {
        char[] characters = word.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }
}
