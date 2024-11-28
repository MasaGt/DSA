/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 *
 * @author Masaomi
 */
public class KeyboardMouse extends Mouse implements KeyListener{
    
    private int keycode;

    public KeyboardMouse(Maze maze, int delay, int startRow, int startCol) {
        super(maze, delay, startRow, startCol);
        Random rand = new Random();
        type = 0;
    }

    /**
     * move according to user input
     */
    @Override
    void move() {
        Direction direction = null;
        int adjRow = row;
        int adjCol = col;
        
        switch(keycode) {
            case KeyEvent.VK_UP:
                //move to North
                adjRow--;
                direction = Direction.NORTH;
                break;
            case KeyEvent.VK_DOWN:
                //move to South
                adjRow++;
                direction = Direction.SOUTH;
                break;
            case KeyEvent.VK_RIGHT:
                //move to East
                adjCol++;
                direction = Direction.EAST;
                break;
            case KeyEvent.VK_LEFT:
                //move to West
                adjCol--;
                direction = Direction.WEST;
                break;
            default:
                //when other key is typed, mouse does't move
                break;
        }
        
        if (direction != null) {
            if (maze.isOpen(row, col, direction)){
                //update the position
                row = adjRow;
                col = adjCol;
                
                //goal check
                if (hasReachedGoal()) {
                    requestStop();
                }
            }
        }
        keycode = KeyEvent.KEY_RELEASED;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do notthing when key is typed
        //System.out.println("key typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("keypressed");
        keycode = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //do nothing when key is released
        //System.out.println("key typed");
    }
    
}
