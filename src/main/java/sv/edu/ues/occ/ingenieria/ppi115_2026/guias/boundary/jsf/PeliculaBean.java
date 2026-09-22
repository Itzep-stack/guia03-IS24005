package sv.edu.ues.occ.ingenieria.ppi115_2026.guias.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.ppi115_2026.guias.control.PeliculaRepository;
import sv.edu.ues.occ.ingenieria.ppi115_2026.guias.entity.Pelicula;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PeliculaBean implements Serializable {

    @EJB
    private PeliculaRepository peliculaRepository;

    private List<Pelicula> peliculas;

    @PostConstruct
    public void init() {
        listarTodasOrdenASC();
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void listarTodasOrdenASC() {
        this.peliculas = peliculaRepository.listarTodasOrdenASC();
    }

    public void listarCienciaFiccionYSuspense() {
        this.peliculas = peliculaRepository.listarCienciaFiccionYSuspense();
    }

    public void listarExcelentes() {
        this.peliculas = peliculaRepository.listarExcelentes();
    }

    public void listarPorChristopherNolan() {
        this.peliculas = peliculaRepository.listarPorChristopherNolan();
    }

    public void listarAnios2000a2010() {
        this.peliculas = peliculaRepository.listarAnios2000a2010();
    }

    public void listarAccionPost2010() {
        this.peliculas = peliculaRepository.listarAccionPost2010();
    }

    public void listarDramaMayorA8() {
        this.peliculas = peliculaRepository.listarDramaMayorA8();
    }
}
