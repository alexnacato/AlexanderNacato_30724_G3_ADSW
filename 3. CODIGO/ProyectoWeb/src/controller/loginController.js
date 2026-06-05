// src/controller/loginController.js
import { FirebaseAdapter } from './Adapter/FirebaseAdapter.js';

export const loginController = {
    validarUsuario: async (email, contrasena) => {
        // Instanciamos el adaptador de Firebase
        const authAdapter = new FirebaseAdapter();
        
        // Le delegamos la validación a Firebase y retornamos lo que devuelva
        return await authAdapter.validarUsuario(email, contrasena);
    }
};