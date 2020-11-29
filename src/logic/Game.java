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

    public void update() {
        player.addMonedas();
        board.attack();
        board.move();
        board.removeDead();
        Vampire.summonVampire(this);
        if (!isFinished())
        ciclo++;
    }

    public void reset() {
        this.ciclo = 0;
        rng = new Random(seed);
        player = new Player(rng);
        board = new GameObjectBoard();
        Vampire.resetSpawned();
    }

    public IAttack getAttackableInPosition(int x, int y) {
        return board.getAttackableInPosition(x,y);
    }

    public void addObject(IAttack object) {
        board.addObject(object);
    }

    public String addSlayer(int x, int y) {
        String error = Slayer.AddSlayer(this, x, y, player.getMonedas());

        if (error == null)
            player.restarMonedas(50);

        return error;

    }

    public void end() {
        gameOver = true;
    }

    public boolean isFinished() {
        return gameOver;
    }

    public boolean isPositionEmpty(int x, int y) {
        return board.isPositionEmpty(x,y);
    }

    // Devuelve un string que representa el estado actual del game.
    public String toString() {
        return printer.toString();
    }

    public Level getLevel() {
        return level;
    }

    public void setWinnerMsg(String x) {
        winnerMsg = x;
    }

    public String getWinnerMsg() {
        return winnerMsg;
    }

    public Random getRng() {
        return this.rng;
    }

    @Override
    public String getPositionToString(int x, int y) {
        return board.getPositionToString(x,y);
    }

    @Override
    public String getInfo() {
        return String.format("Number of cycles: %d\nCoins: %d\nRemaining vampires: %d\nVampires on the board: %d\n", ciclo, player.getMonedas(), Vampire.getRemaining(this), Vampire.getOnBoard());
    }
}
