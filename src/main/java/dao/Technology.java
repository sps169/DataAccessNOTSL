package dao;

import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Clase Enum que modela Technology. Implementa Etiquetas JSON para el modelo
 * de la base de datos.
 */
public enum Technology {
    @JsonValue Java,
    @JsonValue Kotlin,
    @JsonValue C,
    @JsonValue Javascript,
    @JsonValue Python
}
