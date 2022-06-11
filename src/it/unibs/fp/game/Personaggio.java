package it.unibs.fp.game;

import java.util.ArrayList;

public class Personaggio extends Entita{
    public static final int HP = 20;
    public static final int ATTACCO_BASE = 5;
    public static final int DIFESA_BASE = 5;
    public static final Oggetto OGGETTO_BASE = new Oggetto(Oggetto.PUGNI, 1);
    public static final int MAX_OGGETTI = 6;

    private ArrayList<Oggetto> inventario = new ArrayList<>();
    private int nObj = inventario.size();

    public Personaggio(String nome, int x, int y) {
        super(nome, HP, ATTACCO_BASE, DIFESA_BASE, OGGETTO_BASE, x, y);
        aggiungiOggettoInventario(ogg);
    }

    public StringBuffer stampaInventario() {
        StringBuffer inv = new StringBuffer();
        for(Oggetto o : inventario) {
            inv.append(o.toString() + "\n");
        }
        return inv;
    }

    public int getnObj() {
        return nObj;
    }

    public ArrayList<Oggetto> getInventario() {
        return inventario;
    }

    public void aggiungiOggettoInventario(Oggetto o) {
        this.inventario.add(o);
        this.nObj = inventario.size();
    }

    public void rimuoviOggettoInventario(Oggetto o) {
        if(this.inventario.contains(o)) {
            this.inventario.remove(o);
            this.nObj = inventario.size();
        }
    }

    @Override
    public void setHp(int danno) {
        if(ogg.getTipo() == Oggetto.SCUDO) {
            int dannoRimanente = ogg.getHpScudo() - danno;
            if(dannoRimanente > 0)
                ogg.setHpScudo(Oggetto.HP_SCUDO - dannoRimanente);
            else {
                super.setHp(-dannoRimanente);
                inventario.remove(ogg);
                ogg = inventario.get(0);
                System.out.println("Scudo sostituto con " + ogg.toString());
            }
        }
        else
            super.setHp(danno);
    }

    public StringBuffer stampaStatistiche() {
        StringBuffer stats = new StringBuffer();
        stats.append("Nome: " + getNome() + "\n");
        stats.append("HP: " + getHp() + "\n");
        stats.append("Attacco Base: " + getAttaccoBase() + "\n");
        stats.append("Difesa Base: " + getDifesaBase() + "\n");
        stats.append("Oggetto Base: " + getOgg().toString() + "\n");

        return stats;
    }

    public void cura(int cura) {
        super.setHp(-cura);
        if(getHp() > Personaggio.HP)
            this.hp = HP;
    }
}
