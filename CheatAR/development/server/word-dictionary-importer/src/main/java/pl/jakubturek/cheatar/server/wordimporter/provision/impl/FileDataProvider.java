package pl.jakubturek.cheatar.server.wordimporter.provision.impl;

import pl.jakubturek.cheatar.server.wordimporter.exception.DataProviderException;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public abstract class FileDataProvider<T> implements IDataProvider<T>
{
    private final String filename;

    private Collection<T> data;
    private BufferedReader fileReader;

    public FileDataProvider(String filename)
    {
        this.filename = filename;
    }

    @Override
    public Collection<T> provide()
    {
        try
        {
            readData();
        }
        catch (IOException e)
        {
            try
            {
                fileReader.close();
            }
            catch (IOException ex)
            { }

            throw new DataProviderException(e);
        }

        return data;
    }

    private void readData() throws IOException
    {
        openFileReader();
        readDataFromFile();
        closeFileReader();
    }

    private void openFileReader() throws FileNotFoundException
    {
        fileReader = new BufferedReader(new FileReader(filename));
    }

    private void readDataFromFile() throws IOException
    {
        String line;

        do
        {
            line = fileReader.readLine();
            data.add(createEntity(line));
        }
        while (line != null);
    }

    protected abstract T createEntity(String message);

    private void closeFileReader() throws IOException
    {
        fileReader.close();
    }
}
