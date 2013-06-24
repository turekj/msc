package pl.jakubturek.cheatar.server.wordimporter.creation;

public interface IFromStringCreator<T>
{
    public T create(String objectRepresentation);
}
