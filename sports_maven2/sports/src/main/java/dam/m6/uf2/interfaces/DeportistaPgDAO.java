package dam.m6.uf2.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dam.m6.uf2.model.Deportista;

public class DeportistaPgDAO implements DAO<Deportista> {
    private Connection conn;

    public DeportistaPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Deportista item) {
        if (conn == null)
            return;

        try (PreparedStatement ps = conn
                .prepareStatement("INSERT INTO deportistas (nombre, cod_deporte) VALUES (?, ?)")) {
            ps.setString(1, item.getNombre());
            ps.setInt(2, item.getCodDeporte());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Deportista> getAll() {
        List<Deportista> lista = new ArrayList<>();
        if (conn == null)
            return lista;

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(
                    "SELECT d.cod AS code, d.nombre AS deportista, d.cod_deporte AS coddp, dp.nombre AS deporte FROM deportistas d LEFT JOIN deportes dp ON d.cod_deporte = dp.cod ORDER BY d.cod;");
            while (rs.next()) {
                lista.add(new Deportista(
                        rs.getInt("code"),
                        rs.getString("deportista"),
                        rs.getObject("coddp") != null ? rs.getInt("coddp") : null,
                        rs.getString("deporte") != null ? rs.getString("deporte") : null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Deportista> getDeportistaByName(String name) {
        List<Deportista> lista = new ArrayList<>();
        if (conn == null)
            return lista;

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(
                    "SELECT d.cod AS cod, d.nombre AS nombre, d.cod_deporte AS cod_deporte, dp.nombre AS deporte FROM deportistas d LEFT JOIN deportes dp ON d.cod_deporte = dp.cod WHERE LOWER(d.nombre) LIKE LOWER('%"
                            + name + "%')");

            while (rs.next()) {
                lista.add(new Deportista(
                        rs.getInt("cod"),
                        rs.getString("nombre"),
                        rs.getObject("cod_deporte") != null ? rs.getInt("cod_deporte") : null,
                        rs.getString("deporte") != null ? rs.getString("deporte") : null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Deportista> getDeportistaBySportID(int sportID) {
        List<Deportista> lista = new ArrayList<>();
        if (conn == null)
            return lista;

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM list_deportistas_by_deporte(" + sportID + ");");

            while (rs.next()) {
                lista.add(new Deportista(
                        rs.getInt("deportista_cod"),
                        rs.getString("deportista_nombre"),
                        sportID,
                        rs.getString("deporte_nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
