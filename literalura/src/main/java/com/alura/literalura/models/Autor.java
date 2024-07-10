package com.alura.literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private int nacimiento;
    private int fallecimiento;
    @ManyToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libro = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.nacimiento = datosAutor.nacimiento();
        this.fallecimiento = datosAutor.fallecimiento();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(int fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        libro.forEach(l -> l.getAutor().add(this));
        this.libro = libro;
    }

    @Override
    public String toString() {
        String nombresLibros = libro.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));
        return "-------------Autor-------------" +
                "\nAutor: " + nombre +
                "\nAño de Nacimiento: " + nacimiento +
                "\nAño de Fallecimiento: " + fallecimiento +
                "\nLibros: [" + nombresLibros + "]" +
                "\n-------------------------------\n";
    }
}
