package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Decorator que valida que solo mayores de 18 años se registren
 */
public class DecoratorValidacionMayorEdad extends DecoratorEstudiante {

    public DecoratorValidacionMayorEdad(ServicioEstudiante servicio) {
        super(servicio);
    }

    private boolean esMayorDeEdad(int edad) {
        if (edad < 18) {
            JOptionPane.showMessageDialog(null,
                "⚠ Error de Validación:\n\n" +
                "• El estudiante debe ser mayor de 18 años\n" +
                "Edad ingresada: " + edad,
                "Menor de Edad",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public String registrarEstudiante(int id, String nombre, int edad) {
        if (!esMayorDeEdad(edad)) {
            return "Error: Estudiante menor de 18 años.";
        }
        return servicio.registrarEstudiante(id, nombre, edad);
    }

    @Override
    public String actualizarEstudiante(int id, String nombre, int edad) {
        if (!esMayorDeEdad(edad)) {
            return "Error: Estudiante menor de 18 años.";
        }
        return servicio.actualizarEstudiante(id, nombre, edad);
    }
}
