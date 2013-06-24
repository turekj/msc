package pl.jakubturek.cheatar.server.wordimporter.creation;

public interface IFromStringFactory
{
    public <T> T create(Class<? extends T> dataType, String objectRepresentation);
}
