package it.unibs.fp.game;

public class Chest {
    public static final int PROB_ARMA = 40;
    public static final int PROB_SCUDO = 35;
    public static final int PROB_POZIONE = 25;

    private Oggetto ogg;
    private int x;
    private int y;
    private boolean open = false;

    public Chest(Oggetto o, int x, int y) {
        this.ogg = o;
        this.x = x;
        this.y = y;
    }

    public Oggetto getOgg() {
        return ogg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
