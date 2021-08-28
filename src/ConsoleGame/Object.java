package ConsoleGame;

import static ConsoleGame.ConsoleApp.randomValue;

public class Object {
    protected int positionX;
    protected int positionY;
    protected char point;

    public Object(int mapWidth, int mapHeight) {
        this.positionX = (byte) randomValue(0, mapWidth - 1);
        this.positionY = (byte) randomValue(0, mapHeight - 1);
    }

    public Object() {

    }

    public char getPoint() {
        return point;
    }

    public void setPoint(char point) {
        this.point = point;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int x) {
        this.positionX = x;
    }

    public void setPositionY(int y) {
        this.positionY = y;
    }
}
