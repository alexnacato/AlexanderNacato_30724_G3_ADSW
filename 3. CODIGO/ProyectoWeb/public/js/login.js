document.addEventListener('DOMContentLoaded', () => {
    console.log("¡Validación y Redirección de Frontend activa!");

    const formulario = document.querySelector('.formulario');

    if (!formulario) {
        console.error("No se encontró el formulario para validar.");
        return;
    }

    formulario.addEventListener('submit', async (evento) => {
        // 1. 🛑 ¡FRENO DE MANO! Evitamos que el navegador se mude físicamente a la API
        evento.preventDefault(); 

        const inputCorreo = formulario.querySelector('input[name="correoEnviado"]');
        const inputContrasena = formulario.querySelector('input[name="contrasenaEnviada"]');
       
        const correo = inputCorreo.value.trim();
        const contrasena = inputContrasena.value.trim();

        // Validaciones básicas
        if (correo === "" || contrasena === "") {
            alert("Por favor, llena todos los campos antes de continuar.");
            return;
        }

        if (contrasena.length < 6) {
            alert("La contraseña es demasiado corta. Debe tener al menos 6 caracteres.");
            return;
        }

        console.log("Campos válidos en cliente. Conectando con la API y Firebase...");

        try {
            // 2. Enviamos los datos a la API "por debajo" en secreto
            const datosFormulario = new FormData(formulario);
            const respuestaServidor = await fetch('/api/auth', {
                method: 'POST',
                body: datosFormulario
            });

            // 3. Capturamos la respuesta JSON que ya vimos que Firebase genera bien
            const datosJSON = await respuestaServidor.json();

            if (respuestaServidor.ok && datosJSON.exito) {
                console.log("Login exitoso. Rol detectado:", datosJSON.rol);
                
                // 4. El enrutador del Frontend toma el control y te mueve de ventana
                const rol = datosJSON.rol;
                window.location.href = `/${rol}`; 
                
            } else {
                // Si las credenciales no coinciden en Firebase (Error 401)
                alert(datosJSON.error || "Usuario o contraseña incorrectos.");
            }

        } catch (error) {
            console.error("Error en la petición de autenticación:", error);
            alert("Hubo un problema de red al intentar conectar con el servidor.");
        }
    });
});