package com.mycompany.crudestudiante.repository;

import com.mycompany.crudestudiante.model.Estudiante;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEstudiante {

    private List<Estudiante> baseDatos = new ArrayList<>();

    public boolean existeId(int id) {
        return baseDatos.stream().anyMatch(e -> e.getId() == id);
    }

    public void guardar(Estudiante estudiante) {
        baseDatos.add(estudiante);
    }

    public Estudiante buscarPorId(int id) {
        return baseDatos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void actualizar(Estudiante estudiante) {
        for (int i = 0; i < baseDatos.size(); i++) {
            if (baseDatos.get(i).getId() == estudiante.getId()) {
                baseDatos.set(i, estudiante);
                break;
            }
        }
    }

    public void eliminar(int id) {
        baseDatos.removeIf(e -> e.getId() == id);
    }

    public List<Estudiante> listarTodos() {
        return new ArrayList<>(baseDatos);
    }
}