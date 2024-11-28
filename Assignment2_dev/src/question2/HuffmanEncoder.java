/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Masaomi
 */
public class HuffmanEncoder {
    
    public static Map<Character, Integer> countCharacterInstances(char[] input) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        //sweep through the input and count each frequency.
        for (int i = 0; i < input.length; i++) {
            int frequency = frequencyMap.get(input[i]) == null ? 0 : frequencyMap.get(input[i]);
            frequencyMap.put(input[i], ++frequency);
        }
        return frequencyMap;
    }
    
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> map) {
        HeapADT<HuffmanNode> heap = new ArrayHeap();
        //Add each character and frequency as a Node to a priority queue.
        for (Character symbol : map.keySet()) {
            HuffmanNode node = new HuffmanNode(symbol, map.get(symbol));
            heap.add(node);
        }
        
        //build the Huffman Tree.
        while (heap.size() > 1) {
            HuffmanNode newNode = new HuffmanNode();
            HuffmanNode leftChild = heap.removeMin();
            HuffmanNode rightChild = heap.removeMin();
            newNode.attachLeftChild(leftChild);
            newNode.attachRightChild(rightChild);
            newNode.setFrequency(leftChild.getFrequency() + rightChild.getFrequency());
            heap.add(newNode);
        }
        //return the root node.
        return heap.removeMin();
    }
 
    //the entry point for extracting a code map.
    public static Map<Character, String> extractCodeMap(HuffmanNode root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        
        Map<Character, String> codeMap = new HashMap<>();
        innerExtractCodeMap(root, "", codeMap);
        
        return codeMap;
    }
    
    //recursive function for building a code map
    private static void innerExtractCodeMap(HuffmanNode node, String code, Map<Character, String> codeMap) {
        if (node.isLeaf()) {
            //when it reaches a leaf, put the leaf's symbol and its code to a code map
            codeMap.put(node.getSymbol(), code);
            return;
        }
        
        innerExtractCodeMap(node.getLeftChild(), code + "0", codeMap);
        innerExtractCodeMap(node.getRightChild(), code + "1", codeMap);
    }
    
    public static String huffmanEncode(String input, Map<Character, String> codeMap) {
        //convert user's input to huffman code
        String huffmanCode = "";
        //travers user's input
        for (char c : input.toCharArray()) {
            //build huffman code corresponding to each char of input.
            huffmanCode += codeMap.get(c);
        }
        return huffmanCode;
    }
    
    public static String huffmanDecode(HuffmanNode root, String code) {
        //decode huffman code to user's input
        String output = "";
        //starting node
        HuffmanNode rootNode = root;
        //travers the tree
        for (int i = 0; i < code.length();) {
            root = rootNode;
            while (!root.isLeaf()) {
                if ('0' == code.charAt(i)) {
                    //go left
                    root = root.getLeftChild();
                } else {
                    //go right
                    root = root.getRightChild();
                }
                //move a bit to the next one
                i++;
            }
            output += root.getSymbol();
        }
        return output;
    }
}
