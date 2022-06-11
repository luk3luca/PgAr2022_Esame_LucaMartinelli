package it.unibs.fp.game;

public class Chest {
    public static final int PROB_ARMA = 40;
    public static final int PROB_SCUDO = 35;
    public static final int PROB_POZIONE = 25;

    private Oggetto o;
    private int x;
    private int y;
    private boolean open = false;

    public Chest(Oggetto o, int x, int y) {
        this.o = o;
        this.x = x;
        this.y = y;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
