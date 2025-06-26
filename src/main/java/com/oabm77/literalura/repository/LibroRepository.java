package com.oabm77.literalura.repository;

import com.oabm77.literalura.model.Autor;
import com.oabm77.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findAllByTituloContainsIgnoreCase(String tituloLibro);

    Optional<Libro> findByTituloAndAutor(String titulo, Autor autor);

    @Query("SELECT DISTINCT l.idioma FROM Libro l")
    List<String> idiomasDisponibles();

    @Query("SELECT l FROM Libro l WHERE l.idioma ILIKE %:idioma%")
    List<Libro> librosPorIdioma(String idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.numeroDescargas DESC LIMIT 10")
    List<Libro> top10LibrosDescargados();
}
