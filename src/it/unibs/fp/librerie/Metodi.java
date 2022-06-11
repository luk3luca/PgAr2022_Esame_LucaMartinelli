package it.unibs.fp.librerie;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe con metodi utili
 */
public class Metodi {
    /**
     * Metodo per generare un numero casuale compreso tra 2 estremi, escluso lo 0
     * <p>Il numero viene rigenerato se e' uguale a 0</p>
     *
     * @param min Estremo inferiore dell'intervallo
     * @param max Estremo superiore dell'intervallo
     * @return Ritorna il numero randomico generato
     */
    public static int generateRandomNoZero(int min, int max) {
        Random rand = new Random();
        int range = max + 1 - min;
        int ii;
        do {
            ii = rand.nextInt(range) + min;
        }while(ii == 0);

        return ii;
    }

    public static int generateRandom(int min, int max) {
        Random rand = new Random();
        int range = max + 1 - min;
        int ii = rand.nextInt(range) + min;

        return ii;
    }

    public static String generatePermutation(String word) {
        word.toLowerCase();
        List<Character> lettere = word.chars().mapToObj(e->(char)e).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        do {
            Collections.shuffle(lettere);

            for (char c : lettere) {
                sb.append(c);
            }
        }while(sb.equals(word));

        return sb.toString();
    }

    public static boolean isPari(int n) {
        return n % 2 == 0;
    }

}