package it.unibs.fp.game;

public class Main {
    public static void main(String[] args) {
        Partita p = new Partita();
        boolean risultatoPartita = p.gioco();
        if(risultatoPartita)
            System.out.println("Complimenti hai salvato la Principessa Kibo");
        else
            System.out.println("GAME OVER");
    }
}
