/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public abstract class Mouse implements Runnable{
    int row;
    int col;
    Maze maze;
    boolean stopRequested;
    int delay;
    int type;

    public Mouse(Maze maze, int delay, int startRow, int startCol) {
        this.maze = maze;
        this.delay = delay;
        this.row = startRow;
        this.col = startCol;
        stopRequested = false;
    }
    
    /**
     * getter for the x position of mouse
     * @return x(row) position
     */
    public int getRow() {
        return row;
    }
    
    /**
     * getter for the y position of mouse
     * @return x(col) position
     */
    public int getCol() {
        return col;
    }
    
    /**
     * the way of a mouse to move
     * each mouse has its own move
     */
    abstract void  move();

    @Override
    public void run() {
        while(!stopRequested) {
            move();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mouse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * make a mouse stop
     */
    public void requestStop() {
        stopRequested = true;
    }
    
    /**
     * draw a mouse so that it fits the corridor 
     * @param g
     * @param width width of a mouse
     * @param height height of a mouse 
     */
    public void drawMouse(Graphics g, int width, int height) {
        switch(type) {
            case 0:
                //KeyboardMouse
                g.setColor(Color.blue);
                break;
            case 1:
                //RandomMouse
                g.setColor(Color.yellow);
                break;
            case 2:
                //SmartMouse
                g.setColor(Color.pink);
                break;
        }
//        g.drawOval(row*height, col*width, width, height);
//        g.fillOval(row*height, row*width, width, height);
        g.fillOval(col*width, row*height, width, height);
    }
    
    /**
     * check if a mouse reaches goal or not
     * @return true if a mouse reaches goal. otherwise, false.
     */
    protected boolean hasReachedGoal() {
        boolean result = false;
        if (!maze.isInsideMaze(row, col)) {
            result = true;
        }
        
        return result;
    }
    
}
