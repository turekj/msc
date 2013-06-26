package pl.jakubturek.cheatar.server.dal.specification;

public interface ISpecification<T>
{
    public boolean isSpecifiedBy(T entity);
}
