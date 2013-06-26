package pl.jakubturek.cheatar.server.wordimporter.importing;

import java.io.InputStream;

public interface IDataImporter
{
    public void importData(InputStream inputStream);
}
