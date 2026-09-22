package sv.edu.ues.occ.ingenieria.ppi115_2026.guias.control;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import sv.edu.ues.occ.ingenieria.ppi115_2026.guias.entity.Pelicula;

@Stateless
public class PeliculaRepository implements Serializable {

    @PersistenceContext(unitName = "Peliculas-PU")
    private EntityManager em;

    public List<Pelicula> listarTodasOrdenASC() {
        return em.createQuery("""
            SELECT DISTINCT p
            FROM Pelicula p
            JOIN FETCH p.director
            LEFT JOIN FETCH p.generos
            ORDER BY p.titulo ASC
            """, Pelicula.class)
            .getResultList();
    }

    public List<Pelicula> listarCienciaFiccionYSuspense() {
        return em.createQuery("""
            SELECT DISTINCT p
            FROM Pelicula p
            JOIN FETCH p.director
            LEFT JOIN FETCH p.generos
            WHERE EXISTS (SELECT g1 FROM Pelicula p1 JOIN p1.generos g1
                          WHERE p1 = p AND g1.nombre = 'Ciencia Ficción')
              AND EXISTS (SELECT g2 FROM Pelicula p2 JOIN p2.generos g2
                          WHERE p2 = p AND g2.nombre = 'Suspense')
            ORDER BY p.titulo ASC
            """, Pelicula.class)
            .getResultList();
    }

    public List<Pelicula> listarExcelentes() {
        return em.createQuery("""
            SELECT DISTINCT p
            FROM Pelicula p
            JOIN FETCH p.director
            LEFT JOIN FETCH p.generos
            WHERE p.calificacion >= 8.5
            ORDER BY p.calificacion DESC
            """, Pelicula.class)
            .getResultList();
    }

    public List<Pelicula> listarPorChristopherNolan() {
        return em.createQuery(
                "SELECT DISTINCT p FROM Pelicula p JOIN FETCH p.director d LEFT JOIN FETCH p.generos "
                + "WHERE d.nombres = :nombres AND d.apellidos = :apellidos "
                + "ORDER BY p.fechaEstreno DESC",
                Pelicula.class)
                .setParameter("nombres", "Christopher")
                .setParameter("apellidos", "Nolan")
                .getResultList();
    }

    public List<Pelicula> listarAnios2000a2010() {
        return em.createQuery(
                "SELECT DISTINCT p FROM Pelicula p JOIN FETCH p.director LEFT JOIN FETCH p.generos "
                + "WHERE p.fechaEstreno BETWEEN :inicio AND :fin "
                + "ORDER BY p.fechaEstreno ASC",
                Pelicula.class)
                .setParameter("inicio", LocalDate.of(2000, 1, 1))
                .setParameter("fin", LocalDate.of(2010, 12, 31))
                .getResultList();
    }

    public List<Pelicula> listarAccionPost2010() {
        return em.createQuery(
                "SELECT DISTINCT p FROM Pelicula p JOIN FETCH p.director LEFT JOIN FETCH p.generos "
                + "WHERE p.fechaEstreno > :fin2010 AND EXISTS "
                + "(SELECT g FROM Pelicula p2 JOIN p2.generos g WHERE p2 = p AND g.nombre = 'Acción')",
                Pelicula.class)
                .setParameter("fin2010", LocalDate.of(2010, 12, 31))
                .getResultList();
    }

    public List<Pelicula> listarDramaMayorA8() {
        return em.createQuery(
                "SELECT DISTINCT p FROM Pelicula p JOIN FETCH p.director LEFT JOIN FETCH p.generos "
                + "WHERE p.calificacion > 8.0 AND EXISTS "
                + "(SELECT g FROM Pelicula p2 JOIN p2.generos g WHERE p2 = p AND g.nombre = 'Drama')",
                Pelicula.class)
                .getResultList();
    }
}
