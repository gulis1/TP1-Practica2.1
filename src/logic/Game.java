package logic;

import logic.GameObjects.*;
import view.GamePrinter;
import view.IPrintable;

import java.util.Random;

public class Game implements IPrintable {

    private Level level;
    private long seed;
    private GameObjectBoard board;
    private Random rng;
    private int ciclo;
    private Player player;
    private boolean gameOver;
    private String winnerMsg;

    private GamePrinter printer;

    //contructor
    public Game(Long seed, Level level) {
        this.ciclo = 0;
        this.level = level;
        this.seed = seed;
        rng = new Random(seed);
        board = new GameObjectBoard();
        player = new Player(rng);
        printer = new GamePrinter(this, level.getDimX(), level.getDimY());
        gameOver = false;

    }

    //metodo que actualiza el tablero del juego.
    public void update() {

        if (rng.nextFloat() > 0.5 )
            player.addMonedas(10);

        board.update();
        Vampire.summon(this);
        Dracula.summon(this);
        ExplosiveVampire.summon(this);

        if (!isFinished())
            ciclo++;
    }

    //metodo resetea el juego.
    public void reset() {
        this.ciclo = 0;
        rng = new Random(seed);
        player = new Player(rng);
        board = new GameObjectBoard();
        Dracula.resetDraculaAlive();
        Vampire.resetSpawned();
    }

    public boolean garlicPush() {

        if (player.getMonedas()>= 10) {
            board.garlicPush();
            player.restarMonedas(10);
            return true;
        }
        else
            return false;

    }

    public boolean lightFlash(){

         if (player.getMonedas() >= 50) {
            board.lightFlash();
            player.restarMonedas(50);
            return true;
        }
        else
            return false;
    }
    //metodo que añade 1000 monedas al player.
    public void addCoinsToPlayer(int x){
         player.addMonedas(x);
    }

    //devuelve si hay un objeto en esa posicion.
    public IAttack getAttackableInPosition(int x, int y) {
        return board.getAttackableInPosition(x,y);
    }

    //añade un objeto al arraylist.
    public void addObject(IAttack object) {
        board.addObject(object);
    }

    //devuele el string(que detalla el error si existe),si no le resta las monedas al player indicando que se pudo añadir el slayer.
    public String addSlayer(int x, int y) {
        String error = Slayer.AddSlayer(this, x, y, player.getMonedas());

        if (error == null)
            player.restarMonedas(50);

        return error;

    }

    public String addBloodBank(int x, int y, int inversion) {
        String error = BloodBank.AddBloodBank(this, x, y, inversion, player.getMonedas());

        if (error == null)
            player.restarMonedas(inversion);

        return error;

    }
    public String addVampire(String simbolo, int x, int y) {

        String error = Vampire.addVampire(this,simbolo,x,y);

        return error;

    }

    //sobre escribe el gameOver a true, haciendo que el juego termine.
    public void end() {
        gameOver = true;
    }

    //devuelve la variable gameover(es la que controla el final del juego).
    public boolean isFinished() {
        return gameOver;
    }

    //comprueba si la posicion X e Y esta vacia (si no hay ningun objeto).
    public boolean isPositionEmpty(int x, int y) {
        return board.isPositionEmpty(x,y);
    }

    // Devuelve un string que representa el estado actual del game.
    public String toString() {
        return printer.toString();
    }

    //funcion getter del level.
    public Level getLevel() {
        return level;
    }

    //sobre escribe el mensaje final segun como se alla acabado el juego.
    public void setWinnerMsg(String x) { winnerMsg = x; }

    //devuelve el mensaje final del juego.
    public String getWinnerMsg() {
        return winnerMsg;
    }

    //funcion getter de la variable rng.
    public Random getRng() {
        return this.rng;
    }

    //devuelve la posicion del objeto para gameprinter lo imprima.
    @Override
    public String getPositionToString(int x, int y) {
        return board.getPositionToString(x,y);
    }

    //devuelve la informacion del juego para que lo imprima el gameprinter.
    @Override
    public String getInfo() {
        String x=String.format("Number of cycles: %d\nCoins: %d\nRemaining vampires: %d\nVampires on the board: %d\n", ciclo, player.getMonedas(), Vampire.getRemaining(this), Vampire.getOnBoard());;

        if(Dracula.isDraculaAlive())
            x+="Dracula is alive\n";
        return x;
    }


}
