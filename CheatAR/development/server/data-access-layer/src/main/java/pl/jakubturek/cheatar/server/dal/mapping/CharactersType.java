package pl.jakubturek.cheatar.server.dal.mapping;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.CharacterType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import pl.jakubturek.cheatar.common.constants.ScrabbleBoardConstants;
import pl.jakubturek.cheatar.server.dal.aggregation.Characters;
import pl.jakubturek.cheatar.server.dal.constants.DALConstants;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class CharactersType implements CompositeUserType
{
    @Override
    public String[] getPropertyNames()
    {
        return DALConstants.CHARACTER_TYPE_PROPERTY_NAMES;
    }

    @Override
    public Type[] getPropertyTypes()
    {
        Type[] types = new Type[ScrabbleBoardConstants.BOARD_SIZE];
        Arrays.fill(types, CharacterType.INSTANCE);

        return types;
    }

    @Override
    public Object getPropertyValue(Object component, int propertyIndex) throws HibernateException
    {
        if (component == null)
        {
            return null;
        }

        if (isIndexValid(propertyIndex))
        {
            final Characters characters = (Characters) component;

            return characters.getCharacters()[propertyIndex];
        }

        throw new HibernateException("Invalid property index: " + propertyIndex);
    }

    private boolean isIndexValid(int propertyIndex)
    {
        return propertyIndex >= 0 && propertyIndex < ScrabbleBoardConstants.BOARD_SIZE;
    }

    @Override
    public void setPropertyValue(Object component, int propertyIndex, Object value) throws HibernateException
    {
        if (component == null)
        {
            return;
        }

        if (isIndexValid(propertyIndex))
        {
            final Characters characters = (Characters) component;

            characters.getCharacters()[propertyIndex] = (Character) value;
        }

        throw new HibernateException("Invalid property index: " + propertyIndex);
    }

    @Override
    public Class returnedClass()
    {
        return Characters.class;
    }

    @Override
    public boolean equals(Object firstComponent, Object secondComponent) throws HibernateException
    {
        final Characters firstCharacters = (Characters) firstComponent;
        final Characters secondCharacters = (Characters) secondComponent;

        return Arrays.equals(firstCharacters.getCharacters(), secondCharacters.getCharacters());
    }

    @Override
    public int hashCode(Object component) throws HibernateException
    {
        final Characters characters = (Characters) component;

        return Arrays.hashCode(characters.getCharacters());
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException
    {
        if (names == null || names.length != ScrabbleBoardConstants.BOARD_SIZE)
        {
            return null;
        }

        Characters characters = new Characters();

        for (int i = 0; i < names.length; ++i)
        {
            Character character = (Character) CharacterType.INSTANCE.get(resultSet, names[i], session);

            if (character != null)
            {
                characters.getCharacters()[i] = character;
            }
        }

        return characters;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
                            SessionImplementor sessionImplementor) throws HibernateException, SQLException
    {
        Characters characters = value != null ? (Characters) value : null;

        for (int i = 0; i < ScrabbleBoardConstants.BOARD_SIZE; ++i)
        {
            CharacterType.INSTANCE
                    .set(preparedStatement, characters != null ? characters.getCharacters()[i] : null, index + i,
                            sessionImplementor);
        }
    }

    @Override
    public Object deepCopy(Object component) throws HibernateException
    {
        Characters characters = (Characters) component;
        Characters charactersCopy = new Characters();
        charactersCopy.setCharacters(Arrays.copyOf(characters.getCharacters(), characters.getCharacters().length));

        return charactersCopy;
    }

    @Override
    public boolean isMutable()
    {
        return false;
    }

    @Override
    public Serializable disassemble(Object component, SessionImplementor sessionImplementor) throws HibernateException
    {
        return (Characters) component;
    }

    @Override
    public Object assemble(Serializable serializable, SessionImplementor sessionImplementor, Object component)
            throws HibernateException
    {
        return serializable;
    }

    @Override
    public Object replace(Object original, Object target, SessionImplementor sessionImplementor, Object owner)
            throws HibernateException
    {
        return original;
    }
}
