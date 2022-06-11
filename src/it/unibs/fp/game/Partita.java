package it.unibs.fp.game;

public class Partita {
    private Livello livello1;
    private int turno;

    public Partita() {
        this.livello1 = new Livello();
        this.turno = 0;
    }

    public boolean gioco() {
        boolean turnoFinito;

        livello1.getMappa().stampaMappa();

        while(!livello1.isPrincessSalva() && livello1.getP().isVivo()) {
            this.turno++;
            livello1.isAMostro();
            //SE IL GIOCATORE MUORE FINISCE IL GIOCO
            if(!livello1.getP().isVivo())
                return false;

            do {
                turnoFinito = livello1.inputComandi();
            }while(!turnoFinito);

            livello1.getMappa().stampaMappa();
        }

        return true;
    }
}
