/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question4;

import java.util.Random;

/**
 *
 * @author Masaomi
 */
public class RandomMouse extends Mouse{

    private Random rand;
    public RandomMouse(Maze maze, int delay, int startRow, int startCol) {
        super(maze, delay, startRow, startCol);
        this.rand = new Random();
        type = 1;
    }

    @Override
    void move() {
        int directionInt = rand.nextInt(4);
        Direction direction = null;
        int adjRow = row;
        int adjCol = col;
        switch(directionInt) {
            case 0:
                //move to North
                adjRow--;
                direction = Direction.NORTH;
                break;
            case 1:
                //move to South
                adjRow++;
                direction = Direction.SOUTH;
                break;
            case 2:
                //move to East
                adjCol++;
                direction = Direction.EAST;
                break;
            case 3:
                //move to West
                adjCol--;
                direction = Direction.WEST;
                break;
        }
        
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
    
}
