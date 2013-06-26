package pl.jakubturek.cheatar.server.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.jakubturek.cheatar.server.dal.model.Word;
import pl.jakubturek.cheatar.server.dal.util.SessionFactoryBuilder;

public class EntryPoint
{
    private static SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactoryInstance();

    public static void main(String[] args)
    {
        new EntryPoint().persist(createWord());

        sessionFactory.close();
    }

    private void persist(Word word)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(word);
        session.getTransaction().commit();
    }

    private static Word createWord()
    {
        Word word = new Word();

        word.setId(1L);
        word.setWord("krowa");
        word.setHash("akorw");

        return word;
    }
}
