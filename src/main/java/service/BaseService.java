package service;

import repository.CRUDRepository;
import java.util.List;

public abstract class BaseService<T, ID, R extends CRUDRepository<T, ID>> {
    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    public List<T> findAll() throws Exception {
        return repository.findAll();
    }

    public T getById(ID id) throws Exception {
        return repository.getById(id);
    }

    public T save(T t) throws Exception {
        return repository.save(t);
    }

    public T update(T t) throws Exception {
        return repository.update(t);
    }

    public T delete(T t) throws Exception {
        return repository.delete(t);
    }
}
