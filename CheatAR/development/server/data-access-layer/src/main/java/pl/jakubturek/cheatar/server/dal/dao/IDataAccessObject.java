package pl.jakubturek.cheatar.server.dal.dao;

import pl.jakubturek.cheatar.server.dal.specification.ISpecification;

import java.util.List;

public interface IDataAccessObject<T>
{
    public void persist(T entity);
    public void persistAll(List<T> entities);
    public List<T> fetchAll();
    public List<T> fetch(ISpecification<T> specification);
    public void delete(T entity);
    public void deleteAll(List<T> entity);
}
