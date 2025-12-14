package castellet.dam.m12.uf2.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import castellet.dam.m12.uf2.hibernate.HibernateUtil;
import jakarta.persistence.*;

@Entity
@Table(name = "deportes")
public class Deporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private int cod;

    @Column(name = "nombre")
    private String nombre;

    public Deporte() {
    }

    public Deporte(String nombre) {
        this.nombre = nombre;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public static List<Deporte> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Deporte> lista = session.createNativeQuery("SELECT * FROM list_deportes()", Deporte.class).list();

        session.close();
        return lista;
    }

    public static Deporte getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Deporte d = session.get(Deporte.class, id);

        session.close();
        return d;
    }
}
