package com.nghiatq.main;

import java.awt.*;
import java.util.Random;

public class Snake {

    int dSnakeBody = 1;
    int[] xSnake;
    int[] ySnake;

    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;

    int vector = Snake.GO_DOWN;

    long t1 = 0;

    int speed = 200;

    int maxLength = 5;

    static boolean updateChangeVector = true;

    public Snake() {
        xSnake = new int[20];
        ySnake = new int[20];

        xSnake[0] = 5;
        ySnake[0] = 5;

    }

    public void resetGame() {
        xSnake = new int[100];
        ySnake = new int[100];

        xSnake[0] = 5;
        ySnake[0] = 5;

        dSnakeBody = 1;
    }

    public void setVector(int v) {
        if (vector != -v && updateChangeVector == true) {
            vector = v;
            updateChangeVector = false;
        }
    }

    public boolean pointPosition(int x, int y) {
        for (int i = 0; i < dSnakeBody; i++) {
            if (xSnake[i] == x && ySnake[i] == y) {
                return true;
            }
        }
        return false;
    }

    public Point randomPointPosition() {
        Random r = new Random();

        int x;
        int y;

        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        } while (pointPosition(x, y));

        return new Point(x, y);
    }

    public void update() {

        if (dSnakeBody == maxLength) {
            GameScreen.isPlaying = false;
            resetGame();
            GameScreen.level++;
            speed *= 0.9;
            maxLength += 5;
        }

        for (int i = 1; i < dSnakeBody; i++) {
            if (xSnake[0] == xSnake[i] && ySnake[0] == ySnake[i]) {
                GameScreen.isPlaying = false;
                GameScreen.gameOver = true;
                GameScreen.level = 1;
                GameScreen.score = 0;
                speed = 200;
                maxLength =5;
            }
        }


        if (System.currentTimeMillis() - t1 > speed) {

            updateChangeVector = true;

            if (GameScreen.gameBase[xSnake[0]][ySnake[0]] == 2) {
                dSnakeBody++;
                GameScreen.score+=100;
                GameScreen.gameBase[xSnake[0]][ySnake[0]] = 0;
                GameScreen.gameBase[randomPointPosition().x][randomPointPosition().y] = 2;

            }

            for (int i = dSnakeBody - 1; i > 0; i--) {
                xSnake[i] = xSnake[i - 1];
                ySnake[i] = ySnake[i - 1];
            }

            if (vector == Snake.GO_UP) ySnake[0]--;
            if (vector == Snake.GO_DOWN) ySnake[0]++;
            if (vector == Snake.GO_LEFT) xSnake[0]--;
            if (vector == Snake.GO_RIGHT) xSnake[0]++;

            if (xSnake[0] < 0) xSnake[0] = 19;
            if (xSnake[0] > 19) xSnake[0] = 0;
            if (ySnake[0] < 0) ySnake[0] = 19;
            if (ySnake[0] > 19) ySnake[0] = 0;

            t1 = System.currentTimeMillis();
        }
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < dSnakeBody; i++)
            g.fillRect(xSnake[i] * 20 + 1, ySnake[i] * 20 + 1, 18, 18);
    }
}
