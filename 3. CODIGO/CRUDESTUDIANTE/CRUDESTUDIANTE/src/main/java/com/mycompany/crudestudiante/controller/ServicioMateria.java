package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import com.mycompany.crudestudiante.model.Materia;

/**
 * Servicio para asignar materias a estudiantes
 * Usa el controlador central para sincronizar datos
 */
public class ServicioMateria {
    
    private ControlEstudiante controlador;

    public ServicioMateria() {
        this.controlador = new ControlEstudiante();
    }
    
    /**
     * Constructor que permite inyectar el controlador
     */
    public ServicioMateria(ControlEstudiante controlador) {
        this.controlador = controlador;
    }

    /**
     * Asignar una materia a un estudiante
     */
    public String asignarMateria(int idEstudiante, Materia materia) {
        Estudiante estudiante = controlador.obtenerTodos().stream()
                .filter(e -> e.getId() == idEstudiante)
                .findFirst()
                .orElse(null);
        
        if (estudiante == null) {
            return "Error: Estudiante no encontrado.";
        }
        
        if (materia == null) {
            return "Error: Materia inválida.";
        }
        
        // Verificar que sea mayor de 18 años
        if (estudiante.getEdad() < 18) {
            return "Error: Solo estudiantes mayores de 18 años pueden inscribirse.";
        }
        
        if (estudiante.getMaterias().contains(materia)) {
            return "Error: El estudiante ya está inscrito en esta materia.";
        }
        
        estudiante.agregarMateria(materia);
        return "✓ Éxito: Materia asignada correctamente.";
    }

    /**
     * Eliminar una materia de un estudiante
     */
    public String eliminarMateria(int idEstudiante, Materia materia) {
        Estudiante estudiante = controlador.obtenerTodos().stream()
                .filter(e -> e.getId() == idEstudiante)
                .findFirst()
                .orElse(null);
        
        if (estudiante == null) {
            return "Error: Estudiante no encontrado.";
        }
        
        if (!estudiante.getMaterias().contains(materia)) {
            return "Error: El estudiante no está inscrito en esta materia.";
        }
        
        estudiante.eliminarMateria(materia);
        return "✓ Éxito: Materia eliminada correctamente.";
    }
}
