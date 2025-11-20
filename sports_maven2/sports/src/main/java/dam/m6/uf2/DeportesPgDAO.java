package dam.m6.uf2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dam.m6.uf2.model.Deporte;

public class DeportesPgDAO implements DAO<Deporte> {

    private Connection conn;

    public DeportesPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Deporte item) {
        if (conn == null)
            return;

        try (Statement st = conn.createStatement()) {
            st.executeUpdate(
                    "INSERT INTO deportes (nombre) VALUES ('" + item.getNombre() + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Deporte> getAll() {
        List<Deporte> lista = new ArrayList<>();
        if (conn == null)
            return lista;

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM list_deportes();");
            while (rs.next()) {
                lista.add(new Deporte(
                        rs.getInt("cod"),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}