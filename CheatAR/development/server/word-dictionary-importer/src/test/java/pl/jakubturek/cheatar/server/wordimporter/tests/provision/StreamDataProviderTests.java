package pl.jakubturek.cheatar.server.wordimporter.tests.provision;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.wordimporter.creation.IFromStringFactory;
import pl.jakubturek.cheatar.server.wordimporter.creation.impl.FromStringFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.providers.StreamDataProvider;

import java.io.ByteArrayInputStream;
import java.util.List;

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
            IFromStringFactory fromStringFactory = new FromStringFactory();
            StreamDataProvider<Word> dataProvider = new StreamDataProvider<Word>(fromStringFactory);
            String heroBanshee = "hero" + "\n" + "banshee";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(heroBanshee.getBytes());

            List<Word> words = dataProvider.provide(Word.class, byteArrayInputStream);

            Assert.assertEquals(2, words.size());
            Word hero = words.get(0);
            Assert.assertEquals("hero", hero.getWord());
            Word banshee = words.get(1);
            Assert.assertEquals("banshee", banshee.getWord());
        }
    }
}
