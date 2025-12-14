package castellet.dam.m12.uf2.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import castellet.dam.m12.uf2.hibernate.*;
import jakarta.persistence.*;

@Entity
@Table(name = "deportistas")
public class Deportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private int cod;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "cod_deporte")
    private Deporte deporte;

    public Deportista() {
    }

    public Deportista(String nombre, Deporte deporte) {
        this.nombre = nombre;
        this.deporte = deporte;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public void save() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(this);

        tx.commit();
        session.close();
    }

    public void update() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(this);

        tx.commit();
        session.close();
    }

    public void delete() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.remove(this);

        tx.commit();
        session.close();
    }

    public static List<Deportista> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Deportista> lista = session.createQuery(
                "FROM Deportista d LEFT JOIN FETCH d.deporte",
                Deportista.class).list();

        session.close();
        return lista;
    }

    public static List<Deportista> getByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Deportista> lista = session.createQuery(
                "FROM Deportista d LEFT JOIN FETCH d.deporte " +
                        "WHERE LOWER(d.nombre) LIKE LOWER(:name)",
                Deportista.class)
                .setParameter("name", "%" + name + "%")
                .list();

        session.close();
        return lista;
    }

    /*
     * public static List<Deportista> getBySportId(int sportId) {
     * Session session = HibernateUtil.getSessionFactory().openSession();
     * 
     * List<Deportista> lista = session.createQuery(
     * "FROM Deportista d LEFT JOIN FETCH d.deporte dp " +
     * "WHERE dp.cod = :id",
     * Deportista.class)
     * .setParameter("id", sportId)
     * .list();
     * 
     * session.close();
     * return lista;
     * }
     */

    public static List<Deportista> getBySportId(int sportId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Deportista> lista = session.createNativeQuery(
                "SELECT * FROM list_deportistas_by_deporte(:id)",
                Deportista.class)
                .setParameter("id", sportId)
                .list();

        session.close();
        return lista;
    }
}
