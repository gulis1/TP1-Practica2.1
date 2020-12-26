package logic.GameObjects;

import control.Exceptions.*;
import logic.Game;

public class Vampire extends GameObject {

    protected static int onBoard = 0;
    protected static int total = 0;
    protected static final int HP = 5;
    protected boolean shouldMove = false;

    public Vampire(int x, int y, int hp, Game game) {
        super(x,y,hp,game);
        letra = "V";
    }

    // Resetea el numero de vampiros que han salido.
    public static void resetSpawned() {
        onBoard = 0;
        total = 0;
    }

    // Hace aparecer un vampiro en el game (Si el Random decide que debe salir)
    public static void summon(Game game) {

        if (total < game.getLevel().numVampirosLv() && game.getRng().nextDouble() < game.getLevel().getFrecuencia()) {

            int x = game.getLevel().getDimX() - 1;
            int y = game.getRng().nextInt(game.getLevel().getDimY());

            if (game.getAttackableInPosition(x, y) == null) {
                game.addObject(new Vampire(x, y, HP, game));
                total++;
                onBoard++;
            }
        }
    }

    //Comprueba si el vampiro esta vivo, luego se verifica si hay en objeto en la posicion proxima al vampiro (x-1) y si hay un slayer(object!=null) recive le daño.
    public void attack() {
        if (isAlive () ) {
            IAttack other = game.getAttackableInPosition(x - 1, y);
            if (other != null )
                other.receiveVampireAttack(1);
        }
    }

    // Recibe un ataque del slayer y mata al vampiro si su vida llega a 0.Aqui tambien se realiza la comprobación de que hayan muerto todos los vampiros
    public boolean receiveSlayerAttack(int damage) {

        if (vida > 0) {
            vida -= damage;

            if (!isAlive()) {
                onBoard--;
                checkEnd();
            }


            return true;
        }

        else
            return false;
    }

    // Mueve el vampiro una casilla si esta se encuentra vacía. También se realiza comprueba si algún vampiro ha llegado al final del tablero.
    @Override
    public void move() {

        if (shouldMove) {
            if (game.isPositionEmpty(x- 1, y)) {
                x--;
                shouldMove = false;
            }

        }

        else
            shouldMove = true;


        if (x==-1) {
            game.end();
            game.setWinnerMsg("Vampires win!");
        }

    }

    // Comprueba si ya han muerto todos los vampiros.
    public void checkEnd() {
        if (onBoard == 0 && total == game.getLevel().numVampirosLv()) {
            game.end();
            game.setWinnerMsg("Player wins");
        }
    }

    // Empuja a todos los vampiros hacia atrás si la casilla de detrás esta libre.
    @Override
    public boolean receiveGarlicPush() {
        IAttack other = game.getAttackableInPosition(x+1, y);

        shouldMove = false; // El vampiro queda aturdido aunque no se mueva

        if (other == null) {

            if (x == game.getLevel().getDimX() - 1) {
                vida = 0;
                onBoard--;
                checkEnd();
            }

            x++;

            return true;
        }

        return false;
    }

    //Elimina todos los vampiros.
    @Override
    public boolean receiveLightFlash() {
        vida = 0;
        onBoard--;
        checkEnd();

        return true;
    }

    //Añade un vampiro segun la letra(simbolo) que le pasen.y hace las compobraciones  pertinentes( si hay algun error lo devuelve).
    public static void addVampire(Game game,String simbolo, int x, int y) throws CommandExecuteException {
        int dimX = game.getLevel().getDimX();
        int dimY = game.getLevel().getDimY();

        if(getRemaining(game)>0) {
            if (x >= 0 && x < dimX && y >= 0 && y < dimY && game.isPositionEmpty(x, y)) {

                switch(simbolo) {

                    case "v":
                        game.addObject(new Vampire(x, y, HP, game));
                        onBoard++;
                        total++;

                    break;

                    case "e":
                        game.addObject(new ExplosiveVampire(x, y, HP, game));
                        onBoard++;
                        total++;

                    break;

                    case "d":
                        if(!Dracula.isDraculaAlive()) {
                            game.addObject(new Dracula(x, y, HP, game));
                            onBoard++;
                            total++;
                        }

                        else
                            throw  new DraculaIsAliveException("Dracula is already alive");
                        break;

                    default:
                       throw new InvalidTypeException("invalid type");
                }



            } else
                throw new InvalidPositionException(x, y, "Invalid position");
        }

        else
            throw new NoMoreVampiresException("No more remaining vampires left");

    }

    // Devuelve la cantidad de vampiros que quedan por salir.
    public static int getRemaining(Game game) {
        return game.getLevel().numVampirosLv() - total;
    }

    // Devuelve la cantidad de vampiros que hay en el tablero.
    public static int getOnBoard( ) {
        return onBoard;
    }

    @Override
    public String serialize() {
        return super.serialize() + (shouldMove ? ";1" : ";0");
    }
}
