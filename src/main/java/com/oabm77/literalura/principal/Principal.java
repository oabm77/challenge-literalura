package com.oabm77.literalura.principal;

import com.oabm77.literalura.model.*;
import com.oabm77.literalura.repository.AutorRepository;
import com.oabm77.literalura.repository.LibroRepository;
import com.oabm77.literalura.service.ConsumoAPI;
import com.oabm77.literalura.service.ConvierteDatos;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n***** LiterAlura *****
                    1 - Consultar libros
                    2 - Consultar autores
                    3 - Listando libros por idiomas
                    4 - Listando autores vivos en determinado año
                    5 - Top 10 libros más descargados
                    6 - Estadísticas de descargas
                    0 - Salir 
                    """;
            System.out.println(menu);
            try {
                System.out.print("Ingrese la opción: ");
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;
            }
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarAutor();
                    break;
                case 3:
                    buscarLibrosPorIdioma();
                    break;
                case 4:
                    buscarAutoresVivosPorAnio();
                    break;
                case 5:
                    mostrarTop10LibrosDescargados();
                    break;
                case 6:
                    mostrarEstadisticasDeLibros();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
            System.out.println();
        }
    }

    private boolean getDatosLibro(String nombreLibro) {
        boolean encuentraLibros = false;
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
        DatosResultado datos = conversor.obtenerDatos(json, DatosResultado.class);

        for (DatosLibro datosLibro: datos.libros()) {
            encuentraLibros = true;

            List<DatosAutor> datosAutores = datosLibro.autores();
            DatosAutor autorLibro = new DatosAutor("Desconocido", null, null);
            if (!datosAutores.isEmpty()) {
                autorLibro = datosAutores.getFirst();
            }

            autores = autorRepository.findAll();

            DatosAutor finalAutorLibro = autorLibro;
            Optional<Autor> autor = autores.stream()
                    .filter(a -> a.getNombre().contains(finalAutorLibro.nombre()))
                    .findFirst();

            Autor autorEncontrado = null;
            if (autor.isPresent()) {
                autorEncontrado = autor.get();
            } else {
                autorEncontrado = new Autor(autorLibro);
                autorRepository.save(autorEncontrado);
            }

            Libro libro = new Libro(datosLibro);
            libro.setAutor(autorEncontrado);

            Optional<Libro> libroEncontrado = libroRepository.findByTituloAndAutor(libro.getTitulo(), autorEncontrado);
            if (libroEncontrado.isPresent()) {
                System.out.println("Libro: " + libro.getTitulo() + " ya almacenado.");
            } else {
                libroRepository.save(libro);
            }
        }

        return encuentraLibros;
    }

    private void buscarLibro() {
        System.out.print("Escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = teclado.nextLine();

        libros = libroRepository.findAllByTituloContainsIgnoreCase(nombreLibro);

        if (libros.isEmpty()) {
            boolean encuentraLibros = getDatosLibro(nombreLibro);
            if (encuentraLibros) {
                System.out.println("\nLibros encontrados: ");
                libros = libroRepository.findAllByTituloContainsIgnoreCase(nombreLibro);
            } else {
                System.out.println("Libro no encontrado");
            }
        } else {
            System.out.println("\nLibros encontrados: ");
        }

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);

        System.out.println();
    }

    private void buscarAutor() {
        System.out.print("Escribe el nombre del autor que deseas buscar: ");
        var nombreAutor = teclado.nextLine();

        autores = autorRepository.findAllByNombreContainsIgnoreCase(nombreAutor);

        var respuesta = "n";
        do {
            if (autores.isEmpty()) {
                boolean encuentraLibros = getDatosLibro(nombreAutor);
                if (encuentraLibros) {
                    autores = autorRepository.findAllByNombreContainsIgnoreCase(nombreAutor);
                    if (autores.isEmpty()) {
                        System.out.println("Autor no encontrado");
                    } else {
                        System.out.println("\nAutores encontrados: ");
                    }
                } else {
                    System.out.println("Autor no encontrado");
                }
            } else {
                System.out.println("\nAutores encontrados: ");
            }

            autores.stream()
                    .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(System.out::println);

            System.out.print("¿Desea consultar si existen otros libros del autor? S/N: ");
            respuesta = teclado.nextLine().toLowerCase();
            autores.clear();
        } while (respuesta.equals("s"));

        System.out.println();
    }

    private void buscarLibrosPorIdioma() {
        List<String> idiomas = libroRepository.idiomasDisponibles();
        if (idiomas.isEmpty()) {
            System.out.println("Atención: Debe cargar libros antes para poder consultar idiomas");
        } else {
            System.out.println("Idiomas disponibles: ");
            idiomas.forEach(System.out::println);
            System.out.print("Escribe el idioma del cual quiere consultar los libros: ");
            var idioma = teclado.nextLine();
            List<Libro> librosPorIdioma = libroRepository.librosPorIdioma(idioma);
            if (librosPorIdioma.isEmpty()) {
                System.out.println("No se encuentran libros en idioma: " + idioma);
            } else {
                librosPorIdioma.forEach(System.out::println);
                System.out.printf("Total de libros en idioma '%s': %d", idioma, librosPorIdioma.stream().count());
            }
        }
        System.out.println();
    }

    private void buscarAutoresVivosPorAnio() {
        System.out.print("Escribe el año en el que vivió el autor: ");
        Integer anio = null;
        try {
            anio = teclado.nextInt();
            List<Autor> autoresPorAnio = autorRepository.autoresVivosPorAnio(anio);
            if (autoresPorAnio.isEmpty()) {
                System.out.println("No se encontraron autores vivos durante ese año.");
            } else {
                autoresPorAnio.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Año inválido");
        }
        teclado.nextLine();
        System.out.println();
    }

    private void mostrarTop10LibrosDescargados() {
        libros = libroRepository.top10LibrosDescargados();
        AtomicInteger counter = new AtomicInteger();
        libros.forEach(l -> System.out.println(counter.incrementAndGet() + "- Título: " + l.getTitulo()
                + ", idioma: " + l.getIdioma() + ", N° descargas: " + l.getNumeroDescargas()
                + ", Autor: " + l.getAutor()));
    }

    private void mostrarEstadisticasDeLibros() {
        libros = libroRepository.findAll();
        DoubleSummaryStatistics estadisticas = libros.stream()
                .mapToDouble(Libro::getNumeroDescargas)
                .summaryStatistics();

        Libro maxLibro = libros.stream()
                .max(Comparator.comparingInt(Libro::getNumeroDescargas))
                .orElse(null);

        Libro minLibro = libros.stream()
                .min(Comparator.comparingInt(Libro::getNumeroDescargas))
                .orElse(null);

        System.out.println("Total de libros almacenados en Base de Datos: " + estadisticas.getCount());
        System.out.println("Máximo de descargas: " + estadisticas.getMax() + " Título: " + maxLibro.getTitulo() + "  [Autor: " + maxLibro.getAutor().getNombre() + "]");
        System.out.println("Mínimo de descargas: " + estadisticas.getMin() + " Título: " + minLibro.getTitulo() + "  [Autor: " + minLibro.getAutor().getNombre() + "]");

    }

}
