package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import java.util.List;

/**
 * Clase base para todos los decorators
 * Envuelve el servicio y delega las operaciones
 */
public abstract class DecoratorEstudiante implements ServicioEstudiante {
    protected ServicioEstudiante servicio;

    public DecoratorEstudiante(ServicioEstudiante servicio) {
        this.servicio = servicio;
    }

    @Override
    public String registrarEstudiante(int id, String nombre, int edad) {
        return servicio.registrarEstudiante(id, nombre, edad);
    }

    @Override
    public String actualizarEstudiante(int id, String nombre, int edad) {
        return servicio.actualizarEstudiante(id, nombre, edad);
    }

    @Override
    public String borrarEstudiante(int id) {
        return servicio.borrarEstudiante(id);
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        return servicio.obtenerTodos();
    }
}
