package it.unibs.fp.game;

public class Mostro extends Entita{
    private static final int ATTACCO_BASE = 5;
    private static final int DIFESA_BASE = 5;

    public Mostro(String nome, int hp, Oggetto ogg, int x, int y) {
        super(nome, hp, ATTACCO_BASE, DIFESA_BASE, ogg, x, y);
    }

}
