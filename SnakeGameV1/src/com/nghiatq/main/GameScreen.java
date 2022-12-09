package com.nghiatq.main;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable{

    static int [][] gameBase = new int[20][20];

    Snake snake;

    static boolean isPlaying = false;

    static boolean text = true;

    Thread thread;

    static int level = 1;

    static int score = 0;

    static boolean gameOver = false;

    public GameScreen(){

        snake = new Snake();

        gameBase[10][10] = 2;

        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        long t = 0;
        long t2 = 0;

        while(true){

            if(System.currentTimeMillis()-t2>500){
                text=!text;
                t2 = System.currentTimeMillis();
            }

            if(isPlaying){
                if(System.currentTimeMillis()-t>200)
                    t = System.currentTimeMillis();
                snake.update();
            }

            repaint();
            try {
                thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
    }


    public void paintGameBase(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(401,0,180,400);
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
                g.fillRect(i*20,j*20,20,20);
                if(gameBase[i][j] == 2){
                    g.setColor(Color.red);
                    g.fillRect(i*20+1,j*20+1,18,18);
                    g.setColor(Color.BLACK);
                }
            }
    }

    @Override
    public void paint(Graphics g) {
        paintGameBase(g);
        snake.drawSnake(g);

        g.setColor(Color.white);
        g.setFont(getFont().deriveFont(20.0f));
        g.drawString("LEVEL:",410,30);
        g.drawString(" "+level,410,60);

        g.setFont(getFont().deriveFont(15.0f));
        g.drawString("SCORE: ",410,100);
        g.drawString(" "+score,410,130);

        if(!isPlaying){
            if(!gameOver){
                if(text){
                    g.setColor(Color.white);
                    g.setFont(getFont().deriveFont(18.0f));
                    g.drawString("PRESS SPACE TO PLAY!",80,200);
                }
            }
            else {
                if(text){
                    g.setColor(Color.white);
                    g.setFont(getFont().deriveFont(28.0f));
                    g.drawString("GAME OVER!",80,200);
                }
            }
        }

    }
}
