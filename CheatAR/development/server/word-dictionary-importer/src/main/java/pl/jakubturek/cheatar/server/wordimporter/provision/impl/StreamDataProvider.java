package pl.jakubturek.cheatar.server.wordimporter.provision.impl;

import pl.jakubturek.cheatar.server.wordimporter.creation.IFromStringFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.IStreamDataProvider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamDataProvider<T> implements IStreamDataProvider<T>
{
    private final IFromStringFactory fromStringFactory;
    private List<T> data = new ArrayList<T>();
    private InputStream inputStream;
    private Class<? extends T> providedDataType;
    private Scanner scanner;

    public StreamDataProvider(IFromStringFactory fromStringFactory)
    {
        this.fromStringFactory = fromStringFactory;
    }

    @Override
    public List<T> provide(Class<? extends T> providedDataType, InputStream inputStream)
    {
        this.inputStream = inputStream;
        this.providedDataType = providedDataType;

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

    private T createEntity(String objectRepresentation)
    {
        return fromStringFactory.create(providedDataType, objectRepresentation);
    }

    private void closeStream()
    {
        scanner.close();
    }
}
