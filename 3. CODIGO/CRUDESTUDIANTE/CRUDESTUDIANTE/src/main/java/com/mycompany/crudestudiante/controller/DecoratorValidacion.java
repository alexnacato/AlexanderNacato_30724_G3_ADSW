package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Decorator que valida los datos antes de procesar
 * Alerta si hay errores de validación
 */
public class DecoratorValidacion extends DecoratorEstudiante {

    public DecoratorValidacion(ServicioEstudiante servicio) {
        super(servicio);
    }

    private boolean validarDatos(int id, String nombre, int edad) {
        StringBuilder errores = new StringBuilder();

        if (id <= 0) {
            errores.append("• ID debe ser mayor a 0\n");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            errores.append("• Nombre no puede estar vacío\n");
        }

        if (edad < 0) {
            errores.append("• Edad no puede ser negativa\n");
        }

        if (edad > 100) {
            errores.append("• Edad no parece válida (mayor a 100)\n");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(null,
                "⚠ Error de Validación:\n\n" + errores.toString(),
                "Datos Inválidos",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public String registrarEstudiante(int id, String nombre, int edad) {
        if (!validarDatos(id, nombre, edad)) {
            return "Error: Datos inválidos.";
        }
        return servicio.registrarEstudiante(id, nombre, edad);
    }

    @Override
    public String actualizarEstudiante(int id, String nombre, int edad) {
        if (!validarDatos(id, nombre, edad)) {
            return "Error: Datos inválidos.";
        }
        return servicio.actualizarEstudiante(id, nombre, edad);
    }
}
