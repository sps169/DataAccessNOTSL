package repository;

import java.util.List;

/**
 * Interfaz que obliga a implementar las operaciones CRUD
 * retornando el objeto resultante de la operacion
 * @param <T> tipo de objeto
 * @param <ID> tipo de id del objeto
 */
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
