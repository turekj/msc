package pl.jakubturek.cheatar.server.dal.aggregation;

import pl.jakubturek.cheatar.common.constants.ScrabbleBoardConstants;

import java.io.Serializable;

public class Characters implements Serializable
{
    private char[] characters = new char[ScrabbleBoardConstants.BOARD_SIZE];

    public char[] getCharacters()
    {
        return characters;
    }

    public void setCharacters(char[] characters)
    {
        this.characters = characters;
    }
}
