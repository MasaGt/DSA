/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Masaomi
 */
public class LinkedBinaryTreeNode<E> implements MutableTreeNode {

    private E element;
    private MutableTreeNode parentNode;
    private MutableTreeNode rightNode;
    private MutableTreeNode leftNode;

    public LinkedBinaryTreeNode() {
        this(null);
    }

    public LinkedBinaryTreeNode(E element) {
        this.element = element;
        parentNode = null;
        rightNode = null;
        leftNode = null;

    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        //error cases
        if (child == null) {
            throw new IllegalArgumentException("null child");
        } else if (index == 0 && leftNode != null) {
            throw new IllegalArgumentException("left node already exists");
        } else if (index == 1 && rightNode != null) {
            throw new IllegalArgumentException("right node already exists");
        }

        //insert a node
        if (index == 0) {
            child.removeFromParent();
            leftNode = child;
            leftNode.setParent(this);
        } else if (index == 1) {
            rightNode = child;
            rightNode.setParent(this);
        }
    }

    @Override
    public void remove(int index) {
        if (index == 0 && leftNode != null) {
            remove(leftNode);
        } else if (index == 1 && rightNode != null) {
            remove(rightNode);
        }
    }

    @Override
    public void remove(MutableTreeNode node) {
        if (leftNode != null && leftNode == node) {
            leftNode = null;
            node.setParent(null);
        } else if (rightNode != null && rightNode == node) {
            rightNode = null;
            node.setParent(null);
        }
    }

    @Override
    public void setUserObject(Object object) {
        this.element = (E) object;
    }

    @Override
    public void removeFromParent() {
        if (this.parentNode != null) {
            //remove a referecne to this node from a parent node
            parentNode.remove(this);
            //remove parent node
            parentNode = null;
        }
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        if (this.parentNode != null) {
            //remove old parent Node
            removeFromParent();
            //set new parent node
            this.parentNode = newParent;
        }
    }

    @Override
    public TreeNode getChildAt(int childIndex) throws IndexOutOfBoundsException {
        List<MutableTreeNode> children = new ArrayList<>();
        if (leftNode != null) {
            children.add(leftNode);
        }
        if (rightNode != null) {
            children.add(rightNode);
        }
        
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        int childNum = 0;
        
        if (leftNode != null) {
            childNum++;
        }
        if (rightNode != null) {
            childNum++;
        }
        
        return childNum;
    }

    @Override
    public TreeNode getParent() {
        if (parentNode != null) {
            return parentNode;
        } else {
            throw new NoSuchElementException("this is the root node.");
        }
    }

    @Override
    public int getIndex(TreeNode node) {
        if (node==null){
            throw new IllegalArgumentException();
        }
        
        int foundAt = -1;
        Enumeration enumerator = this.children();
        int counter = 0;
        while(enumerator.hasMoreElements()) {
            if (enumerator.nextElement().equals(node)) {
                foundAt = counter;
            }
            counter++;
        }
        return foundAt;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return (rightNode == null && leftNode == null);
    }

    @Override
    public Enumeration children() {
        List<MutableTreeNode> children = new ArrayList<>();
        if (leftNode != null) {
            children.add(leftNode);
        }
        if (rightNode != null) {
            children.add(rightNode);
        }
        return (Enumeration<E>)
         (new Enumerator(children.iterator()));
    }

    @Override
    public String toString() {

        String output = "" + this.element;
        if (!isLeaf()) {
            output += "[ ";
            if (leftNode != null) {
                output += leftNode + " ";
            }

            if (rightNode != null) {
                output += rightNode;
            }
            output += "]";
        }

        return output;
    }

    public static void main(String[] args) {
        // create some sample nodes
        MutableTreeNode root = new LinkedBinaryTreeNode<>("A");
        MutableTreeNode nodeB = new LinkedBinaryTreeNode<>("B");
        MutableTreeNode nodeC = new LinkedBinaryTreeNode<>("C");
        MutableTreeNode nodeD = new LinkedBinaryTreeNode<>("D");
        MutableTreeNode nodeE = new LinkedBinaryTreeNode<>("E");
        MutableTreeNode nodeF = new LinkedBinaryTreeNode<>("F");
        // build the tree
        nodeB.insert(nodeD, 0);
        nodeB.insert(nodeE, 1);
        nodeC.insert(nodeF, 0);
        root.insert(nodeB, 0);
        root.insert(nodeC, 1);
        System.out.println("Original Tree: " + root);
        
        root.remove(nodeC);
        System.out.println("Modified Tree: " + root);
        
        System.out.println("node at index1 of root's children: " + root.getChildAt(0));
        System.out.println("The size of children of B: " + nodeB.getChildCount());
        
    }

    class Enumerator<E> implements Enumeration<E> {

        private Iterator<E> iterator;

        public Enumerator(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        public boolean hasMoreElements() {
            return iterator.hasNext();
        }

        public E nextElement() {
            return iterator.next();
        }
    }
}
