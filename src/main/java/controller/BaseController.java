package controller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controlador generico que implementa un mapper para salidas en JSON
 */
public class BaseController {
    protected static ObjectMapper jsonMapper = new ObjectMapper();


}
