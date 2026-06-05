# Guía de Uso de Firebase para el Equipo

## 1. Importar la Base de Datos

Nunca inicialicen Firebase de nuevo. Usen la instancia compartida que ya está configurada en `src/config/firebase.js`:

```javascript
import { db } from '../config/firebase.js'; // Ajusta la ruta relativa según tu archivo
import { collection, getDocs, doc, getDoc } from 'firebase/firestore';
```

---

## 2. Cómo Leer Datos (Ejemplo de consulta)

Si necesitan traer todos los documentos de una colección (por ejemplo, usuarios), usen la estructura asíncrona estándar:

```javascript
try {
    const querySnapshot = await getDocs(collection(db, "usuarios"));

    querySnapshot.forEach((documento) => {
        console.log(`ID: ${documento.id} => Datos:`, documento.data());
    });
} catch (error) {
    console.error("Error al consultar Firestore:", error);
}
```

---

## 3. Buenas Prácticas del Repositorio

- No alteren las credenciales directas de Firebase sin avisar al grupo.
- Las dependencias locales y las carpetas de caché (`node_modules`, `.astro`) están correctamente configuradas en el `.gitignore` raíz, por lo que los comandos de Git se mantendrán limpios.
