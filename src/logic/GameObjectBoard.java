package logic;

import logic.Game;
import logic.GameObjects.GameObject;
import logic.GameObjects.IAttack;
import logic.Lists.GameObjectList;

import java.util.ArrayList;

public class GameObjectBoard {

    private GameObjectList list;

    public GameObjectBoard(){
        list = new GameObjectList();
    }

    // Añade un objeto a la lista.
    public void addObject(IAttack object) {
        list.add(object);
    }

    public void update() {
        list.move();
        list.attack();
        list.removeDead();

    }

    // Devuelve el obejto que haya en esa posicion, si no hay ninguno devuelve null.
    public IAttack getAttackableInPosition (int x, int y) {
        return (IAttack) list.getObjectInPosition(x,y);
    }

       // Devuelve un string que representa una posición (x,y) en el tablero.
    public String getPositionToString(int x, int y) {

        GameObject object = list.getObjectInPosition(x, y);

        if (object == null)
            return " ";
        else
            return object.getString();
    }

    // Comprueba si una posición (x,y) esta vacía
    public boolean isPositionEmpty(int x, int y) {
        return getAttackableInPosition(x,y) == null;
    }

    public void garlicPush() {
        list.garlicPush();
    }
    public void lightFlash() {
        list.light();
    }

    public String serialize() {
        return list.serialize();
    }
}
