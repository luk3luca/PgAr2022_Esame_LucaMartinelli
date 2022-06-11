package it.unibs.fp.game;

public class Chest {
    public static final int PROB_ARMA = 40;
    public static final int PROB_SCUDO = 35;
    public static final int PROB_POZIONE = 25;

    private Oggetto o;
    private boolean open = false;

    public Chest(Oggetto o) {
        this.o = o;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
