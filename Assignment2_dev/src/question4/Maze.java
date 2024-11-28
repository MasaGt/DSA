/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question4;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Masaomi
 */
public class Maze {
    private int numRows;
    private int numCols;
    private Room[][] rooms;
//    public Room[][] rooms;
    private final int DEFAULT_SIZE = 5;
    
    /**
     * default initialization of maze
     */
    public Maze() {
        numRows = DEFAULT_SIZE;
        numCols = DEFAULT_SIZE;
        populateRooms(numRows,numCols);
    }

    /**
     * init maze with the given size
     * @param numRows the number of the rows in maze
     * @param numCols  the number of the columns in maze
     */
    public Maze(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        populateRooms(numRows,numCols);
    }
    
    /**
     * getter for the number of rows
     * @return the number of the rows in maze
     */
    public int getNumRows() {
        return numRows;
    }
    
    /**
     * getter for the number of columns
     * @return the number of the colmuns in maze
     */
    public int getNumCols() {
        return numCols;
    }
    
    /**
     * check if the door (specified by the parameter) is open or not 
     * @param row row number of the room
     * @param col col number of the room
     * @param direction direction of the door in the room
     * @return true if the door (given direction) in the room is open, otherwise false;
     */
    public boolean isOpen(int row, int col, Direction direction) {
        if (!isInsideMaze(row, col)) {
            throw new IllegalArgumentException();
        }
        return rooms[row][col].isDoorOpen(direction);
    }
    
    /**
     * check if any of the door is open or not
     * @param row
     * @param col
     * @return true if any of the doors in the room is open. Otherwise, false
     */
    public boolean hasOpenDoor(int row, int col) {
        if (!isInsideMaze(row, col)) {
            throw new IllegalArgumentException();
        }
        return rooms[row][col].hasOpenDoor();
    }
    
    /**
     * open the door (specified by the parameter) 
     * @param row row number of the room
     * @param col col number of the room
     * @param direction directin of the room
     */
    public void openDoor(int row, int col, Direction direction) {
        if (!isInsideMaze(row, col)) {
            throw new IllegalArgumentException();
        }
        rooms[row][col].openDoor(direction);
        
        int adjRow = row;     
        int adjCol = col;
        
        //open the door of a corresponding room to passed direction
        switch (direction) {
            case NORTH:
                adjRow--;
                if (isInsideMaze(adjRow, col)) {
                    rooms[adjRow][adjCol].openDoor(Direction.SOUTH);
                }
                break;
            case EAST:
                adjCol++;
                if (isInsideMaze(row, adjCol)) {
                    rooms[adjRow][adjCol].openDoor(Direction.WEST);
                }
                break;
            case WEST:
                adjCol--;
                if (isInsideMaze(row, adjCol)) {
                    rooms[adjRow][adjCol].openDoor(Direction.EAST);
                }
                break;
            case SOUTH:
                adjRow++;
                if (isInsideMaze(adjRow, col)) {
                    rooms[adjRow][adjCol].openDoor(Direction.NORTH);
                }
                break;
        }
    }
    
    /**
     * draw maze according to the given size
     * @param g
     * @param worldwidth width of the whole maze
     * @param worldheight height of the whole maze
     */
    public void draw(Graphics g, int worldwidth, int worldheight) {
        
        int roomHeight = worldheight/rooms.length;
        int roomWidth = worldheight/rooms[0].length;
        
        List<Direction> directionList = new ArrayList<>(4);
        for (Direction direction : Direction.values()) {
            directionList.add(direction);
        }
        //start position of the room
        int x = 0;
        int y = 0;
        
        //check all the rooms
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                Iterator<Direction> iterator = directionList.iterator();
                //check all directions of a room
                //if the door to each direction is not open. draw a door(line)
                while (iterator.hasNext()) {
                    Direction direction = iterator.next();
                    switch (direction) {
                        case NORTH:
                            if (!rooms[i][j].isDoorOpen(direction)) {
                                g.drawLine(x, y, x+roomWidth, y);
                            }
                            break;
                        case EAST:
                            if (!rooms[i][j].isDoorOpen(direction)) {
                                g.drawLine(x+roomWidth, y, x+roomWidth, y+roomHeight);
                            }
                            break;
                        case SOUTH:
                            if (!rooms[i][j].isDoorOpen(direction)) {
                                g.drawLine(x, y+roomHeight, x+roomWidth, y+roomHeight);
                            }
                            break;
                        case WEST:
                            if (!rooms[i][j].isDoorOpen(direction)) {
                                g.drawLine(x, y, x, y+roomHeight);
                            }
                            break;
                    }
                }
                x += roomWidth; 
            }
             y += roomHeight;
             x = 0;
        }
    }
    
    /**
     * check if the given row and col fits the maze.
     * @param row
     * @param col
     * @return true if the given col and row is within the maze. Otherwise, false
     */
    public boolean isInsideMaze(int row, int col) {
        boolean result = !(row < 0 || row >= numRows || col < 0 || col >= numCols);
        return result;
    }
    
    private void populateRooms(int rows, int cols) {
        rooms = new Room[numRows][numCols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rooms[i][j] = new Room();
            }
        }
    }
}
