package logic.Lists;

import logic.GameObjects.GameObject;
import logic.GameObjects.IAttack;

import java.util.ArrayList;

public class GameObjectList {

    private ArrayList<GameObject> gameobjects;

    public GameObjectList() {
        gameobjects = new ArrayList<GameObject>();
    }

    // Añade un objeto a la lista
    public void add(IAttack x) {
        gameobjects.add((GameObject) x);
    }

    //recorre el arraylist en busca si hay un objeto en la posicion X e Y.
    public GameObject getObjectInPosition(int x, int y) {
        GameObject object = null;
        int i = 0;

        while (object == null && i < gameobjects.size()) {

            if (gameobjects.get(i).isAt(x,y))
                object = gameobjects.get(i);

            i++;
        }

        return object;
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

    // Elimina todos los objetos muertos de la lista.
    public void removeDead() {
        gameobjects.removeIf(x -> !x.isAlive());
    }
}
