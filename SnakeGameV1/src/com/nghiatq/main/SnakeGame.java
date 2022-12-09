package com.nghiatq.main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeGame extends JFrame {

    GameScreen game;


    public SnakeGame(){
        setSize(600,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        game = new GameScreen();
        add(game);

        this.addKeyListener(new handler());

        setVisible(true);

    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
    }

    private class handler implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                GameScreen.isPlaying=!GameScreen.isPlaying;
                if(GameScreen.gameOver){
                    GameScreen.gameOver = false;
                    game.snake.resetGame();
                }
            }

            if(e.getKeyCode() == KeyEvent.VK_UP){
                game.snake.setVector(Snake.GO_UP);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                game.snake.setVector(Snake.GO_DOWN);
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                game.snake.setVector(Snake.GO_LEFT);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                game.snake.setVector(Snake.GO_RIGHT);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
