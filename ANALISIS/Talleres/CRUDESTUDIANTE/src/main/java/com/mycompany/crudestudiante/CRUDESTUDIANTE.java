package com.mycompany.crudestudiante;

import com.mycompany.crudestudiante.view.FrmEstudiante;
import javax.swing.JFrame;

/**
 * @author Alexander
 */
public class CRUDESTUDIANTE {

    public static void main(String[] args) {
        // 1. Creamos la ventana principal (el marco)
        JFrame ventana = new JFrame("Sistema de Gestión de Estudiantes");
        
        // 2. Importante: Que el programa se cierre al cerrar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 3. Agregamos tu panel al centro de la ventana
        ventana.add(new FrmEstudiante());
        
        // 4. Ajustamos el tamaño de la ventana al contenido del panel
        ventana.pack();
        
        // 5. Centramos la ventana en la pantalla
        ventana.setLocationRelativeTo(null);
        
        // 6. ¡La hacemos visible!
        ventana.setVisible(true);
    }
}