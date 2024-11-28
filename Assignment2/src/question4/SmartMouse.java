/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * A Mouse move by following depth-first search
 * @author Masaomi
 */
public class SmartMouse extends Mouse{
    
    Set<VisitedRoom> visitedRooms;
    Deque<VisitedRoom> roomStack;

    public SmartMouse(Maze maze, int delay, int startRow, int startCol) {
        super(maze, delay, startRow, startCol);
        type = 2;
        visitedRooms = new HashSet<>();
        roomStack = new ArrayDeque<>();
        roomStack.push(new VisitedRoom(row, col));
    }

    /**
     * move the maze by following depth-first search(stack base)
     */
    @Override
    void move() {
        
        //make the current room visited
        visitedRooms.add(new VisitedRoom(row, col));
        
        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++) {
            Direction direction = directions[i];
            int adjRow = row, adjCol = col;
            switch (direction) {
                case NORTH:
                    adjRow--;
                    break;
                case EAST:
                    adjCol++;
                    break;
                case SOUTH:
                    adjRow++;
                    break;
                case WEST:
                    adjCol--;
                    break;
            }

            if (maze.isOpen(row, col, direction) && !visitedRooms.contains(new VisitedRoom(adjRow, adjCol))) {
                //if there is an unvisited room
                this.row = adjRow;
                this.col = adjCol;
                roomStack.push(new VisitedRoom(row, col));
                break;
            } else if (i == directions.length - 1) {
                //no where to go. then, move back to the previous room
                roomStack.pop();
                this.row = roomStack.peek().row;
                this.col = roomStack.peek().col;
            }
        }
        
        //goal check
        if (hasReachedGoal()) {
            requestStop();
        }   
    }
    
    /**
     * visited room class for depth-first serach
     */
    private class VisitedRoom {

        private int row;
        private int col;
        
        public VisitedRoom(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj instanceof VisitedRoom) {
                if (this.row == ((VisitedRoom) obj).row && this.col == ((VisitedRoom) obj).col) {
                    result = true;
                }
            }
            return result; 
        }

        /**
         * reutrn unique hashCode so that contains works properly
         * @return 
         */
        @Override
        public int hashCode() {
            return 31 * 11 + row + 17 * 11  + col;
            //when I used super.hashCode, contains method did not work properly
//            return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
