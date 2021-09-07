package App.model;

import App.Tools;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 06.09.2021
 */

public class Enemy {

    private int health;
    private int attackPoint;
    private int x;
    private int y;
    private int enemyValueMin = 5;
    private int enemyValueMax = 10;

    public static int count = 0;

    public Enemy() {
        this.health = Tools.randomValue(enemyValueMin, enemyValueMax);
        this.attackPoint = Tools.randomValue(enemyValueMin, enemyValueMax);
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
        return this.attackPoint;
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
