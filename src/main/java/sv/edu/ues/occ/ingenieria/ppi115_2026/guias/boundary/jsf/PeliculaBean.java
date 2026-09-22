package sv.edu.ues.occ.ingenieria.ppi115_2026.guias.boundary.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.facelets.FaceletContext;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.ppi115_2026.guias.control.PeliculaRepository;
import sv.edu.ues.occ.ingenieria.ppi115_2026.guias.entity.Pelicula;

import java.io.Serializable;
import java.util.List;

@Named
@jakarta.faces.view.facelets.FaceletsPowered // o @ViewScoped si usas Jakarta Faces 4.x
public class PeliculaBean implements Serializable {

    @EJB
    private PeliculaRepository peliculaRepository;

    private List<Pelicula> peliculas;

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void listarAccionPost2010() {
        this.peliculas = peliculaRepository.listarAccionPost2010();
    }

    public void listarDramaMayorA8() {
        this.peliculas = peliculaRepository.listarDramaMayorA8();
    }

    public void listarTodas() {
        this.peliculas = peliculaRepository.listarTodas();
    }
}
