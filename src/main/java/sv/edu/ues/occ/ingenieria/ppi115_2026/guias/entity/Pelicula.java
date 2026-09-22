package sv.edu.ues.occ.ingenieria.ppi115_2026.guias.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Integer idPelicula;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "fecha_estreno")
    private LocalDate fechaEstreno;

    @Column(name = "calificacion", precision = 3, scale = 1)
    private BigDecimal calificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_director", nullable = false)
    private Director director;

    @ManyToMany
    @JoinTable(
        name = "pelicula_genero",
        joinColumns = @JoinColumn(name = "id_pelicula"),
        inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private List<Genero> generos = new ArrayList<>();

    public Pelicula() {
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
