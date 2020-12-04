package logic.GameObjects;

import java.util.Random;

public class Player {
    private int monedas;
    private Random rng;
    //constructor
    public Player (Random rng) {
        this.monedas = 50;
        this.rng = rng;

    }
    // comprueba si tiene monedas suficientes para comprar un slayer.(si tiene lo compra y le resta la cantidad)
    public void restarMonedas (int x) {
        boolean suficientes = false;

        if (x <= monedas) {
            suficientes = true;
            monedas -= x;
        }

    }
    // añaden monedas segun el rng.
    public void addMonedas() {
        if (rng.nextFloat() > 0.5 ) {
            monedas += 10;
        }
    }

    //añade 1000 monedas al jugador.
    public void addMonedaMil(){

        monedas+=1000;

    }
    // funcion getters.
    public int getMonedas() {

        return monedas;
    }


}