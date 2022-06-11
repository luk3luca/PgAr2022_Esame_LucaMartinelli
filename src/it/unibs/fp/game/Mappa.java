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

    public void setCella(int row, int col, char c) {
        mappa[row][col] = new Cella('c');
    }

    public Cella[][] getMappa() {
        return mappa;
    }

    public void stampaMappa() {
        /*
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(mappa[i][j].stampa() + " ");
            }
            System.out.println();
        }

         */

        for(Cella[] cr : mappa) {
            for (Cella cc : cr)
                System.out.print(cc.getC() + " ");
            System.out.println();
        }
    }
}
