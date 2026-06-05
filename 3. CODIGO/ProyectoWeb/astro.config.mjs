import { defineConfig } from 'astro/config';
import node from '@astrojs/node'; // 1. Importamos el adaptador de Node

// https://astro.build/config
export default defineConfig({
  // 2. ¡La clave mágica! Le decimos a Astro que trabaje bajo demanda en el servidor
  output: 'server', 
  
  // 3. Le decimos qué motor va a usar el servidor para procesar el backend
  adapter: node({
    mode: 'standalone',
  }),
});