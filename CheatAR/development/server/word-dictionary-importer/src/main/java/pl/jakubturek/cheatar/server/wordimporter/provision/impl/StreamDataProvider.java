package pl.jakubturek.cheatar.server.wordimporter.provision.impl;

import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProvider;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

public abstract class StreamDataProvider<T> implements IDataProvider<T>
{
    private final InputStream inputStream;

    private Collection<T> data = new LinkedHashSet<T>();
    private Scanner scanner;

    public StreamDataProvider(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public Collection<T> provide()
    {
        readData();
        return data;
    }

    private void readData()
    {
        openStream();
        readDataFromStream();
        closeStream();
    }

    private void openStream()
    {
        scanner = new Scanner(inputStream);
    }

    private void readDataFromStream()
    {
        String line;

        do
        {
            line = scanner.nextLine();
            data.add(createEntity(line));
        }
        while (scanner.hasNextLine());
    }

    protected abstract T createEntity(String message);

    private void closeStream()
    {
        scanner.close();
    }
}
