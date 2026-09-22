package sv.edu.ues.occ.ingenieria.ppi115_2026.guias.control;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
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
            SELECT p
            FROM Pelicula p
            JOIN p.generos g
            WHERE g.nombre IN ('Ciencia Ficción', 'Suspense')
            GROUP BY p
            HAVING COUNT(DISTINCT g.nombre) = 2
            ORDER BY p.titulo ASC
            """, Pelicula.class)
            .getResultList();
    }

    public List<Pelicula> listarExcelentes() {
        return em.createQuery("""
            SELECT p
            FROM Pelicula p
            WHERE p.calificacion >= 8.5
            ORDER BY p.calificacion DESC
            """, Pelicula.class)
            .getResultList();
    }

    // AQUÍ deben permanecer también los métodos
    // de Fernando y Rodrigo.
}
