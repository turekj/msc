package pl.jakubturek.cheatar.server.wordimporter.creation.creators;

import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.creation.IFromStringCreator;

import java.util.Arrays;

public class WordFromStringCreator implements IFromStringCreator<Word>
{
    private String wordRepresentation;
    private Word word;

    @Override
    public Word create(String wordRepresentation)
    {
        this.wordRepresentation = wordRepresentation;
        this.word = new Word();

        setWord();
        setHash();

        return word;
    }

    private void setWord()
    {
        word.setWord(wordRepresentation);
    }

    private void setHash()
    {
        word.setHash(sortCharactersAlphabetically(wordRepresentation));
    }

    private String sortCharactersAlphabetically(String toSort)
    {
        char[] characters = toSort.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }
}
