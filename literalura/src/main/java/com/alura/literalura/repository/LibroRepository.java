package com.alura.literalura.repository;

import com.alura.literalura.models.Idiomas;
import com.alura.literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    List<Libro> findByIdiomas(Idiomas idiomas);

    List<Libro> findTop10ByOrderByDescargasDesc();

    List<Libro> findByOrderByDescargasDesc();
}
