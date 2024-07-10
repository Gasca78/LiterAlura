package com.alura.literalura.models;

import java.text.Normalizer;

public enum Idiomas {
    INGLES("en","Ingles"),
    ESPANOL("es","Espanol"),
    FRANCES("fr","Frances"),
    PORTUGUES("pt","Portugues");

    private String idiomaAPI;
    private String idiomaEspanol;

    Idiomas(String idiomaAPI, String idiomaEspanol) {
        this.idiomaAPI = idiomaAPI;
        this.idiomaEspanol = idiomaEspanol;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.idiomaAPI.equalsIgnoreCase(text)){
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
    }

    public static Idiomas fromEspanol(String text) {
        String textNormalizado;
        for (Idiomas idiomas : Idiomas.values()) {
            textNormalizado = Normalizer.normalize(text, Normalizer.Form.NFD);
            textNormalizado = textNormalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            if (idiomas.idiomaEspanol.equalsIgnoreCase(textNormalizado)){
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
    }
}
