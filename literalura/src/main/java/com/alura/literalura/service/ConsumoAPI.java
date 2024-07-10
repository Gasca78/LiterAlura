package com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Método para poder realizar la solicitud de información a la API
public class ConsumoAPI {
    public String obtenerAPI(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder() //Variable para la solicitud de la informaci+on
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
//        System.out.println(response.statusCode()); Así puedes imprimir que respuesta se da a tu solicitud con statusCode()
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body();
        return json;
    }
}