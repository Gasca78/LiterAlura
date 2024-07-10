package com.alura.literalura.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private int descargas;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        try {
            this.titulo = datosLibro.titulo();
            this.idiomas = Idiomas.fromString(datosLibro.idiomas().get(0));
            this.descargas = datosLibro.descargas();
        } catch (IllegalArgumentException e) {
            System.out.println("Libro no disponible para los idiomas inglés, español, francés o portugués");
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    // Configuración para una correcta bidireccionalidad entre Libros y Autores
    public void setAutor(List<Autor> autor) {
        autor.forEach(a -> a.getLibro().add(this)); // Se agrega el libro a los autores
        this.autor = autor;
    }

    @Override
    public String toString() {
        String nombresAutores = autor.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));
        return "-------------Libro-------------\n" +
                "Titulo: " + titulo +
                "\nAutor: " + nombresAutores +
                "\nIdioma: " + idiomas +
                "\nNúmero de descargas: " + descargas +
                "\n-------------------------------\n";
    }
}
