package pl.jakubturek.cheatar.server.wordimporter.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.jakubturek.cheatar.server.wordimporter.model.WordEntityWrapper;

@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    WordEntityWrapperTests.TheConstructorMethodTests.class
})
public class WordEntityWrapperTests
{
    public static class TheConstructorMethodTests
    {
        @Test
        public void setsWordProperty()
        {
            WordEntityWrapper word = new WordEntityWrapper("unittests");

            Assert.assertEquals("unittests", word.getWord());
        }

        @Test
        public void sortsHashAlphabetically()
        {
            WordEntityWrapper word = new WordEntityWrapper("madness");

            Assert.assertEquals("ademnss", word.getHash());
        }
    }
}
