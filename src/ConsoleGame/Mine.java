package ConsoleGame;

public class Mine extends Object {
    private char point = 'M';
    private int count;
    private int attackPoint;
    private int healthPoint;
    public Mine(int mapWidth, int mapHeight) {
        setAttackPoint(20);
        setHealthPoint(20);
        setCount(mapWidth, mapHeight);
    }

    public char getPoint() {
        return point;
    }

    public int getCount() {
        return count;
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

    public void setCount(int mapWidth, int mapHeight) {
        this.count = (mapWidth + mapHeight) / 4;
    }
}
