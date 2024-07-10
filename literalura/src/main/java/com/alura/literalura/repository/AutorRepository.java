package com.alura.literalura.repository;

import com.alura.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :anio AND a.fallecimiento >= :anio")
    List<Autor> autoresVivosEnEseAnio(int anio);
    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombre%")
    List<Autor> autorPorNombre(String nombre);
}
