package service;

import repository.CRUDRepository;
import java.util.List;

public abstract class BaseService<T, ID, R extends CRUDRepository<T, ID>> {
    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    // Operaciones CRUD

    // Obtiene todos
    public List<T> findAll() throws Exception {
        return repository.findAll();
    }

    // Obtiene por ID
    public T getById(ID id) throws Exception {
        return repository.getById(id);
    }

    // Salva
    public T save(T t) throws Exception {
        return repository.save(t);
    }

    // Actualiza
    public T update(T t) throws Exception {
        return repository.update(t);
    }

    // Elimina
    public T delete(T t) throws Exception {
        return repository.delete(t);
    }
}
