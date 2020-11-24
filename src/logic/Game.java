package logic;

import logic.GameObjects.*;
import view.GamePrinter;
import view.IPrintable;

import java.util.Random;

public class Game implements IPrintable {

    private Level level;
    private long seed;
    private GamePrinter printer;
    private GameObjectBoard board;
    private Random rng;
    private int ciclo;
    private Player player;
    private boolean gameOver;

    //contructor
    public Game(Long seed, Level level) {
        this.ciclo = 0;
        this.level = level;
        this.seed = seed;
        rng = new Random(seed);
        board = new GameObjectBoard();
        player = new Player(rng);
        gameOver = false;

    }

    public void update() {
        player.addMonedas();
        board.attack();
        board.move();
        board.removeDead();
        Vampire.summonVampire(this);
        ciclo++;
    }

    public IAttack getAttackableInPosition(int x, int y) {
        return board.getAttackableInPosition(x,y);
    }

    public void addObject(IAttack object) {
        board.addObject(object);
    }

    public void removeObject(IAttack object) {
        board.removeObject(object);
    }

    public boolean addSlayer(int x, int y) {
        if (Slayer.AddSlayer(this, x, y, player.getMonedas())) {
            player.restarMonedas(50);
            return true;
        }

        else
            return false;


    }

    public void end() {
        gameOver = true;
    }

    public boolean isFinished() {
        return gameOver;
    }

    public float nextFloat() {
        return rng.nextFloat();
    }

    public double nextDouble() {
        return rng.nextDouble();
    }

    public int nextInt(int x) {
        return rng.nextInt(x);
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String getPositionToString(int x, int y) {
        return board.getPositionToString(x,y);
    }

    @Override
    public String getInfo() {
        return String.format("Number of cycles: %d\nCoins: %d\nRemaining vampires: %d\nVampires on the board: %d\n", ciclo, player.getMonedas(), Vampire.getRemaining(this), Vampire.getOnBoard());
    }

    public boolean isPositionEmpty(int x, int y) {
        return board.isPositionEmpty(x,y);
    }
}
