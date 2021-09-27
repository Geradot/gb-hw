package App;

import App.model.Enemy;
import App.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 02.09.2021
 */

public class GameMap extends JPanel {

    private GameWindow gameWindow;

    private Player player;
    private Enemy enemy;

    private final int CELL_PLAYER = 1;
    private final int CELL_ENEMY = 2;
    private final int CELL_READY = 99;
    private final int CELL_EMPTY = 0;

    public static final int DIRECTION_MOVE_UP = 8;
    public static final int DIRECTION_MOVE_DOWN = 2;
    public static final int DIRECTION_MOVE_LEFT = 4;
    public static final int DIRECTION_MOVE_RIGHT = 6;

    private int[][] map;
    private int[][] invisibleMap;
    private int mapWidth;
    private int mapHeight;
    private int mapValueMin = 3;
    private int mapValueMax = 5;
    private int levelCount;

    private int cellWidth;
    private int cellHeight;

    private boolean isMapExist;
    private boolean isGameStart;

    GameMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.isMapExist = false;
        this.isGameStart = false;
        setBackground(Color.BLACK);
    }

    void startGame() {
        player = new Player("Boris");
        startNewRound();
        levelCount = 1;
        gameWindow.refreshGameInfo();
        isMapExist = true;
        isGameStart = true;
    }

    private void startNewRound() {
        createMap();
        spawnPlayer();
        spawnEnemy();
        ++levelCount;
        gameWindow.recordLog("Start level " + levelCount);
        gameWindow.refreshGameInfo();
    }

    public void update(int key) {

        if (!isMapExist) {
            return;
        }

        if (!isGameStart) {
            return;
        }

        int currentX = player.getX();
        int currentY = player.getY();

        switch (key) {
            case DIRECTION_MOVE_UP:
                player.moveUp();
                break;
            case DIRECTION_MOVE_LEFT:
                player.moveLeft();
                break;
            case DIRECTION_MOVE_RIGHT:
                player.moveRight();
                break;
            case DIRECTION_MOVE_DOWN:
                player.moveDown();
                break;
        }

        if (!isValidPlayerStep(currentX, currentY, player.getX(), player.getY())) {
            return;
        }

        playerNextMoveAction(currentX, currentY, player.getX(), player.getY());
        gameWindow.refreshGameInfo();
        repaint();

        if (isFullMap()) {
            startNewRound();
        }

        if (!player.isAlive()) {
            isGameStart = false;
            JOptionPane.showMessageDialog(this, player.getName() + " is Dead");
        }

    }

    private void render(Graphics g) {
        if (!isMapExist) {
            return;
        }

        paintMap(g);

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {

                if (map[y][x] == CELL_EMPTY) {
                    continue;
                }

                if (map[y][x] == CELL_PLAYER) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }

                if (map[y][x] == CELL_READY) {
                    g.setColor(Color.GRAY);
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }

    private void paintMap(Graphics g) {

        int widthMe = getWidth();
        int heightMe = getHeight();

        cellWidth = widthMe / mapWidth;
        cellHeight = heightMe / mapHeight;

        g.setColor(Color.WHITE);

        for (int i = 0; i <= mapHeight ; i++) {
            int y = i* cellHeight;
            g.drawLine(0, y, widthMe, y);
        }

        for (int i = 0; i < mapWidth; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, heightMe);
        }
    }

    private void createMap() {
        mapHeight = Tools.randomValue(mapValueMin, mapValueMax);
        mapWidth = Tools.randomValue(mapValueMin, mapValueMax);
        map = new int[mapHeight][mapWidth];
        invisibleMap = new int[mapHeight][mapWidth];
        gameWindow.recordLog("Create map. Size " + mapWidth + "x" + mapHeight);
        repaint();
    }

    private void spawnPlayer() {
        player.setCoordinates(Tools.randomValue(0, mapWidth - 1), Tools.randomValue(0, mapHeight - 1));
        map[player.getY()][player.getX()] = CELL_PLAYER;
    }

    private void spawnEnemy() {
        Enemy.count = (mapWidth + mapHeight) / 2;

        int tmpX;
        int tmpY;

        for (int i = 0; i < Enemy.count; i++) {
            do {
                tmpX = Tools.random.nextInt(mapWidth);
                tmpY = Tools.random.nextInt(mapHeight);
            } while (isPlayerMapCellEmpty(tmpX, tmpY) && isInvisibleMapCellEmpty(tmpX, tmpY));

            invisibleMap[tmpY][tmpX] = CELL_ENEMY;
        }
        enemy = new Enemy();
    }

    private void playerNextMoveAction(int currentX, int currentY, int nextX, int nextY) {
        if (invisibleMap[nextY][nextX] == CELL_ENEMY) {
            gameWindow.recordLog("Enemy Attack!");
            battle();
        }

        invisibleMap[nextY][nextX] = CELL_EMPTY;
        map[currentY][currentX] = CELL_READY;
        map[nextY][nextX] = CELL_PLAYER;
    }

    private void battle() {
        int tmpHP = enemy.getHealth();

        while (player.isAlive() && tmpHP > 0) {
            tmpHP -= player.getAttackPoint();
            player.decreaseHealth(enemy.getAttackPoint());
        }
        if (tmpHP <= 0) {
            --Enemy.count;
        }
        gameWindow.refreshGameInfo();
    }

    private boolean isValidPlayerStep(int currentX, int currentY, int nextX, int nextY) {
        if (nextX >= 0 && nextX < mapWidth && nextY >= 0 && nextY < mapHeight) {
            gameWindow.recordLog(player.getName() + " move to [" + (nextX + 1) + ":" + (nextY + 1) + "] success!");
            return true;
        } else {
            player.setCoordinates(currentX, currentY);
            gameWindow.recordLog(player.getName() + " is Invalid! Your move is fail!");
            return false;
        }
    }

    private boolean isFullMap() {
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (map[y][x] == CELL_EMPTY) return false;
            }
        }
        return true;
    }

    private boolean isPlayerMapCellEmpty(int x, int y) {
        return map[y][x] == CELL_PLAYER;
    }

    private boolean isInvisibleMapCellEmpty(int x, int y) {
        return invisibleMap[y][x] == CELL_ENEMY;
    }

    public Player getPlayer() {
        return player;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public String getMapSize() {
        return mapWidth + ":" + mapHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
}
