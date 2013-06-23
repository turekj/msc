package pl.jakubturek.cheatar.server.wordimporter.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.provision.providers.WordStreamDataProvider;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Iterator;

@RunWith(Suite.class)
@Suite.SuiteClasses(
{
        StreamDataProviderTests.TheProvideMethod.class
})
public class StreamDataProviderTests
{
    public static class TheProvideMethod
    {
        @Test
        public void providesWords()
        {
            String heroBanshee = "hero" + "\n" + "banshee";
            ByteArrayInputStream stream = new ByteArrayInputStream(heroBanshee.getBytes());
            WordStreamDataProvider dataProvider = new WordStreamDataProvider(stream);
            Collection<Word> words = dataProvider.provide();
            Iterator<Word> wordIterator = words.iterator();

            Assert.assertEquals(2, words.size());
            Word hero = wordIterator.next();
            Assert.assertEquals("hero", hero.getWord());
            Word banshee = wordIterator.next();
            Assert.assertEquals("banshee", banshee.getWord());
        }
    }
}
