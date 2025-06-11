package com.oabm77.literalura.principal;

import com.oabm77.literalura.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";

    public void getDatosLibros() {
        var json = consumoAPI.obtenerDatos(URL_BASE + "?ids=11");
        System.out.println(json);
    }
}
