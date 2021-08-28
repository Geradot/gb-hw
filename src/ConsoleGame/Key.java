package ConsoleGame;

public class Key extends Object {
    private char point = 'K';
    private boolean keyTaken;

    public Key(int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);
        setKeyTaken(false);
    }

    public char getPoint() {
        return this.point;
    }

    public boolean getKeyTaken() {
        return this.keyTaken;
    }

    public void setKeyTaken(boolean keyTaken) {
        this.keyTaken = keyTaken;
    }
}
