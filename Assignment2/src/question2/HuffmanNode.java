/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

/**
 *
 * @author Masaomi
 */
public class HuffmanNode implements Comparable<HuffmanNode>{
    
    private Character symbol;
    private int frequency;
    private HuffmanNode leftChild;
    private HuffmanNode rightChild;

    public HuffmanNode() {
        symbol = null;
        frequency = 0;
        leftChild = null;
        rightChild = null;
    }

    public HuffmanNode(Character symbol, int frequency) {
        this();
        this.symbol = symbol;
        this.frequency = frequency;
    }
    
    public HuffmanNode(Character symbol) {
        this();
        this.symbol = symbol;
    }

    /**
     * @return the symbol
     */
    public Character getSymbol() {
        return symbol;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    public boolean isLeaf() {
        return (rightChild == null & leftChild == null);
    }
    
    public boolean detachLeftChild() {
        if (leftChild == null) {
            return false;
        } else {
            leftChild = null;
            return true;
        }
    }
    
    public boolean detachRightChild() {
        if (rightChild == null) {
            return false;
        } else {
            rightChild = null;
            return true;
        }
    }
    
    public boolean attachLeftChild(HuffmanNode child) {
        if (leftChild != null) {
            return false;
        } else {
            leftChild = child;
            return true;
        }
    }
    
    public boolean attachRightChild(HuffmanNode child) {
        if (rightChild != null) {
            return false;
        } else {
            rightChild = child;
            return true;
        }
    }
    
    public HuffmanNode getLeftChild() {
        return leftChild;
    }
    
    public HuffmanNode getRightChild() {
        return rightChild;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        int result = frequency - o.getFrequency();
        if (result == 0) {
            if (symbol != null && o.getSymbol() != null) {
                result = symbol.compareTo(o.getSymbol());
            } else {
                return 0;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "symbol; " + symbol + ", freq: " + frequency;
    }
}
