package dao;


import com.fasterxml.jackson.annotation.JsonValue;

public enum Technology {
    @JsonValue Java,
    @JsonValue Kotlin,
    @JsonValue C,
    @JsonValue Javascript,
    @JsonValue Python
}
