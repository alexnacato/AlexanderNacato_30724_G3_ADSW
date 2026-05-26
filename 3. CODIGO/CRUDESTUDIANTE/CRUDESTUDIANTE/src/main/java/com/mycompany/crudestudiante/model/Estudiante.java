package com.mycompany.crudestudiante.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private int id;
    private String nombre;
    private int edad;
    private List<Materia> materias;

    public Estudiante(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.materias = new ArrayList<>();
    }

    public int getId()     { return id; }
    public String getNombre() { return nombre; }
    public int getEdad()      { return edad; }
    
    public List<Materia> getMaterias() { return materias; }
    
    public void agregarMateria(Materia materia) {
        if (!materias.contains(materia)) {
            materias.add(materia);
        }
    }
    
    public void eliminarMateria(Materia materia) {
        materias.remove(materia);
    }
    
    public boolean tieneMaterias() {
        return !materias.isEmpty();
    }
}