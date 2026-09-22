package sv.edu.ues.occ.ingenieria.ppi115_2026.guias.control;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.ppi115_2026.guias.entity.Pelicula;

import java.util.List;

@Stateless
public class PeliculaRepository {

    @PersistenceContext(unitName = "Peliculas-PU")
    private EntityManager em;

    public List<Pelicula> listarAccionPost2010() {
        return em.createQuery(
            "SELECT p FROM Pelicula p JOIN p.generos g WHERE g.nombre = 'Acción' AND YEAR(p.fechaEstreno) > 2010",
            Pelicula.class
        ).getResultList();
    }

    public List<Pelicula> listarDramaMayorA8() {
        return em.createQuery(
            "SELECT p FROM Pelicula p JOIN p.generos g WHERE g.nombre = 'Drama' AND p.calificacion > 8",
            Pelicula.class
        ).getResultList();
    }
}
