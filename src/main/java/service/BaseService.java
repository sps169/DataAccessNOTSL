package service;

import repository.CRUDRepository;
import java.util.List;

/**
 * clase abstracta que implementa mapeos basicos de los repositorios
 * a los servicios. Implementa la interfaz CRUDRepository
 * @param <T> Tipo de dato para el que damos servicio
 * @param <ID> Tipo de clave del dato
 * @param <R> Tipo de repositorio del dato
 */
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
