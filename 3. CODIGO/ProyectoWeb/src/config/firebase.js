// src/config/firebase.js
import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore"; // ◄── Cambiado a firestore

const firebaseConfig = {
  apiKey: "AIzaSyCgrf_3gQvrW1IqNClZmRaAEEq4UU00_Ec",
  authDomain: "transcontrol-web.firebaseapp.com",
  projectId: "transcontrol-web",
  storageBucket: "transcontrol-web.firebasestorage.app",
  messagingSenderId: "477949553059",
  appId: "1:477949553059:web:4ae7bed7900fdac57cd216",
  measurementId: "G-C3MSN34VDP"
};

// Inicializamos Firebase
const app = initializeApp(firebaseConfig);

// Exportamos la base de datos para el Adapter
export const db = getFirestore(app); // ◄── Exportación limpia para tu backend