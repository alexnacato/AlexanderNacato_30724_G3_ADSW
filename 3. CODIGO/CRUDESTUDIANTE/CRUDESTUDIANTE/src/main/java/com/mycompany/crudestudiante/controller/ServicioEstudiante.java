package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import java.util.List;

/**
 * Interfaz que define las operaciones del servicio de estudiantes
 * Permite que los decorators envuelvan el controlador
 */
public interface ServicioEstudiante {
    String registrarEstudiante(int id, String nombre, int edad);
    String actualizarEstudiante(int id, String nombre, int edad);
    String borrarEstudiante(int id);
    List<Estudiante> obtenerTodos();
}
