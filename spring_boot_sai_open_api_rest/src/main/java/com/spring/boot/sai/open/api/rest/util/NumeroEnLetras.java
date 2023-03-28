package com.spring.boot.sai.open.api.rest.util;
public class NumeroEnLetras {

    private static final String[] UNIDADES = {"", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
    private static final String[] DECENAS = {"", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
    private static final String[] CENTENAS = {"", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"};

    public static String convertir(int numero) {
        if (numero == 0) {
            return "cero";
        } else if (numero < 0) {
            return "menos " + convertir(-numero);
        }

        String palabras = "";
        int residuo;

        if (numero >= 1000000) {
            residuo = numero / 1000000;
            if (residuo > 1) {
                palabras += convertir(residuo) + " millones ";
            } else {
                palabras += "un millón ";
            }
            numero %= 1000000;
        }

        if (numero >= 1000) {
            residuo = numero / 1000;
            palabras += convertir(residuo) + " mil ";
            numero %= 1000;
        }

        if (numero >= 100) {
            residuo = numero / 100;
            if (numero == 100) {
                palabras += "cien";
            } else {
                palabras += CENTENAS[residuo];
            }
            numero %= 100;
        }

        if (numero >= 20) {
            residuo = numero / 10;
            palabras += " " + DECENAS[residuo];
            numero %= 10;
        }

        if (numero >= 10) {
            int[] especiales = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
            String[] nombres = {"diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"};
            for (int i = 0; i < 10; i++) {
                if (numero == especiales[i]) {
                    palabras += " " + nombres[i];
                    numero = 0;
                    break;
                }
            }
        }

        if (numero < 10) {
            if (!palabras.isEmpty() && numero > 0) {
                palabras += " y ";
            }
            palabras += UNIDADES[numero];
        }

        return palabras.trim();
    }
}
