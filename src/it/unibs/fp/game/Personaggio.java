package it.unibs.fp.game;

import java.util.ArrayList;

public class Personaggio extends Entita{
    private static final int HP = 20;
    private static final int ATTACCO_BASE = 5;
    private static final int DIFESA_BASE = 5;
    private static final Oggetto OGGETTO_BASE = new Oggetto(Oggetto.PUGNI, 1);

    private ArrayList<Oggetto> inventario;

    public Personaggio(String nome, int x, int y) {
        super(nome, HP, ATTACCO_BASE, DIFESA_BASE, OGGETTO_BASE, x, y);
        this.inventario = new ArrayList<>();
    }



}
