package com.alura.literalura.principal;

import com.alura.literalura.models.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepositorio = libroRepository;
        this.autorRepositorio = autorRepository;
    }

    public void menu() {
        System.out.println("Escribe tu nombre, por favor: ");
        String name = scanner.nextLine();
        System.out.println("¡Bienvenido %s a tu biblioteca virtual!".formatted(name));
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    Selecciona una opción del menú:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un año determinado
                    5 - Listar libros por idioma
                    6 - Mostrar el top 10 de Libros basado en cantidad de descargas
                    7 - Estadísticas de descargas de los libros
                    8 - Buscar autor por nombre

                    0 - Salir""";
            System.out.println(menu);
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    buscarLibro(getDatos());
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    autoresVivosAnio();
                    break;
                case 5:
                    buscarLibrosPorIdioma();
                    break;
                case 6:
                    mostrarTop10();
                    break;
                case 7:
                    estadisticasDescargas();
                    break;
                case 8:
                    buscarAutorPorNombre();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Datos getDatos() {
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        String texto = scanner.nextLine();
        String nombreLibro = URLEncoder.encode(texto, StandardCharsets.UTF_8);
        var json = consumoAPI.obtenerAPI(URL_BASE+nombreLibro);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        return datos;
    }

    private void buscarLibro(Datos datos) {
        List<DatosLibro> datosLibros = datos.libro();
        if (datosLibros.isEmpty()) {
            System.out.println("No se encontraron libros.");
            return;
        }
        try {
            DatosLibro datosLibro = datosLibros.get(0);
            List<Autor> autors = datosLibro.autor().stream()
                    .map(Autor::new)
                    .collect(Collectors.toList());
            Libro libro = new Libro(datosLibro);
            libro.setAutor(autors);
            libroRepositorio.save(libro);
            autors.forEach(autor -> {
                if (libroRepositorio.existsById(autor.getId())) {
                    autorRepositorio.save(autor);
                }
            });
            System.out.println(libro);
            System.out.println("Libro guardado correctamente en la base de datos.");
        } catch (DataIntegrityViolationException e) {
            System.out.println("No se puede agregar libros ya existentes");;
        }
    }


    private void mostrarLibros() {
        libros = libroRepositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void mostrarAutores() {
        List<Autor> autores = autorRepositorio.findAll();
        List<Autor> autors = relacionAutoresLibros(autores);
        autors.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    public void autoresVivosAnio() {
        System.out.println("\nIngrese el año en el que quiere conocer que autores estaban vivos: ");
        try {
            var anio = Integer.parseInt(scanner.nextLine());
            List<Autor> autoresVivos = autorRepositorio.autoresVivosEnEseAnio(anio);
            if (autoresVivos.isEmpty()) {
                System.out.println("No hay autores vivos en ese año");
            } else {
                List<Autor> autorsVivos = relacionAutoresLibros(autoresVivos);
                System.out.println("Autores encontrados: ");
                autorsVivos.stream()
                        .sorted(Comparator.comparing(Autor::getNombre))
                        .forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingresa un año válido");;
        }
//        var anio = scanner.nextInt();
//        scanner.nextLine();
    }

    public void buscarLibrosPorIdioma() {
        String menuIdiomas = """
                
                Ingrese el idioma:
                - Inglés
                - Español
                - Francés
                - Portugués""";
        System.out.println(menuIdiomas);
        var idioma = scanner.nextLine();
        try {
            var idiomas = Idiomas.fromEspanol(idioma);
            List<Libro> libroIdioma = libroRepositorio.findByIdiomas(idiomas);
            System.out.println("Libros en el idioma: " + idioma);
            if (libroIdioma.isEmpty()) {
                System.out.println("No hay libros guardados para ese idioma");
            } else {
                libroIdioma.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ningún libro se encuentra en ese idioma: " + idioma);
        }
    }

    public void mostrarTop10() {
        List<Libro> topLibros = libroRepositorio.findTop10ByOrderByDescargasDesc();
        System.out.println("Top 10 de libros con más descargas:");
        topLibros.forEach(l ->
                System.out.println("Libro: " + l.getTitulo() + ", Total de descargas: " + l.getDescargas()));
    }

    private void estadisticasDescargas() {
        List<Libro> libro = libroRepositorio.findByOrderByDescargasDesc();
        IntSummaryStatistics est =libro.stream()
                .filter(l -> l.getDescargas() > 0)
                .collect(Collectors.summarizingInt(Libro::getDescargas));
        System.out.printf("Promedio de las descargas de todos los libros: %.2f\n", est.getAverage());
        System.out.println("Libro con la mayor cantidad de descargas: " + "\nLibro: " + libro.get(0).getTitulo() + ", descargas: " + est.getMax());
        System.out.println("Libro con la menor cantidad de descargas: " + "\nLibro: " + libro.get(libro.size()-1).getTitulo() + ", descargas: " + est.getMin());
    }

    public void buscarAutorPorNombre() {
        System.out.println("Escribe el nombre del autor que deseas buscar: ");
        String nombre = scanner.nextLine();
        List<Autor> autor = autorRepositorio.autorPorNombre(nombre);
        if (autor.isEmpty()) {
            System.out.println("No hay autores con ese nombre");
        } else {
            List<Autor> autores = relacionAutoresLibros(autor);
            autores.stream()
                    .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(System.out::println);
        }
    }

    public List<Autor> relacionAutoresLibros(List<Autor> autores) {
        Map<String, Autor> autoresConLibros = new HashMap<>();

        for (Autor autor : autores) {
            if (!autoresConLibros.containsKey(autor.getNombre())) {
                autoresConLibros.put(autor.getNombre(), autor);
            } else {
                autoresConLibros.get(autor.getNombre()).getLibro().addAll(autor.getLibro());
            }
        }

        return new ArrayList<>(autoresConLibros.values());
    }
}
