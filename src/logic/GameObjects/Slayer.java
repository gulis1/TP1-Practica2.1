package logic.GameObjects;

import logic.Game;

import java.util.Random;

public class Slayer extends GameObject {

    public static final int HP = 3;
    // constructor
    public Slayer(int x, int y, int vida, Game game) {
        super(x,y,vida,game);
        letra = "S";
    }

    //metodo attack del slayer  , recorre toda la fila en la que esta desde su posicion +1 y para cuando alla goleado a un vampiro.
    public void attack() {
        IAttack other = null;
        int i = x+1;
        boolean hitSomeone = false;

        if (isAlive ()) {

            while(!hitSomeone &&  i < game.getLevel().getDimX()) {

                other = game.getAttackableInPosition(i, y);
                if (other != null)
                    hitSomeone = other.receiveSlayerAttack(1);

                i++;

            }
        }
    }

    //comprueba si el slayer esta vivo, si lo esta, se le resta la vida y devuleve true.
    public boolean receiveVampireAttack(int damage) {

        if (vida > 0) {
            vida -= damage;
            return true;
        }

        else
            return false;
    }

    // Intenta añadir un slayer a la partida. En caso de no poderse, devuelve un mensaje de error indicando el motivo por el cual no se pudo.
    public static String AddSlayer(Game game, int x, int y, int monedas) {
        int dimX = game.getLevel().getDimX();
        int dimY = game.getLevel().getDimY();

        if (x >= 0 && x < dimX && y >= 0 && y < dimY && game.isPositionEmpty(x, y)) {
            if (monedas >= 50)
                game.addObject(new Slayer(x, y, 3, game));
            else
                return "Not enough coins";

            return null;
        }

        else
            return "Invalid position";


    }
    //metodo move  heredado de la superclase, esta vacio porque los slayers no se mueven.
    public void move(){

    };
}
