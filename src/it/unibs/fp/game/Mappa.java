package it.unibs.fp.game;

public class Mappa {
    private int width;
    private int height;
    private Cella[][] mappa;

    public Mappa() {
        this.mappa = ReadXmlDomParser.creaMappa();
        this.height = mappa.length;
        this.width = mappa[0].length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cella getCella(int row, int col) {
        return mappa[row][col];
    }
}
