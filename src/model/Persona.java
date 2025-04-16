// --- Paquete: com.tuorganizacion.model ---
package model;

/**
 * Clase POJO (Plain Old Java Object) que representa una Persona.
 */
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    // Constructor vacío
    private Persona() {
    }

    // Constructor para crear una nueva persona (sin ID)
    public Persona(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    // Constructor completo (al leer de la BD)
    public Persona(int id, String nombre, String apellido, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // --- Método toString ---
    @Override
    public String toString() {
        return "Persona{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", email='" + email + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }
}