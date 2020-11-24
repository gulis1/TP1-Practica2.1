package logic.GameObjects;

import logic.Game;

import java.util.ArrayList;

public class GameObjectBoard {

    private ArrayList<GameObject> gameobjects;

    public GameObjectBoard(){
        gameobjects = new ArrayList<GameObject>();
    }

    public void addObject(IAttack object) {
        gameobjects.add((GameObject)object);
    }

    public void removeObject(IAttack object) {
        gameobjects.remove((GameObject)object);
    }

    public void attack() {
        for (GameObject object : gameobjects)
            object.attack();
    }

    public void move() {
        for (GameObject object : gameobjects)
            object.move();
    }

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

    public IAttack getAttackableInPosition (int x, int y) {

        return (IAttack) getObjectInPosition(x,y);

    }


    public String getPositionToString(int x, int y) {

        GameObject object = getObjectInPosition(x, y);

        if (object == null)
            return " ";
        else
            return object.getString();
    }

    public boolean isPositionEmpty(int x, int y) {
        return getAttackableInPosition(x,y) == null;
    }

    public void removeDead() {
        gameobjects.removeIf(x -> !x.isAlive());
    }
}
