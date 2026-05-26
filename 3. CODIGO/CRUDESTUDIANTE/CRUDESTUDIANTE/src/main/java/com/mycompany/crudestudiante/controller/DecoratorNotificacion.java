package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Decorator que muestra notificaciones cuando se realizan acciones
 */
public class DecoratorNotificacion extends DecoratorEstudiante {

    public DecoratorNotificacion(ServicioEstudiante servicio) {
        super(servicio);
    }

    @Override
    public String registrarEstudiante(int id, String nombre, int edad) {
        String resultado = servicio.registrarEstudiante(id, nombre, edad);
        
        if (resultado.contains("Éxito")) {
            JOptionPane.showMessageDialog(null, 
                "✓ Estudiante agregado exitosamente\n" +
                "ID: " + id + "\n" +
                "Nombre: " + nombre,
                "Registro Exitoso",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resultado,
                "Error en el registro",
                JOptionPane.ERROR_MESSAGE);
        }
        
        return resultado;
    }

    @Override
    public String actualizarEstudiante(int id, String nombre, int edad) {
        String resultado = servicio.actualizarEstudiante(id, nombre, edad);
        
        if (resultado.contains("Éxito")) {
            JOptionPane.showMessageDialog(null,
                "✓ Estudiante actualizado exitosamente\n" +
                "ID: " + id + "\n" +
                "Nombre: " + nombre,
                "Actualización Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resultado,
                "Error en la actualización",
                JOptionPane.ERROR_MESSAGE);
        }
        
        return resultado;
    }

    @Override
    public String borrarEstudiante(int id) {
        String resultado = servicio.borrarEstudiante(id);
        
        if (resultado.contains("Éxito")) {
            JOptionPane.showMessageDialog(null,
                "✓ Estudiante eliminado exitosamente\n" +
                "ID: " + id,
                "Eliminación Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resultado,
                "Error en la eliminación",
                JOptionPane.ERROR_MESSAGE);
        }
        
        return resultado;
    }
}
