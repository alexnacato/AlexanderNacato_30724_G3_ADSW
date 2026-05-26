package com.mycompany.crudestudiante.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que proporciona las materias disponibles en el sistema
 */
public class CatalogoMaterias {
    
    private static final String[] MATERIAS = {
        "Estadística",
        "Cálculo Diferencial e Integral",
        "Fundamentos de Programación",
        "Programación Orientada a Objetos",
        "Fundamentos de Software",
        "Análisis y Diseño de Software",
        "Ingeniería de Requisitos",
        "Aplicaciones Basadas en el Conocimiento",
        "Bases de Datos",
        "Programación Web"
    };

    /**
     * Obtener todas las materias disponibles
     */
    public static List<Materia> obtenerTodasLasMaterias() {
        List<Materia> materias = new ArrayList<>();
        for (String nombre : MATERIAS) {
            materias.add(new Materia(nombre));
        }
        return materias;
    }

    /**
     * Obtener una materia por nombre
     */
    public static Materia obtenerMateria(String nombre) {
        for (String materia : MATERIAS) {
            if (materia.equalsIgnoreCase(nombre)) {
                return new Materia(materia);
            }
        }
        return null;
    }
}
