package App.model;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 06.09.2021
 */

public class Player {

    private String name;
    private int health;
    private int attackPoint;
    private int x;
    private int y;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.attackPoint = 10;
    }

    public void moveUp(){
        this.y -= 1;
    }

    public void moveLeft(){
        this.x -= 1;
    }

    public void moveRight(){
        this.x += 1;
    }

    public void moveDown(){
        this.y += 1;
    }

    public String getName() {
        return name;
    }

    public String getCoordinatesInfo() {
        return (this.x + 1) + ":" + (this.y + 1);
    }

    public void decreaseHealth(int value) {
        this.health -= value;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return health > 0;
    }

}
