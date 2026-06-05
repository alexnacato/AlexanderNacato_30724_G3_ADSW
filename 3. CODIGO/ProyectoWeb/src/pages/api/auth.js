// src/pages/api/auth.js
import { loginController } from '../../controller/loginController.js'; // ◄── ¡Llama al controlador!

export const POST = async ({ request }) => {
    const datosDelFormulario = await request.formData();
    const cooreoInput = datosDelFormulario.get("correoEnviado"); 
    const passwordInput = datosDelFormulario.get("contrasenaEnviada");

    // El controlador hace todo el trabajo sucio
    const respuesta = await loginController.validarUsuario(cooreoInput, passwordInput);

    if (respuesta.encontrado === false) {
        return new Response(JSON.stringify({ error: "Usuario o contraseña incorrectos." }), { status: 401 });
    }
    
    return new Response(JSON.stringify({ 
        exito: true, 
        nombre: respuesta.datos.nombreUsuario, 
        rol: respuesta.datos.rol               
    }), { status: 200 });
};