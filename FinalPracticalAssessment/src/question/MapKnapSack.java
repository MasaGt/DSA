/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Masaomi
 */
public class MapKnapSack<E> implements KnapSack<E>{
    
    private Map<E, Integer> itemMap;
    private int totalItems;
    private int maxNum;

    public MapKnapSack() {
        this(20);
    }

    public MapKnapSack(int maxNum) {
        this.itemMap = new HashMap<>();
        totalItems = 0;
        this.maxNum = maxNum;
    }
    
    
    
    @Override
    public boolean add(E element, int amount) {
        
        if ((totalItems + amount) > maxNum) {
            return false;
        }
        Integer storedNum = itemMap.get(element);

        if (null == storedNum) {
            storedNum = amount;
        } else {
            storedNum += amount;
        }
        
        itemMap.put(element, storedNum);
        totalItems += amount;
        return true;
    }

    @Override
    public boolean add(E element) {
        if (totalItems >= maxNum) {
            return false;
        }
        if (itemMap.containsKey(element)) {
            int storedNum = itemMap.get(element);
            itemMap.put(element, ++storedNum);
        } else {
            itemMap.put(element, 1);
        }
        
        totalItems++;
        return true;
    }

    @Override
    public boolean removeAll(E element) {
        if (itemMap != null && !itemMap.containsKey(element)){
            return false;
        } 
        
        int removeNum = itemMap.get(element);
        itemMap.remove(element);
        totalItems -= removeNum;
        
        return true;
    }

    @Override
    public boolean remove(E element, int amount) {
        if (itemMap != null && !itemMap.containsKey(element)) {
            return false;
        }
        
        int numStored = itemMap.get(element);
        if (numStored - amount <= 0) {
            //remove all;
            removeAll(element);
        } else {
            //remove an item by a specifiedd amount.
            itemMap.put(element, numStored - amount);
            totalItems = totalItems - amount;
        }
        
        return true;
    }

    @Override
    public boolean contains(E element) {
        return itemMap.containsKey(element);
    }

    @Override
    public E grab() {
        Random rand = new Random();
        int randIndex = rand.nextInt(itemMap.size()+1);
        int count = 0;
        E elem = null;
        for (E key : itemMap.keySet()) {   
            if (count >= randIndex) {
                elem = key;
                break;
            }
            count++;
        }
        return elem;
    }

    @Override
    public int numberUniqueItems() {
        return itemMap.entrySet().size();
    }

    @Override
    public int currentQuantity() {
        return totalItems;
    }

    @Override
    public String toString() {
        String output = "[ ";
        for (E key : itemMap.keySet()) {
            output += key.toString() + "=" + itemMap.get(key) + ", ";
        }
        return output + " ]";
    }
    
    
}
