package logic.GameObjects;

import logic.Game;

import java.util.Random;

public abstract class GameObject implements IAttack {
    protected Game game;
    protected int vida, x, y;
    protected String letra;

    // Constructor
    public GameObject(int x, int y, int vida, Game game){
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.game = game;
    }

    // Método que devuele true si la vida del objeto es mayor que 0.
    public boolean isAlive() { return vida > 0; }

    // Método que devuleve true si el objeto esta en esa posición.
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getX() {
        return x;
    }


    // Método que devuelve la vida y el identificador del objeto.
    public String getString() { return String.format("%s [%d]", letra, vida); }

    // Devuelve un string que representa de forma serializada el estado del objeto. Subclases de esta clase podrán sobreescribir este método
    // para añadir información específica de cada subclase.
    public String serialize() {
        return String.format("%s;%d;%d;%d", letra, x, y, vida);
    }


    // Métodos abstractos
    public abstract void move();
    public abstract void attack();
}

