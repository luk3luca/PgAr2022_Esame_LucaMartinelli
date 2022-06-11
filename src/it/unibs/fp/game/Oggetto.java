package it.unibs.fp.game;

public class Oggetto {
    public static final String PUGNI = "PUGNI";
    public static final String ARMA = "ARMA";
    public static final String SCUDO = "SCUDO";
    public static final String POZIONE = "POZIONE";

    private String tipo;
    private int potenza;
    //private String descrizione;

    public Oggetto(String tipo, int potenza) {
        this.tipo = tipo;
        this.potenza = potenza;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPotenza() {
        return potenza;
    }
}
