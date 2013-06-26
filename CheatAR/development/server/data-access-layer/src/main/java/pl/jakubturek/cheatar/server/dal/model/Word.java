package pl.jakubturek.cheatar.server.dal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="words")
public class Word implements Serializable
{
    @Id @GeneratedValue private Long id;
    private String word;
    private String hash;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public String getHash()
    {
        return hash;
    }

    public void setHash(String hash)
    {
        this.hash = hash;
    }

    @Override
    public String toString()
    {
        return word;
    }
}
