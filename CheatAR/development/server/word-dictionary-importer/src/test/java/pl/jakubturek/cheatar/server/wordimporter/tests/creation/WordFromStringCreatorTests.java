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
    }
}
