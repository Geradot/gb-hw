package ConsoleGame;

public class Player extends Object {
    private char point = '&';
    private String name;
    private int attackPoint;
    private int healthPoint;

    public Player(String name, int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);
        setAttackPoint(20);
        setHealthPoint(100);
        setName(name);
    }
    public boolean isPlayerAlive() {
        return this.getHealthPoint() > 0;
    }
    public char getPoint() {
        return this.point;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoint() {
        return this.attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }
}
