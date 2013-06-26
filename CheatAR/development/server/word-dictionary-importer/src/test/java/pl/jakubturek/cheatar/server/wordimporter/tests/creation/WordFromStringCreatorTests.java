package pl.jakubturek.cheatar.server.wordimporter.tests.creation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.creation.creators.WordFromStringCreator;

@RunWith(Suite.class)
@Suite.SuiteClasses(
{
        WordFromStringCreatorTests.TheCreateMethodTests.class
})
public class WordFromStringCreatorTests
{
    public static class TheCreateMethodTests
    {
        @Test
        public void setsWord()
        {
            WordFromStringCreator creator = new WordFromStringCreator();

            Word word = creator.create("madness");

            Assert.assertEquals("madness", word.getWord());
        }

        @Test
        public void setsHash()
        {
            WordFromStringCreator creator = new WordFromStringCreator();

            Word word = creator.create("madness");

            Assert.assertEquals("ademnss", word.getHash());
        }

        @Test
        public void setsCharacters()
        {
            WordFromStringCreator creator = new WordFromStringCreator();

            Word word = creator.create("madness");
            char[] characters = word.getCharacters().getCharacters();

            Assert.assertEquals(15, characters.length);
            Assert.assertEquals('m', characters[0]);
            Assert.assertEquals('a', characters[1]);
            Assert.assertEquals('d', characters[2]);
            Assert.assertEquals('n', characters[3]);
            Assert.assertEquals('e', characters[4]);
            Assert.assertEquals('s', characters[5]);
            Assert.assertEquals('s', characters[6]);
            Assert.assertEquals('\0', characters[7]);
            Assert.assertEquals('\0', characters[8]);
            Assert.assertEquals('\0', characters[9]);
            Assert.assertEquals('\0', characters[10]);
            Assert.assertEquals('\0', characters[11]);
            Assert.assertEquals('\0', characters[12]);
            Assert.assertEquals('\0', characters[13]);
            Assert.assertEquals('\0', characters[14]);
        }
    }
}
