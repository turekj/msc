package pl.jakubturek.cheatar.server.orm.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.jakubturek.cheatar.server.orm.model.Word;
import pl.jakubturek.cheatar.server.orm.util.SessionFactoryBuilder;

public class OrmEntryPoint
{
    private static SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactoryInstance();

    public static void main(String[] args)
    {
        new OrmEntryPoint().persist(createWord());

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
