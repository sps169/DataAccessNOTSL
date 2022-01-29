package repository;

import java.util.List;

public interface CRUDRepository<T, ID> {

    // Operaciones CRUD

    // Obtiene todos
    List<T> findAll() throws Exception;

    // Obtiene por ID
    T getById(ID id) throws Exception;

    // Salva
    T save(T t) throws Exception;

    // Actualiza
    T update(T t) throws Exception;

    // Elimina
    T delete(T t) throws Exception;


}
