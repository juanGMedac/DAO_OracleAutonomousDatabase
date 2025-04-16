package main;

import dao.PersonaDAO;
import model.Persona;
import util.DatabaseConnection;

import java.util.List;
import java.util.Scanner; // Para una interacción básica (opcional)

/**
 * Clase principal para demostrar las operaciones CRUD.
 */
public class Main {

    public static void main(String[] args) {
        PersonaDAO personaDAO = new PersonaDAO();

        System.out.println("--- Ejemplo CRUD Oracle DB (Sin Maven) ---");

        try {
            // --- CREATE ---
            System.out.println("\n[CREATE] Creando nueva persona...");
            Persona nuevaPersona = new Persona("Carlos", "Santana", "carlos.santana@example.com", "600555666");
            boolean creado = personaDAO.crearPersona(nuevaPersona);
            if (creado) {
                System.out.println("Persona creada exitosamente.");
            } else {
                System.out.println("Fallo al crear persona.");
            }

            // --- READ ALL ---
            System.out.println("\n[READ ALL] Leyendo todas las personas...");
            List<Persona> personas = personaDAO.leerTodasLasPersonas();
            if (personas.isEmpty()) {
                System.out.println("No hay personas en la base de datos.");
            } else {
                personas.forEach(System.out::println); // Imprime cada persona usando toString()
            }

            // Asumimos que existe al menos una persona para Update/Delete
            // Buscamos la última persona insertada (podría ser Carlos Santana si tuvo éxito)
            int idParaModificar = -1;
            personas = personaDAO.leerTodasLasPersonas(); // Recargar lista
            if (!personas.isEmpty()) {
                // Tomamos el ID de la última persona en la lista
                idParaModificar = personas.get(personas.size() - 1).getId();
                System.out.println("\nID seleccionado para Update/Delete: " + idParaModificar);


                // --- READ ONE ---
                System.out.println("\n[READ ONE] Leyendo persona con ID: " + idParaModificar);
                Persona personaLeida = personaDAO.leerPersonaPorId(idParaModificar);
                if (personaLeida != null) {
                    System.out.println("Persona encontrada: " + personaLeida);

                    // --- UPDATE ---
                    System.out.println("\n[UPDATE] Actualizando persona con ID: " + idParaModificar);
                    personaLeida.setNombre("Carlos Alberto");
                    personaLeida.setTelefono("600777888");
                    boolean actualizado = personaDAO.actualizarPersona(personaLeida);
                    if (actualizado) {
                        System.out.println("Persona actualizada exitosamente.");
                        // Volver a leer para verificar
                        Persona personaActualizada = personaDAO.leerPersonaPorId(idParaModificar);
                        System.out.println("Datos actualizados: " + personaActualizada);
                    } else {
                        System.out.println("Fallo al actualizar persona.");
                    }

                    // --- DELETE ---
                    System.out.println("\n[DELETE] Eliminando persona con ID: " + idParaModificar);
                    boolean eliminado = personaDAO.eliminarPersona(idParaModificar);
                    if (eliminado) {
                        System.out.println("Persona eliminada exitosamente.");
                    } else {
                        System.out.println("Fallo al eliminar persona.");
                    }

                    // --- READ ALL (Verificación final) ---
                    System.out.println("\n[READ ALL] Verificación después de eliminar...");
                    List<Persona> personasFinal = personaDAO.leerTodasLasPersonas();
                    if (personasFinal.isEmpty()) {
                        System.out.println("No hay personas en la base de datos.");
                    } else {
                        personasFinal.forEach(System.out::println);
                    }

                } else {
                    System.out.println("No se encontró la persona con ID: " + idParaModificar + " para actualizar/eliminar.");
                }
            } else {
                System.out.println("\nNo hay personas para seleccionar para Update/Delete.");
            }


        } finally {
            // --- CERRAR CONEXIÓN ---
            // Es importante cerrar la conexión al final de las operaciones
            DatabaseConnection.closeConnection();
        }

        System.out.println("\n--- Fin del ejemplo ---");
    }
}
