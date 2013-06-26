package pl.jakubturek.cheatar.server.wordimporter.importing.impl;

import pl.jakubturek.cheatar.server.wordimporter.importing.IDataImporter;
import pl.jakubturek.cheatar.server.wordimporter.persistence.IDataPersister;
import pl.jakubturek.cheatar.server.wordimporter.persistence.IDataPersisterFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.IDataProviderFactory;
import pl.jakubturek.cheatar.server.wordimporter.provision.IStreamDataProvider;

import java.io.InputStream;

public class DataImporter<T> implements IDataImporter
{
    private final Class<? extends T> dataClass;
    private final IDataProviderFactory dataProviderFactory;
    private final IDataPersisterFactory dataPersisterFactory;

    public DataImporter(Class<? extends T> dataClass, IDataProviderFactory dataProviderFactory,
                        IDataPersisterFactory dataPersisterFactory)
    {
        this.dataClass = dataClass;
        this.dataProviderFactory = dataProviderFactory;
        this.dataPersisterFactory = dataPersisterFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void importData(InputStream inputStream)
    {
        IDataPersister<T> dataPersister = dataPersisterFactory.getDataPersister(dataClass);
        IStreamDataProvider<T> streamDataProvider =
                (IStreamDataProvider<T>) dataProviderFactory.createDataProvider(dataClass);

        dataPersister.persist(streamDataProvider.provide(dataClass, inputStream));
    }
}
