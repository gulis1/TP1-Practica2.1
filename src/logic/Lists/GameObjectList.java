package logic.Lists;

import logic.Game;
import logic.GameObjects.GameObject;
import logic.GameObjects.IAttack;

import java.util.ArrayList;

public class GameObjectList {

    private ArrayList<GameObject> gameobjects;

    public GameObjectList() {
        gameobjects = new ArrayList<GameObject>();
    }

    // Añade un objeto a lista
    public void add(IAttack x) { gameobjects.add((GameObject) x); }


    // Recorre el arraylist en busca si hay un objeto en la posicion X e Y.
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

    // Invoca el metodo receiveGarlicPush de todos los objetos de la lista.
    public void garlicPush() {
        // Para mover primero los vampiros de cada que se encuentren más atras, se recorre la lista al revés.
        // De este modo arreglamos el problema de que los vampiros ocupen la misma posicion que los slayers tras un garlicPush.
        for (int i = gameobjects.size() - 1; i >= 0; i--)
            gameobjects.get(i).receiveGarlicPush();
    }

    // Invoca el metodo receiveLightFlash de todos los objetos de la lista.
    public void light(){
        for(GameObject object : gameobjects){
            object.receiveLightFlash();
        }
    }

    // Elimina todos los objetos muertos de la lista.
    public void removeDead() {
        gameobjects.removeIf(x -> !x.isAlive());
    }

}
