package ConsoleGame;

public class Door extends Object {
    private char point = '█';
    private boolean doorOpened;

    public Door(int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);
        setDoorOpened(false);
    }

    public char getPoint() {
        return this.point;
    }

    public boolean getDoorOpened() {
        return this.doorOpened;
    }

    public void setDoorOpened(boolean doorOpened) {
        this.doorOpened = doorOpened;
    }
}
