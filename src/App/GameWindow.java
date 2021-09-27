package App;

import App.model.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 02.09.2021
 */

public class GameWindow extends JFrame {

    private int winWidth = 1024;
    private int winHeight = 768;
    private int winPosX = 300;
    private int winPosY = 100;

    private GameMap map;
    private JPanel gui;

    private JPanel gameControlDiv;
    private JButton btnStartGame;
    private JButton btnExitGame;

    private JPanel gameInfoDiv;
    private JLabel countLevelInfo;
    private JLabel mapSizeInfo;
    private JLabel countEnemyInfo;

    private JPanel playerInfoDiv;
    private JLabel playerHealthInfo;
    private JLabel playerAtkPointInfo;
    private JLabel playerCoordinatesInfo;

    private JPanel playerControllerDiv;
    private JButton playerMoveUp;
    private JButton playerMoveLeft;
    private JButton playerMoveRight;
    private JButton playerMoveDown;

    private JScrollPane scrollPanel;
    private JTextArea gameLog;

    GameWindow() {
        setupWindow();

        map = new GameMap(this);

        setupGui();

        add(gui, BorderLayout.EAST);
        add(map);


        setVisible(true);
    }

    private void setupGui() {
        gui = new JPanel();
        gui.setLayout(new GridLayout(5, 1));

        setupGameControl();
        setupGameInfo();
        setupPlayerInfo();
        setupPlayerController();
        setupLog();

        gui.add(gameControlDiv);
        gui.add(gameInfoDiv);
        gui.add(playerInfoDiv);
        gui.add(playerControllerDiv);
        gui.add(scrollPanel, BorderLayout.SOUTH);
    }

    private void setupGameControl() {
        gameControlDiv = new JPanel();
        gameControlDiv.setLayout(new GridLayout(3, 1));

        btnStartGame = new JButton("<html><i>Start Game</i></html>");
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.startGame();
                recordLog("Start Game");
            }
        });

        btnExitGame = new JButton("<html><i>Exit Game</i></html>");
        btnExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gameControlDiv.add(new JLabel("== Game Menu =="));
        gameControlDiv.add(btnStartGame);
        gameControlDiv.add(btnExitGame);
    }

    private void setupGameInfo() {
        gameInfoDiv = new JPanel();
        gameInfoDiv.setLayout(new GridLayout(4, 1));

        countLevelInfo = new JLabel("Level: -");
        mapSizeInfo = new JLabel("Map size: -:-");
        countEnemyInfo = new JLabel("Enemies: -");

        gameInfoDiv.add(new JLabel("== Game Info =="));
        gameInfoDiv.add(countLevelInfo);
        gameInfoDiv.add(mapSizeInfo);
        gameInfoDiv.add(countEnemyInfo);

    }

    private void setupPlayerInfo() {
        playerInfoDiv = new JPanel();
        playerInfoDiv.setLayout(new GridLayout(4, 1));

        playerHealthInfo = new JLabel("Health: -");
        playerAtkPointInfo = new JLabel("Attack: -");
        playerCoordinatesInfo = new JLabel("Coord.: -:-");

        playerInfoDiv.add(new JLabel("== Player Info =="));
        playerInfoDiv.add(playerHealthInfo);
        playerInfoDiv.add(playerAtkPointInfo);
        playerInfoDiv.add(playerCoordinatesInfo);
    }

    private void setupPlayerController() {
        playerControllerDiv = new JPanel();
        playerControllerDiv.setLayout(new GridLayout(3, 3));

        playerMoveUp = new JButton("\uD83E\uDC45");
        playerMoveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.update(GameMap.DIRECTION_MOVE_UP);
            }
        });

        playerMoveLeft = new JButton("\uD83E\uDC44");
        playerMoveLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.update(GameMap.DIRECTION_MOVE_LEFT);
            }
        });


        playerMoveRight = new JButton("\uD83E\uDC46");
        playerMoveRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.update(GameMap.DIRECTION_MOVE_RIGHT);
            }
        });


        playerMoveDown = new JButton("\uD83E\uDC47");
        playerMoveDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.update(GameMap.DIRECTION_MOVE_DOWN);
            }
        });

        playerControllerDiv.add(new JPanel());
        playerControllerDiv.add(playerMoveUp);
        playerControllerDiv.add(new JPanel());
        playerControllerDiv.add(playerMoveLeft);
        playerControllerDiv.add(new JPanel());
        playerControllerDiv.add(playerMoveRight);
        playerControllerDiv.add(new JPanel());
        playerControllerDiv.add(playerMoveDown);
        playerControllerDiv.add(new JPanel());
    }

    private void setupLog() {
        gameLog = new JTextArea();
        scrollPanel = new JScrollPane(gameLog);
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
    }

    void recordLog(String msg) {
        gameLog.append(msg + "\n");
    }


    private void setupWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(winPosX, winPosY);
        setSize(winWidth, winHeight);
        setTitle("This is my Game");
        setResizable(true);
    }

    void refreshGameInfo() {
        countLevelInfo.setText("Level: " + map.getLevelCount());
        mapSizeInfo.setText("Map size: " + map.getMapSize());
        countEnemyInfo.setText("Enemies: " + Enemy.count);
        playerHealthInfo.setText("Health: " + map.getPlayer().getHealth());
        playerAtkPointInfo.setText("Attack: " + map.getPlayer().getAttackPoint());
        playerCoordinatesInfo.setText("Coord.: " + map.getPlayer().getCoordinatesInfo());
    }

}
