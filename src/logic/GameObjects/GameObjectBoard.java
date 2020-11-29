package logic.GameObjects;

import logic.Game;

import java.util.ArrayList;

public class GameObjectBoard {

    private ArrayList<GameObject> gameobjects;

    public GameObjectBoard(){
        gameobjects = new ArrayList<GameObject>();
    }

    // Añade un GameObject a la lista.
    public void addObject(IAttack object) {
        gameobjects.add((GameObject)object);
    }

    // Invoca el metodo attack de todos los objetos de la lista.
    public void attack() {
        for (GameObject object : gameobjects)
            object.attack();
    }

    // Invoca el metodo move de todos los objetos de la lista.
    public void move() {
        for (GameObject object : gameobjects)
            object.move();
    }

    private GameObject getObjectInPosition(int x, int y) {
        GameObject object = null;
        int i = 0;

        while (object == null && i < gameobjects.size()) {

            if (gameobjects.get(i).isAt(x,y))
                object = gameobjects.get(i);

            i++;
        }

        return object;
    }

    public IAttack getAttackableInPosition (int x, int y) {

        return (IAttack) getObjectInPosition(x,y);

    }

    // Devuelve un string que representa una posición (x,y) en el tablero.
    public String getPositionToString(int x, int y) {

        GameObject object = getObjectInPosition(x, y);

        if (object == null)
            return " ";
        else
            return object.getString();
    }

    // Comprueba si una posición (x,y) esta vacía
    public boolean isPositionEmpty(int x, int y) {
        return getAttackableInPosition(x,y) == null;
    }

    // Elimina todos los objetos muertos de la lista.
    public void removeDead() {
        gameobjects.removeIf(x -> !x.isAlive());
    }
}
