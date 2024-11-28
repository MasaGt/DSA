/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1_3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author wjh2989
 */
public class Ball implements Runnable{

    private int size;
    private Color col;
    //position
    private int x,y;
    //directions
    private int dx,dy;
    private boolean isAlive;
    public static int WORLD_W, WORLD_H;
    private static Random random = new Random();

    public Ball() {
        
        size = random.nextInt(100) + 20;
//        x = WORLD_W/2;
//        y = WORLD_H/2;
        x = random.nextInt(WORLD_W - size);
        y = random.nextInt(WORLD_H - size);
        dx = 2;
        dy = - 1;
        int r = random.nextInt(256);
        int b = random.nextInt(256);
        int g = random.nextInt(256);
        col = new Color(r, g, b);
    }
    
    @Override
    public void run() {
        isAlive = true;
        while (isAlive) {
            x += dx;
            y += dy;
            bounce();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
        this.col = new Color(255,255,255, 0);
    }
    
    public void drawBall(Graphics g) {
        g.setColor(col);
        g.fillOval(x, y, size, size);
    }
    
    /**
     * When a ball reached the wall, bounce it
     */
    private void bounce() {
       if (x+size > WORLD_W) {
           this.x = WORLD_W - size;
           this.dx *= -1;
       } 
       if (x < 0) {
           this.x = 0;
           this.dx *= -1;
       }
       if (y+size > WORLD_H) {
           this.y = WORLD_H - size;
           this.dy *= -1;
       }
       if (y < 0) {
           this.y = 0;
           this.dy *= -1;
       }
    }
    
    public void die() {
        this.isAlive = false;
    }
    
}
