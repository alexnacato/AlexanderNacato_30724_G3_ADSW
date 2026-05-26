package com.mycompany.crudestudiante.controller;

import com.mycompany.crudestudiante.model.Estudiante;
import com.mycompany.crudestudiante.model.Materia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * FACADE - Proporciona estadísticas sobre estudiantes y materias
 * Simplifica consultas complejas de datos
 */
public class FachadaEstadisticas {
    
    private ControlEstudiante controlador;

    public FachadaEstadisticas() {
        this.controlador = new ControlEstudiante();
    }
    
    /**
     * Constructor que permite inyectar el controlador
     */
    public FachadaEstadisticas(ControlEstudiante controlador) {
        this.controlador = controlador;
    }

    /**
     * Obtener todos los estudiantes
     */
    public List<Estudiante> obtenerTodos() {
        return controlador.obtenerTodos();
    }

    /**
     * Obtener estudiantes por edad específica
     */
    public List<Estudiante> obtenerEstudiantesPorEdad(int edad) {
        return controlador.obtenerTodos().stream()
                .filter(e -> e.getEdad() == edad)
                .collect(Collectors.toList());
    }

    /**
     * Obtener estudiantes en un rango de edad
     */
    public List<Estudiante> obtenerEstudiantesPorRangoEdad(int edadMin, int edadMax) {
        return controlador.obtenerTodos().stream()
                .filter(e -> e.getEdad() >= edadMin && e.getEdad() <= edadMax)
                .collect(Collectors.toList());
    }

    /**
     * Total de estudiantes
     */
    public int obtenerTotalEstudiantes() {
        return controlador.obtenerTodos().size();
    }

    /**
     * Edad promedio de los estudiantes
     */
    public double obtenerEdadPromedio() {
        List<Estudiante> estudiantes = controlador.obtenerTodos();
        if (estudiantes.isEmpty()) return 0;
        
        double suma = estudiantes.stream()
                .mapToInt(Estudiante::getEdad)
                .sum();
        return suma / estudiantes.size();
    }

    /**
     * Estudiante con mayor edad
     */
    public Estudiante obtenerEstudianteMayorEdad() {
        return controlador.obtenerTodos().stream()
                .max((e1, e2) -> Integer.compare(e1.getEdad(), e2.getEdad()))
                .orElse(null);
    }

    /**
     * Estudiante con menor edad
     */
    public Estudiante obtenerEstudianteMenorEdad() {
        return controlador.obtenerTodos().stream()
                .min((e1, e2) -> Integer.compare(e1.getEdad(), e2.getEdad()))
                .orElse(null);
    }

    /**
     * Estudiantes que tienen materias asignadas
     */
    public List<Estudiante> obtenerEstudiantesConMaterias() {
        return controlador.obtenerTodos().stream()
                .filter(Estudiante::tieneMaterias)
                .collect(Collectors.toList());
    }

    /**
     * Estudiantes SIN materias asignadas
     */
    public List<Estudiante> obtenerEstudiantesSinMaterias() {
        return controlador.obtenerTodos().stream()
                .filter(e -> !e.tieneMaterias())
                .collect(Collectors.toList());
    }

    /**
     * Materia más popular (más estudiantes inscritos)
     */
    public Materia obtenerMateriaMasPopular() {
        Map<Materia, Integer> conteo = new HashMap<>();
        
        for (Estudiante e : controlador.obtenerTodos()) {
            for (Materia m : e.getMaterias()) {
                conteo.put(m, conteo.getOrDefault(m, 0) + 1);
            }
        }
        
        return conteo.keySet().stream()
                .max((m1, m2) -> Integer.compare(conteo.get(m1), conteo.get(m2)))
                .orElse(null);
    }

    /**
     * Obtener estadísticas de cada materia
     */
    public Map<String, Integer> obtenerEstadisticasPorMateria() {
        Map<String, Integer> estadisticas = new HashMap<>();
        
        for (Estudiante e : controlador.obtenerTodos()) {
            for (Materia m : e.getMaterias()) {
                estadisticas.put(m.getNombre(), 
                    estadisticas.getOrDefault(m.getNombre(), 0) + 1);
            }
        }
        
        return estadisticas;
    }

    /**
     * Obtener distribucion de estudiantes por edad
     */
    public Map<Integer, Integer> obtenerDistribucionPorEdad() {
        Map<Integer, Integer> distribucion = new HashMap<>();
        
        for (Estudiante e : controlador.obtenerTodos()) {
            int edad = e.getEdad();
            distribucion.put(edad, distribucion.getOrDefault(edad, 0) + 1);
        }
        
        return distribucion;
    }

    /**
     * Obtener reporte general en texto - MEJORADO
     */
    public String obtenerReporteGeneral() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========== REPORTE GENERAL DEL SISTEMA ==========\n\n");
        
        // Información general
        reporte.append("📊 INFORMACIÓN GENERAL:\n");
        reporte.append("  • Total de Estudiantes: ").append(obtenerTotalEstudiantes()).append("\n");
        reporte.append("  • Edad Promedio: ").append(String.format("%.2f", obtenerEdadPromedio())).append(" años\n");
        
        Estudiante mayor = obtenerEstudianteMayorEdad();
        if (mayor != null) {
            reporte.append("  • Mayor: ").append(mayor.getNombre())
                    .append(" (").append(mayor.getEdad()).append(" años)\n");
        }
        
        Estudiante menor = obtenerEstudianteMenorEdad();
        if (menor != null) {
            reporte.append("  • Menor: ").append(menor.getNombre())
                    .append(" (").append(menor.getEdad()).append(" años)\n");
        }
        
        reporte.append("\n📚 INSCRIPCIONES A MATERIAS:\n");
        reporte.append("  • Con Materias: ").append(obtenerEstudiantesConMaterias().size()).append("\n");
        reporte.append("  • Sin Materias: ").append(obtenerEstudiantesSinMaterias().size()).append("\n");
        
        // Estadísticas por materia - ORDENADAS por cantidad
        Map<String, Integer> estadisticasMaterias = obtenerEstadisticasPorMateria();
        
        if (estadisticasMaterias.isEmpty()) {
            reporte.append("\n❌ No hay materias asignadas aún.\n");
        } else {
            reporte.append("\n📖 MATERIAS POR CANTIDAD DE ESTUDIANTES:\n");
            reporte.append("  ").append("-".repeat(50)).append("\n");
            
            // Ordenar por cantidad de estudiantes (descendente)
            estadisticasMaterias.entrySet().stream()
                    .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                    .forEach(entry -> {
                        reporte.append(String.format("  %-40s | %2d estudiantes\n", 
                            entry.getKey(), entry.getValue()));
                    });
            
            reporte.append("  ").append("-".repeat(50)).append("\n");
            
            Materia popular = obtenerMateriaMasPopular();
            if (popular != null) {
                int cantidad = estadisticasMaterias.get(popular.getNombre());
                reporte.append("\n  ⭐ MÁS POPULAR: ").append(popular.getNombre())
                        .append(" (").append(cantidad).append(" estudiantes)\n");
            }
        }
        
        reporte.append("\n================================================\n");
        
        return reporte.toString();
    }
}
