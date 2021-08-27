import java.util.Random;
import java.util.Scanner;

public class ConsoleApp {

    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static char playerPoint = '&';
    public static String playerName = "Антон";
    public static int playerHealthPoint = 100;
    public static int playerPositionX;
    public static int playerPositionY;
    public static final int PLAYER_MOVE_UP = 8;
    public static final int PLAYER_MOVE_DOWN = 2;
    public static final int PLAYER_MOVE_LEFT = 4;
    public static final int PLAYER_MOVE_RIGHT = 6;

    public static char enemyPoint = 'E';
    public static int enemyAttackPoint;
    public static int enemyValueMin = 20;
    public static int enemyValueMax = 40;

    public static char keyPoint = 'K';
    public static boolean isKeyTaken;
    public static char doorPoint = '█';
    public static int doorPositionX;
    public static int doorPositionY;
    public static boolean isDoorOpened;

    public static char[][] map;
    public static char[][] invisibleMap;
    public static int mapWidth;
    public static int mapHeight;
    public static int mapValueMin = 4;
    public static int mapValueMax = 8;
    public static char mapEmpty = '░';
    public static char mapReady = '▒';
    public static int levelCount = 0;

    public static void main(String[] args) {

        while (isPlayerAlive()) {
            ++levelCount;
            System.out.println("\n===== УРОВЕНЬ " + levelCount + " =====");
            levelCycle();
        }
        System.out.println("===== ИГРА ОКОНЧЕНА! " + playerName + " прошёл " + levelCount + " уровней =====");
    }

    public static void levelCycle() {
        createMap();
        showLegend();
        spawnPlayer();
        spawnEnemy();
        spawnKey();
        isKeyTaken = false;
        spawnDoor();
        isDoorOpened = false;


        while (true) {
            showMap();
            changePositionPlayer();

            if (!isPlayerAlive()) {
                System.out.println(playerName + " погиб!");
                break;
            }

            if (isDoorOpened) {
                System.out.println(playerName + " прошел уровень!");
                break;
            }
        }
    }

    public static void createMap() {
        mapHeight = randomValue(mapValueMin, mapValueMax);
        mapWidth = randomValue(mapValueMin, mapValueMax);
        map = new char[mapHeight][mapWidth];
        invisibleMap = new char[mapHeight][mapWidth];

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                map[y][x] = mapEmpty;
            }
        }
        System.out.println("Создана карта размером " + mapWidth + "x" + mapHeight);

    }

    public static void showMap() {
        System.out.println("===== КАРТА =====");
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                System.out.print(map[y][x] + "│");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    public static void spawnPlayer() {
        playerPositionX = randomValue(0, mapWidth - 1);
        playerPositionY = randomValue(0, mapHeight - 1);
        map[playerPositionY][playerPositionX] = playerPoint;
        System.out.println(playerName + " появился в точке [" + (playerPositionX + 1) + ":" + (playerPositionY + 1) + "]. Здоровье: " + playerHealthPoint + " ♥");
    }

    public static void spawnEnemy() {
        enemyAttackPoint = randomValue(enemyValueMin, enemyValueMax);

        int countEnemies = (mapWidth + mapHeight) / 4; // random math logic

        int enemyPosX;
        int enemyPosY;

        for (int i = 0; i < countEnemies; i++) {

            do {
                enemyPosX = random.nextInt(mapWidth);
                enemyPosY = random.nextInt(mapHeight);
            } while (enemyPosX == playerPositionX && enemyPosY == playerPositionY);

            invisibleMap[enemyPosY][enemyPosX] = enemyPoint;
        }

        System.out.println("Создано " + countEnemies + " мин, наносящих по " + enemyAttackPoint + " урона");
    }

    public static void spawnKey() {
        int keyPosX;
        int keyPosY;

        do {
            keyPosX = random.nextInt(mapWidth);
            keyPosY = random.nextInt(mapHeight);
        } while (keyPosX == playerPositionX && keyPosY == playerPositionY &&
                invisibleMap[keyPosY][keyPosX] == enemyPoint);

        map[keyPosY][keyPosX] = keyPoint;

        System.out.println("Найдите ключ! Он открывает дверь на новый уровень!");
    }

    public static void spawnDoor() {
        int doorPosX;
        int doorPosY;

        do {
            doorPosX = random.nextInt(mapWidth);
            doorPosY = random.nextInt(mapHeight);
        } while (doorPosX == playerPositionX && doorPosY == playerPositionY &&
                invisibleMap[doorPosY][doorPosX] == enemyPoint &&
                map[doorPosY][doorPosX] == keyPoint);

        map[doorPosY][doorPosX] = doorPoint;
        doorPositionX = doorPosX;
        doorPositionY = doorPosY;
    }

    public static void changePositionPlayer() {
        int currentX = playerPositionX;
        int currentY = playerPositionY;

        int playerMove;

        do {
            System.out.print("Пожалуйста, введите направление шага (↑ = " + PLAYER_MOVE_UP +
                    ", ← = " + PLAYER_MOVE_LEFT +
                    ", → = " + PLAYER_MOVE_RIGHT +
                    ", ↓ = " + PLAYER_MOVE_DOWN + ") >>> ");

            playerMove = scanner.nextInt();

            switch (playerMove) {
                case PLAYER_MOVE_UP:
                    playerPositionY -= 1;
                    break;
                case PLAYER_MOVE_LEFT:
                    playerPositionX -= 1;
                    break;
                case PLAYER_MOVE_RIGHT:
                    playerPositionX += 1;
                    break;
                case PLAYER_MOVE_DOWN:
                    playerPositionY += 1;
                    break;
            }
        } while (!isValidPlayerStep(currentX, currentY, playerPositionX, playerPositionY));

        playerNextMoveAction(currentX, currentY, playerPositionX, playerPositionY);
    }

    public static void playerNextMoveAction(int currentX, int currentY, int nextX, int nextY) {
        // Наступил на мину
        if (invisibleMap[nextY][nextX] == enemyPoint) {
            playerHealthPoint -= enemyAttackPoint;
            System.out.println("Внимание! Враг нанёс урона: " + enemyAttackPoint + ". " + playerName + " имеет теперь " + playerHealthPoint + " ♥ здоровья");
            invisibleMap[nextY][nextX] = mapEmpty;
        }

        // Взятие ключа
        if (map[nextY][nextX] == keyPoint) {
            System.out.println("Вы нашли ключ! Теперь дверь на новый уровень можно открыть.");
            isKeyTaken = true;
        }

        // Вход в дверь
        if (map[nextY][nextX] == doorPoint) {
            if (!isKeyTaken) System.out.println("Дверь на следующий уровень закрыта. Найдите ключ!");
            else isDoorOpened = true;
        }



        map[currentY][currentX] = (!(map[currentY][currentX] == map[doorPositionY][doorPositionX])) ? mapReady : doorPoint;
        map[nextY][nextX] = playerPoint;

    }

    public static boolean isValidPlayerStep(int currentX, int currentY, int nextX, int nextY) {
        if (nextX >= 0 && nextX < mapWidth && nextY >= 0 && currentY < mapHeight) {
            System.out.println(playerName + " успешно походил на [" + (nextX + 1) + ":" + (nextY + 1) + "]!");
            return true;
        } else {
            playerPositionX = currentX;
            playerPositionY = currentY;
            System.out.println("Вы упёрлись в стену! Сделайте правильный ход.");
            return false;
        }
    }

    public static boolean isFullMap() {
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (map[y][x] == mapEmpty) return false;
            }
        }
        return true;
    }

    public static int randomValue(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public static boolean isPlayerAlive() {
        return playerHealthPoint > 0;
    }

    public static void showLegend() {
        System.out.println("Игрок: " + playerPoint);
        System.out.println("Ключ: " + keyPoint);
        System.out.println("Дверь: " + doorPoint);
        System.out.println("Остерегайтесь мин!");
    }
}
