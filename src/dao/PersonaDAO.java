package dao;

import model.Persona;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Persona. Operaciones CRUD.
 */
public class PersonaDAO {

    /**
     * Inserta una nueva persona.
     * @param persona Objeto Persona a insertar.
     * @return true si éxito, false si error.
     */
    public boolean crearPersona(Persona persona) {
        String sql = "INSERT INTO Personas (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean exito = false;

        try {
            conn = DatabaseConnection.getConnection();
            // Importante: Deshabilitar auto-commit si vas a hacer varias operaciones
            // conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApellido());
            pstmt.setString(3, persona.getEmail());
            pstmt.setString(4, persona.getTelefono());

            int filasAfectadas = pstmt.executeUpdate();
            exito = (filasAfectadas > 0);

            // Si deshabilitaste auto-commit:
            // conn.commit();

        } catch (SQLException e) {
            System.err.println("Error al crear persona: " + e.getMessage());
            // Si usas transacciones:
            // try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            // try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } // Restaurar auto-commit
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            // No cerramos la conexión global aquí, se cierra al final en Main
            // DatabaseConnection.closeConnection();
        }
        return exito;
    }

    /**
     * Busca una persona por su ID.
     * @param id ID de la persona.
     * @return Objeto Persona o null si no se encuentra.
     */
    public Persona leerPersonaPorId(int id) {
        String sql = "SELECT id, nombre, apellido, email, telefono FROM Personas WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Persona persona = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                persona = mapResultSetToPersona(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al leer persona por ID: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return persona;
    }

    /**
     * Obtiene todas las personas.
     * @return Lista de objetos Persona.
     */
    public List<Persona> leerTodasLasPersonas() {
        String sql = "SELECT id, nombre, apellido, email, telefono FROM Personas ORDER BY id";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Persona> listaPersonas = new ArrayList<>();

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                listaPersonas.add(mapResultSetToPersona(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer todas las personas: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return listaPersonas;
    }

    /**
     * Actualiza los datos de una persona existente.
     * @param persona Objeto Persona con los datos actualizados (el ID debe existir).
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarPersona(Persona persona) {
        String sql = "UPDATE Personas SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean exito = false;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApellido());
            pstmt.setString(3, persona.getEmail());
            pstmt.setString(4, persona.getTelefono());
            pstmt.setInt(5, persona.getId()); // Condición WHERE

            int filasAfectadas = pstmt.executeUpdate();
            exito = (filasAfectadas > 0);

        } catch (SQLException e) {
            System.err.println("Error al actualizar persona: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return exito;
    }

    /**
     * Elimina una persona de la base de datos por su ID.
     * @param id El ID de la persona a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarPersona(int id) {
        String sql = "DELETE FROM Personas WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean exito = false;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            exito = (filasAfectadas > 0);

        } catch (SQLException e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return exito;
    }

    /**
     * Método helper para mapear un ResultSet a un objeto Persona.
     * @param rs ResultSet posicionado en una fila válida.
     * @return Objeto Persona creado desde el ResultSet.
     * @throws SQLException Si hay error al leer del ResultSet.
     */
    private Persona mapResultSetToPersona(ResultSet rs) throws SQLException {
        return new Persona(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("telefono")
        );
    }
}