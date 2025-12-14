package dam.m6.uf2.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO deportes (nombre) VALUES (?)")) {
            ps.setString(1, item.getNombre());
            ps.executeUpdate();
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