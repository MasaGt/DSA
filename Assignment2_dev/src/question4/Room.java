/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question4;

/**
 *
 * @author Masaomi
 */
public class Room {
    private boolean northDoorOpen;
    private boolean eastDoorOpen;
    private boolean southDoorOpen;
    private boolean westDoorOpen;

    /**
     * init the room
     * close all the doors
     */
    public Room() {
        northDoorOpen = false;
        eastDoorOpen = false;
        southDoorOpen = false;
        westDoorOpen = false;
    }
    
    /**
     * open the door according to the given direction
     * @param door direction of the door to open
     */
    public void openDoor(Direction door){
        switch (door) {
            case NORTH:
                northDoorOpen = true;
                break;
            case EAST:
                eastDoorOpen = true;
                break;
            case SOUTH:
                southDoorOpen = true;
                break;
            case WEST:
                westDoorOpen = true;
                break;
        }
    }
    
    /**
     * check if any of the doors is open
     * @return true is any of the door is open. otherwise, false
     */
    public boolean hasOpenDoor() {
        return (northDoorOpen || eastDoorOpen || southDoorOpen || westDoorOpen);
    }
    
    /**
     * check if the door of the given direction is open
     * @param door the direction of the door to check
     * @return true if the door is open. otherwise, false
     */
    public boolean isDoorOpen(Direction door) {
        boolean result = false;
        switch (door) {
            case NORTH:
                result = northDoorOpen;
                break;
            case EAST:
                result = eastDoorOpen;
                break;
            case SOUTH:
                result = southDoorOpen;
                break;
            case WEST:
                result = westDoorOpen;
                break;
        }
        return result;
    }
}
