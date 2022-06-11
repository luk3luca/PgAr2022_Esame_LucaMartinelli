package it.unibs.fp.game;

public class Oggetto {
    public static final String PUGNI = "PUGNI";
    public static final String ARMA = "ARMA";
    public static final String SCUDO = "SCUDO";
    public static final String POZIONE = "POZIONE";
    public static final int HP_SCUDO = 5;

    private String tipo;
    private int potenza;
    private int hpScudo;
    //private String descrizione;

    public Oggetto(String tipo, int potenza) {
        this.tipo = tipo;
        this.potenza = potenza;
        this.hpScudo = HP_SCUDO;
    }

    public int getHpScudo() {
        return hpScudo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setHpScudo(int danno) {
        this.hpScudo -= hpScudo;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + "\tPotenza: " + potenza;
    }
}
