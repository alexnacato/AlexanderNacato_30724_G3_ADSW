package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import com.mycompany.crudestudiante.repository.RepositorioEstudiante;
import java.util.List;

public class ControlEstudiante {

    private RepositorioEstudiante repositorio = new RepositorioEstudiante();

    public boolean validarDatos(int id, String nombre, int edad) {
        return id > 0
            && nombre != null && !nombre.trim().isEmpty()
            && edad >= 0;
    }

    public String registrarEstudiante(int id, String nombre, int edad) {
        if (!validarDatos(id, nombre, edad))  return "Error: Datos inválidos.";
        if (repositorio.existeId(id))         return "Error: El ID ya está registrado.";

        repositorio.guardar(new Estudiante(id, nombre, edad));
        return "Éxito: Estudiante registrado.";
    }

    public String actualizarEstudiante(int id, String nombre, int edad) {
        if (!validarDatos(id, nombre, edad))  return "Error: Datos inválidos.";
        if (!repositorio.existeId(id))        return "Error: ID no encontrado.";

        repositorio.actualizar(new Estudiante(id, nombre, edad));
        return "Éxito: Estudiante actualizado.";
    }

    public String borrarEstudiante(int id) {
        if (!repositorio.existeId(id)) return "Error: ID no encontrado.";

        repositorio.eliminar(id);
        return "Éxito: Estudiante eliminado.";
    }

    public List<Estudiante> obtenerTodos() {
        return repositorio.listarTodos();
    }
}