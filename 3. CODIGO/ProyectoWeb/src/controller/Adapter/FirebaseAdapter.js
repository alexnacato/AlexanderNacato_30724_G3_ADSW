import { db } from "../../config/firebase.js";
import { collection, query, where, getDocs } from "firebase/firestore";

export class FirebaseAdapter {
    
    async validarUsuario(correoEnviado, contrasenaEnviada) {
        try {
            //Busco el nombre de mi collection de firestore
            const usuariosRef = collection(db, "usuarios");
            
            //Sentencia para buscar el correo que se trajo como argumento
            const q = query(usuariosRef, where("correo", "==", correoEnviado));
            
            // ejecutar la consulta dentro de firestore
            const querySnapshot = await getDocs(q);
            
            // Si la consulta no existe el valor de esa consulta es null
            if (querySnapshot.empty) {
                return { encontrado: false, datos: null };
            }

            // Obtener los datos del primer usuario encontrado
            const docUsuario = querySnapshot.docs[0];
            const datosUsuario = docUsuario.data();

            // Validar la contraseña de igual forma para ver si coincide con el form
            if (datosUsuario.contrasena === contrasenaEnviada) {
                
                // Tomamos lo NOSQL de firestore y lo transformamos a JSON dentro de nuestra web
                return {
                    encontrado: true,
                    datos: {
                        nombreUsuario: datosUsuario.nombreUsuario,
                        rol: datosUsuario.rol
                    }
                };
            } else {

                return { encontrado: false, datos: null };
            }

        } catch (error) {
            console.error("Error en el adaptador de Firebase:", error);
            return { encontrado: false, datos: null };
        }
    }
}