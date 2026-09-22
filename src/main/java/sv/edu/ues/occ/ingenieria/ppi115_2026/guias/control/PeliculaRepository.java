package sv.edu.ues.occ.ingenieria.ppi115_2026.guias.control;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import sv.edu.ues.occ.ingenieria.ppi115_2026.guias.entity.Pelicula;

@Stateless
public class PeliculaRepository implements Serializable {

    @PersistenceContext(unitName = "Peliculas-PU")
    private EntityManager em;

    public List<Pelicula> listarTodasOrdenASC() {
        return Collections.emptyList();
    }

    public List<Pelicula> listarCienciaFiccionYSuspense() {
        return Collections.emptyList();
    }

    public List<Pelicula> listarExcelentes() {
        return Collections.emptyList();
    }

    public List<Pelicula> listarPorChristopherNolan() {
        return em.createQuery(
                "SELECT p FROM Pelicula p JOIN FETCH p.director d LEFT JOIN FETCH p.generos "
                + "WHERE d.nombres = :nombres AND d.apellidos = :apellidos "
                + "ORDER BY p.fechaEstreno DESC",
                Pelicula.class)
                .setParameter("nombres", "Christopher")
                .setParameter("apellidos", "Nolan")
                .getResultList();
    }

    public List<Pelicula> listarAnios2000a2010() {
        return em.createQuery(
                "SELECT p FROM Pelicula p JOIN FETCH p.director LEFT JOIN FETCH p.generos "
                + "WHERE p.fechaEstreno BETWEEN :inicio AND :fin "
                + "ORDER BY p.fechaEstreno ASC",
                Pelicula.class)
                .setParameter("inicio", LocalDate.of(2000, 1, 1))
                .setParameter("fin", LocalDate.of(2010, 12, 31))
                .getResultList();
    }

    public List<Pelicula> listarAccionPost2010() {
        return Collections.emptyList();
    }

    public List<Pelicula> listarDramaMayorA8() {
        return Collections.emptyList();
    }
}
