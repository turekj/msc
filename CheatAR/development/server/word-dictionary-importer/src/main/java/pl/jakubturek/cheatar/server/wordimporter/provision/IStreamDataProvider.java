package pl.jakubturek.cheatar.server.wordimporter.provision;

import java.io.InputStream;
import java.util.List;

public interface IStreamDataProvider<T>
{
    public List<T> provide(Class<? extends T> providedDataType, InputStream inputStream);
}
