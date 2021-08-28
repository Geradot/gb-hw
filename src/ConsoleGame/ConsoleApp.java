package ConsoleGame;

import java.util.Random;
import java.util.Scanner;

public class ConsoleApp {

    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static byte levelCount = 0;

    static GameMap gameMap = new GameMap(3, 6);

    static Player player = new Player("Антон", gameMap.getMapWidth(), gameMap.getMapHeight());
    static Mine mine = new Mine(gameMap.getMapWidth(), gameMap.getMapHeight());
    static Key key = new Key(gameMap.getMapWidth(), gameMap.getMapHeight());
    static Door door = new Door(gameMap.getMapWidth(), gameMap.getMapHeight());


    private static final byte PLAYER_MOVE_UP = 8;
    private static final byte PLAYER_MOVE_DOWN = 2;
    private static final byte PLAYER_MOVE_LEFT = 4;
    private static final byte PLAYER_MOVE_RIGHT = 6;

    public static void main(String[] args) {

        while (player.isPlayerAlive()) {
            ++levelCount;
            System.out.println("\n===== УРОВЕНЬ " + levelCount + " =====");
            levelCycle();
        }
        System.out.println("===== ИГРА ОКОНЧЕНА! " + player.getName() + " прошёл " + levelCount + " уровней =====");
    }

    public static void levelCycle() {
        gameMap.createMap();

        showLegend();

        // Располагаем маркер игрока на карте
        gameMap.spawnPlayer(player);
        System.out.println(player.getName() + " появился в точке [" + (player.getPositionX() + 1) + ":" + (player.getPositionY() + 1) + "]. Здоровье: " + player.getHealthPoint() + " ♥");

        // Генерируем и расставляем мины
        gameMap.spawnEnemies(player, mine);

        // Генерируем и располагаем ключ
        gameMap.spawnKey(player, mine, key);
        key.setKeyTaken(false);

        // Генерируем и располагаем дверь
        gameMap.spawnDoor(player, mine, key, door);
        door.setDoorOpened(false);

        boolean isDoorPositionSet = false;
        for (int y = 0; y < gameMap.getCellsLength(); y++) {
            for (int x = 0; x < gameMap.getCellsLength(y); x++) {
                if (gameMap.getCells(y, x) == door.getPoint()) {
                    door.setPositionX(x);
                    door.setPositionY(y);
                    isDoorPositionSet = true;
                    break;
                }
            }
            if (isDoorPositionSet) break;
        }

        while (true) {
            gameMap.showMap();
            changePositionPlayer();

            if (!player.isPlayerAlive()) {
                System.out.println(player.getName() + " погиб!");
                break;
            }

            if (door.getDoorOpened()) {
                System.out.println(player.getName() + " прошел уровень!");
                break;
            }
        }
    }

    public static void changePositionPlayer() {
        int currentX = player.getPositionX();
        int currentY = player.getPositionY();

        int playerMove;

        do {
            System.out.print("Введите направление шага (↑ = " + PLAYER_MOVE_UP +
                    ", ← = " + PLAYER_MOVE_LEFT +
                    ", → = " + PLAYER_MOVE_RIGHT +
                    ", ↓ = " + PLAYER_MOVE_DOWN + ") >>> ");

            playerMove = scanner.nextInt();

            switch (playerMove) {
                case PLAYER_MOVE_UP:
                    player.setPositionY(player.getPositionY() - 1);
                    break;
                case PLAYER_MOVE_LEFT:
                    player.setPositionX(player.getPositionX() - 1);
                    break;
                case PLAYER_MOVE_RIGHT:
                    player.setPositionX(player.getPositionX() + 1);
                    break;
                case PLAYER_MOVE_DOWN:
                    player.setPositionY(player.getPositionY() + 1);
                    break;
            }
        } while (!isValidPlayerStep(currentX, currentY, player.getPositionX(), player.getPositionY()));

        playerNextMoveAction(currentX, currentY, player.getPositionX(), player.getPositionY());
    }

    public static void playerNextMoveAction(int currentX, int currentY, int nextX, int nextY) {
        // Наступил на мину
        if (gameMap.getInvisibleCells(nextY, nextX) == mine.getPoint()) {
            player.setHealthPoint(player.getHealthPoint() - mine.getAttackPoint());
            System.out.println("Внимание! Враг нанёс урона: " + mine.getAttackPoint() + ". " + player.getName() + " имеет теперь " + player.getHealthPoint() + " ♥ здоровья");
            gameMap.setInvisibleCells(nextY, nextX, gameMap.getCellEmpty());
        }

        // Взятие ключа
        if (gameMap.getCells(nextY, nextX) == key.getPoint()) {
            System.out.println("Вы нашли ключ! Теперь дверь на новый уровень можно открыть.");
            key.setKeyTaken(true);
        }

        // Вход в дверь
        if (gameMap.getCells(nextY, nextX) == door.getPoint()) {
            if (!key.getKeyTaken()) System.out.println("Дверь на следующий уровень закрыта. Найдите ключ!");
            else door.setDoorOpened(true);
        }

        // Записать в текущую ячейку дверь, если было пусто, иначе - оставить дверь
        gameMap.setCells(currentY, currentX, (!(gameMap.getCells(currentY, currentX) == gameMap.getCells(door.positionY, door.positionX)) ? gameMap.getCellReady() : door.getPoint()));
        gameMap.setCells(nextY, nextX, player.getPoint());
    }

    public static boolean isValidPlayerStep(int currentX, int currentY, int nextX, int nextY) {
        if (nextX >= 0 && nextX < gameMap.getMapWidth() && nextY >= 0 && nextY < gameMap.getMapHeight()) {
            System.out.println(player.getName() + " успешно походил на [" + (nextX + 1) + ":" + (nextY + 1) + "]!");
            return true;
        } else {
            player.setPositionX(currentX);
            player.setPositionY(currentY);
            System.out.println("Вы упёрлись в стену! Сделайте правильный ход.");
            return false;
        }
    }

    public static int randomValue(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }



    public static void showLegend() {
        System.out.println("Игрок: " + player.getPoint());
        System.out.println("Ключ: " + key.getPoint());
        System.out.println("Дверь: " + door.getPoint());
        System.out.println("Остерегайтесь мин!");
    }
}
