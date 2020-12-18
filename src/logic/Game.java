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

    // Constructor
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

    // Método que actualiza el tablero del juego.
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

    // Método resetea el juego.
    public void reset() {
        this.ciclo = 0;
        rng = new Random(seed);
        player = new Player(rng);
        board = new GameObjectBoard();
        Dracula.resetDraculaAlive();
        Vampire.resetSpawned();
    }

    // Si el jugador tiene las monedas suficientes, ejecuta el GarlicPush (y se las resta).
    public boolean garlicPush() {

        if (player.getMonedas()>= 10) {
            board.garlicPush();
            player.restarMonedas(10);
            return true;
        }
        else
            return false;

    }

    // Si el jugador tiene las monedas suficientes, ejecuta el LightFlash (y se las resta).
    public boolean lightFlash(){

         if (player.getMonedas() >= 50) {
            board.lightFlash();
            player.restarMonedas(50);
            return true;
        }
        else
            return false;
    }
    //Método que añade  monedas al player.
    public void addCoinsToPlayer(int x){
         player.addMonedas(x);
    }

    // Devuelve si hay un objeto en esa posicion.
    public IAttack getAttackableInPosition(int x, int y) {
        return board.getAttackableInPosition(x,y);
    }

    // Añade un objeto al arraylist.
    public void addObject(IAttack object) {
        board.addObject(object);
    }

    // Devuele el string(que detalla el error si existe),si no le resta las monedas al player indicando que se pudo añadir el slayer.
    public String addSlayer(int x, int y) {
        String error = Slayer.AddSlayer(this, x, y, player.getMonedas());

        if (error == null)
            player.restarMonedas(50);

        return error;

    }

    // Devuele el string(que detalla el error si existe),si no le resta las monedas al player(les resta la inversion) indicando que se pudo añadir el BloodBank.
    public String addBloodBank(int x, int y, int inversion) {
        String error = BloodBank.AddBloodBank(this, x, y, inversion, player.getMonedas());

        if (error == null)
            player.restarMonedas(inversion);

        return error;

    }

    // Devuele el string(que detalla el error si existe),si no añade el vampiro.
    public String addVampire(String simbolo, int x, int y) {

        String error = Vampire.addVampire(this,simbolo,x,y);

        return error;

    }

    public String serialize() {

        String text;

        text = String.format("Cycles: %d\nCoins: %d\nLevel: %s\nRemaining Vampires: %d\nVampires on Board: %d\n\n", ciclo, player.getMonedas(), level.name(), Vampire.getRemaining(this), Vampire.getOnBoard());
        text += board.serialize();

        return text;
    }

    // Sobre escribe el gameOver a true, haciendo que el juego termine.
    public void end() {
        gameOver = true;
    }

    // Devuelve la variable gameover(es la que controla el final del juego).
    public boolean isFinished() {
        return gameOver;
    }

    // Comprueba si la posicion X e Y esta vacia (si no hay ningun objeto).
    public boolean isPositionEmpty(int x, int y) {
        return board.isPositionEmpty(x,y);
    }

    // Devuelve un string que representa el estado actual del game.
    public String toString() {
        return printer.toString();
    }

    // Funcion getter del level.
    public Level getLevel() {
        return level;
    }

    // Sobre escribe el mensaje final segun como se alla acabado el juego.
    public void setWinnerMsg(String x) { winnerMsg = x; }

    // Devuelve el mensaje final del juego.
    public String getWinnerMsg() {
        return winnerMsg;
    }

    // Funcion getter de la variable rng.
    public Random getRng() {
        return this.rng;
    }

    // Devuelve la posicion del objeto para gameprinter lo imprima.
    @Override
    public String getPositionToString(int x, int y) {
        return board.getPositionToString(x,y);
    }

    // Devuelve la informacion del juego para que lo imprima el gameprinter.
    @Override
    public String getInfo() {
        String x=String.format("Number of cycles: %d\nCoins: %d\nRemaining vampires: %d\nVampires on the board: %d\n", ciclo, player.getMonedas(), Vampire.getRemaining(this), Vampire.getOnBoard());;

        if(Dracula.isDraculaAlive())
            x+="Dracula is alive\n";
        return x;
    }


}
