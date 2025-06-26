package com.oabm77.literalura.repository;

import com.oabm77.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAllByNombreContainsIgnoreCase(String nombreAutor);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= %:anio% AND a.anioFallecimiento >= %:anio%")
    List<Autor> autoresVivosPorAnio(int anio);
}
