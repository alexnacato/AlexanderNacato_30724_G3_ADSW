#TransControl - Módulo de Autenticación (RF01)

Este módulo gestiona el control de acceso seguro para el sistema **TransControl** utilizando **Astro** para el Frontend/BfE y **Firebase Firestore** como base de datos en la nube. El backend implementa un **Patrón de Diseño Adaptador (Adapter Pattern)** para desacoplar la lógica del negocio del proveedor de la base de datos.

---

## 🛠️ Requisitos Previos

Antes de hacer arrancar el proyecto, asegúrate de tener instalado en tu sistema (Fedora/Ubuntu/Windows):
* **Node.js** (Versión 18 o superior recomendada)
* **npm** (Viene incluido con Node.js)

---

## Instalación y Arranque Local

Seguir estos pasos en tu terminal para poner a correr el proyecto en tu máquina:

1. **Entra a la carpeta del proyecto:**
Es decir entrar a la carpeta de Proyecto Web desde su consola
2. **Instala las dependencias necesarias:
(Esto descargará Astro, el SDK de Firebase y demás herramientas configuradas en el package.json)
  1.1 . npm install
  1.2 npm run dev
3. **En consola ha de aparecer el localhost el cual ya les redirige automaticamente a la pagina web**
** Los correos y contraseñas son:**
   1. secretaria@transcontrol.com - 123456
    2.  transportista@transcontrol.com - 123456
    3.  administrador@transcontrol.com - 123456
    4.  presidente@transcontrol.com - 123456
Estos se encuentran totalmente validados
