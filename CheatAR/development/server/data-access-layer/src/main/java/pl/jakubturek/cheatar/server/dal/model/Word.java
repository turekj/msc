package pl.jakubturek.cheatar.server.dal.model;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import pl.jakubturek.cheatar.server.dal.aggregation.Characters;
import pl.jakubturek.cheatar.server.dal.mapping.CharactersType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "words")
@TypeDef(name = "characters", typeClass = CharactersType.class)
public class Word implements Serializable
{
    @Id @GeneratedValue private Long id;
    private String word;
    private String hash;
    @Type(type = "characters")
    @Columns(columns =
    {
            @Column(name = "firstChar"),
            @Column(name = "secondChar"),
            @Column(name = "thirdChar"),
            @Column(name = "fourthChar"),
            @Column(name = "fifthChar"),
            @Column(name = "sixthChar"),
            @Column(name = "seventhChar"),
            @Column(name = "eighthChar"),
            @Column(name = "ninthChar"),
            @Column(name = "tenthChar"),
            @Column(name = "eleventhChar"),
            @Column(name = "twelfthChar"),
            @Column(name = "thirteenthChar"),
            @Column(name = "fourteenthChar"),
            @Column(name = "fifteenthChar")
    })
    private Characters characters;

    public Characters getCharacters()
    {
        return characters;
    }

    public void setCharacters(Characters characters)
    {
        this.characters = characters;
    }

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
