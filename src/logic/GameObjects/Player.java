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

        if (x <= monedas)
            monedas -= x;

    }


    // añaden monedas segun el rng.
    public void addMonedas(int x) {
        monedas += x;
    }

    // funcion getters.
    public int getMonedas() {

        return monedas;
    }


}