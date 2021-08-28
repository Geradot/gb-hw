package ConsoleGame;

import org.jetbrains.annotations.NotNull;

import static ConsoleGame.ConsoleApp.random;
import static ConsoleGame.ConsoleApp.randomValue;

public class GameMap {
    private char[][] cells;
    private char[][] invisibleCells;
    private int mapWidth;
    private int mapHeight;
    private int mapValueMin;
    private int mapValueMax;
    private char cellEmpty = '░';
    private char cellReady = '▒';

    public GameMap(int mapValueMin, int mapValueMax) {
        this.mapValueMin = mapValueMin;
        this.mapValueMax = mapValueMax;
        this.mapHeight = randomValue(this.mapValueMin, this.mapValueMax);
        this.mapWidth = randomValue(this.mapValueMin, this.mapValueMax);
        this.cells = new char[this.mapHeight][this.mapWidth];
        this.invisibleCells = new char[this.mapHeight][this.mapWidth];
    }

    public void createMap() {
        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                //this.cells[y][x] = cellEmpty;
                setCells(y, x, cellEmpty);
            }
        }
        System.out.println("Создана карта размером " + this.mapWidth + "x" + this.mapHeight);
    }

    public void spawnPlayer(Player player) {
        this.cells[player.getPositionY()][player.getPositionX()] = player.getPoint();
    }

    public void spawnEnemies(Player player, @NotNull Mine mine) {
        int enemyPosX;
        int enemyPosY;

        for (int i = 0; i < mine.getCount(); i++) {

            do {
                enemyPosX = random.nextInt(this.mapWidth);
                enemyPosY = random.nextInt(this.mapHeight);
            } while (this.cells[enemyPosY][enemyPosX] == player.getPoint() ||
                    this.invisibleCells[enemyPosY][enemyPosX] == mine.getPoint());

            this.invisibleCells[enemyPosY][enemyPosX] = mine.getPoint();
        }
    }

    public void spawnKey(Player player, Mine mine, Key key) {
        int keyPosX;
        int keyPosY;

        do {
            keyPosX = random.nextInt(this.mapWidth);
            keyPosY = random.nextInt(this.mapHeight);
        } while (this.cells[keyPosY][keyPosX] == player.getPoint() ||
                this.invisibleCells[keyPosY][keyPosX] == mine.getPoint());

        this.cells[keyPosY][keyPosX] = key.getPoint();

        System.out.println("Найдите ключ! Он открывает дверь на новый уровень!");
        System.out.println("Координаты ключа: [" + (keyPosX + 1) + "][" + (keyPosY + 1) + "]");
    }

    public void spawnDoor(Player player, Mine mine, Key key, Door door) {
        int doorPosX;
        int doorPosY;

        do {
            doorPosX = random.nextInt(this.mapWidth);
            doorPosY = random.nextInt(this.mapHeight);
        } while (this.cells[doorPosY][doorPosX] == player.getPoint() ||
                this.invisibleCells[doorPosY][doorPosX] == mine.getPoint() ||
                this.cells[doorPosY][doorPosX] == key.getPoint());

        this.cells[doorPosY][doorPosX] = door.getPoint();
        // door.setPositionX(doorPosX);
        // door.setPositionY(doorPosY);
    }



    public void showMap() {
        System.out.println("===== КАРТА =====");
        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                System.out.print(this.cells[y][x] + "│");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    public char getCells(int y, int x) {
        return this.cells[y][x];
    }

    public int getCellsLength() {
        return this.cells.length;
    }

    public int getCellsLength(int y) {
        return this.cells[y].length;
    }

    public char getInvisibleCells(int y, int x) {
        return this.invisibleCells[y][x];
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapValueMin() {
        return mapValueMin;
    }

    public int getMapValueMax() {
        return mapValueMax;
    }

    public char getCellEmpty() {
        return this.cellEmpty;
    }

    public char getCellReady() {
        return this.cellReady;
    }

    public void setCells(int y, int x, char point) {
        this.cells[y][x] = point;
    }

    public void setInvisibleCells(int y, int x, char point) {
        this.invisibleCells[y][x] = point;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMapValueMin(int mapValueMin) {
        this.mapValueMin = mapValueMin;
    }

    public void setMapValueMax(int mapValueMax) {
        this.mapValueMax = mapValueMax;
    }

    public void setCellEmpty(char cellEmpty) {
        this.cellEmpty = cellEmpty;
    }

    public void setCellReady(char cellReady) {
        this.cellReady = cellReady;
    }

}
