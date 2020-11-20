package logic;

import logic.gameObjects.Player;
import logic.gameObjects.Vampiro;
import view.GamePrinter;

import java.util.Random;

public class Game {

    private Level level;
    private long seed;
    private GamePrinter printer;
    private GameObjectBoard board;
    private Random rng;
    private int ciclo;
    private Player player;

    //contructor
    public Game(Long seed, Level level) {
        this.ciclo = 0;
        this.level = level;
        this.seed = seed;
        rng = new Random(seed);
        printer =  new GamePrinter(this, this.level.getDimX(), this.level.getDimY());
        board = new GameObjectBoard(rng, this);
        player = new Player(rng);

    }

    //todas la funciones que se tienen que hacer en cada ciclo.
    public void update() {
        ciclo++;
        player.addMonedas();
        board.move();
        board.attack();
        board.summonVampires(level);
    }
    // resetea el juego e inicializa todos los objetos.
    public void reset() {
        this.ciclo = 0;
        board = new GameObjectBoard(rng, this);
        player = new Player(rng);
    }
    // saca por pantalla el tablero.
    public String printGame() {

        return this.printer.toString();
    }
    // comprueba si el juego ha finalizado( de manera no forzada).
    public int isFinished() {
        int code = 0;

        if (Vampiro.llegaronAlFinal())
            code = 1;

        else if (board.getVampRestantes() == 0 && board.getVampTablero() == 0)
            code = 2;

        return code;

    }
    // intenta poner un slayer en la pocision "X" e "Y".
    public int addSlayer(int x, int y) {
        int error = 0;

        if (!board.sePuedePonerSlayerEn(x,y))
            error = 1;

        else if (!player.restarMonedas(50))
            error  = 2;

        if (error == 0)
            board.addSlayer(x,y);

        return error;
    }
    // funciones getters.
    public int getCiclo() {
        return ciclo;
    }


    public Player getPlayer() {
        return player;
    }

    public long getSeed() {
        return seed;
    }

    public GameObjectBoard getBoard() {
        return board;
    }

    public Level getLevel() {
        return level;
    }

}
