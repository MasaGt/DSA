/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question3;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Masaomi
 */
public class Test {
    
    public static void main(String[] args) {
        Set<String> set = new HashSetWithChaining<>(6, 0.75f);
        
        //isEmpty
        System.out.println("----------isEmpty test----------");
        System.out.println("is the set emplty?" + set.isEmpty());
        System.out.println();
        
        //add some elements
        set.add("Seth");
        set.add("Bob");
        set.add("Ian");
        set.add("Adam");
        
        System.out.println("----------add test----------");
        System.out.println("Creating Set initial capacity=6.. Adding Seth, Bob. Ian, Adam");
        System.out.println(set);
        System.out.println("Size is: " + set.size());
        System.out.println();
        
        //isEmplty
        System.out.println("----------isEmpty test----------");
        System.out.println("is the set empty?" + set.isEmpty());
        System.out.println();
        
        //add more elements so that hashTable expands
        System.out.println("----------add and expand capacity test----------");
        System.out.println("Adding Jill, Amy, Nat, Seth, Bob, Andy, Simon");
        set.add("Jill");
        set.add("Amy");
        set.add("Nat");
        set.add("Seth");
        set.add("Bob");
        set.add("Andy");
        set.add("Simon");
        System.out.println("Size is: " + set.size());
        System.out.println(set);
        System.out.println();
        
        //contains
        System.out.println("----------contains test----------");
        System.out.println("Contains Seth?->" + set.contains("Seth") + " / Contains Nat?->" + set.contains("Nat") + " / Contains Gary?->" + set.contains("Gary"));
        System.out.println();
        
        
        //containsAll
        System.out.println("----------containsAll test----------");
        System.out.println("case1: create another set{Amy,Andy}");
        Set<String> anotherSet = new HashSetWithChaining<>(6);
        anotherSet.add("Amy");
        anotherSet.add("Andy");
        System.out.println("(should be true)the set contains all of {Amy, Andy}?->" + set.containsAll(anotherSet));
        
        System.out.println("case2: create another set{Amy,Andy, Sample1}");
        anotherSet.add("Amy");
        anotherSet.add("Sample1");
        anotherSet.add("Andy");
        System.out.println("(should be false)the set contains all of {Amy, Andy, Sample1}?->" + set.containsAll(anotherSet));
        System.out.println();
        
        //iterating
        System.out.println("----------iterator test----------");
        System.out.println("iteraing");
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        System.out.println();
        
        
        //remove
        System.out.println("----------remove test----------");
        System.out.println("Remove Simon, Ian, Jill, and Gary");
        boolean removeRst1 = set.remove("Simon");
        boolean removeRst2 = set.remove("Ian");
        boolean removeRst3 = set.remove("Jill");
        boolean removeRst4 = set.remove("Gary");
        System.out.println("Size is: " + set.size());
        System.out.println(set);
        System.out.println("Is Simon removed?->" + removeRst1 + " / Is Ian removed?->" + removeRst2 + " / Is Jillremoved?->" + removeRst3 + " / Is Gary removed?->" + removeRst4);
        System.out.println();
        
        //removeAll
        System.out.println("----------removeAll test----------");
        System.out.println("Create a new set {Adam, Hero, Nat} that shold be removed from set");
        Set<String> removeTarget = new HashSetWithChaining<>(6);
        removeTarget.add("Adam");
        removeTarget.add("Hero");
        removeTarget.add("Nat");
        boolean removeAllRst = set.removeAll(removeTarget);
        System.out.println("Is removeAll successfull?->" + removeAllRst);
        System.out.println("Size is: " + set.size());
        System.out.println(set);
        
        System.out.println("Create a new set {Hero, Villain, Others} that shold be removed from set");
        removeTarget = new HashSetWithChaining<>(6);
        removeTarget.add("Hero");
        removeTarget.add("Villain");
        removeTarget.add("Others");
        removeAllRst = set.removeAll(removeTarget);
        System.out.println("Is removeAll successfull?->" + removeAllRst);
        System.out.println("Size is: " + set.size());
        System.out.println(set);
        System.out.println();
        
        
        //clear
        System.out.println("----------clear test----------");
        System.out.println("target");
        System.out.println("Size is: " + set.size());
        System.out.println(removeTarget);
        System.out.println("*****clear*****");
        removeTarget.clear();
        System.out.println("Size is: " + removeTarget.size());
        System.out.println(removeTarget);
        
        //retainAll
        System.out.println("----------retainAll test----------");
        System.out.println("Add Simon, Adam, Nat, Jill, and Ian again");
        set.add("Simon");
        set.add("Adam");
        set.add("Nat");
        set.add("Jill");
        set.add("Ian");
        System.out.println("Size is: " + set.size());
        System.out.println(set);
        
        System.out.println("retain all the elements (nothing removed and the result should be false)");
        boolean retainRslt = set.retainAll(set);
        System.out.println("Was retainAll successfull?->" + retainRslt);
        System.out.println("Size is: " + set.size());
        System.out.println(set);
        
        System.out.println("retain only {Seth, Adam, Bob}. The other elements should be removed and the result should be true");
        Set<String> retainTarget = new HashSetWithChaining<>(6);
        retainTarget.add("Seth");
        retainTarget.add("Adam");
        retainTarget.add("Bob");
        retainRslt = set.retainAll(retainTarget);
        System.out.println("Was retainAll successfull?->" + retainRslt);
        System.out.println("Size is: " + set.size());
        System.out.println(set);
        
        //toArray test
        System.out.println("----------toArray test----------");
        Object[] arr = set.toArray();
        printArray(arr);
        System.out.println();
        
        //toArray(T[]) test
        System.out.println("----------toArray(T[]) test----------");
        System.out.println("case1: pass an array that shorter than the size of the set");
        String[] passedArr = new String[set.size() - 1];
        String[] resultArr = set.toArray(passedArr);
        set.toArray(passedArr);
        System.out.println("print passed array (this should be filled with null)");
        printArray(passedArr);
        System.out.println("print returned array (this conatina all the elements of the set)");
        printArray(resultArr);
        System.out.println();
        
        System.out.println("case2: pass an array whose length is equal to the size of the set");
        passedArr = new String[set.size()];
        resultArr = set.toArray(passedArr);
        set.toArray(passedArr);
        System.out.println("print passed array (this conatina all the elements of the set)");
        printArray(passedArr);
        System.out.println("print returned array (this conatina all the elements of the set)");
        printArray(resultArr);
        System.out.println();
        
        
        System.out.println("case3: pass an array that longer than the size of the set");
        passedArr = new String[set.size() + 2];
        resultArr = set.toArray(passedArr);
        set.toArray(passedArr);
        System.out.println("print passed array (this should contains all the elements of the set and null)");
        printArray(passedArr);
        System.out.println("print returned array (this should contains all the elements of the set and null)");
        printArray(resultArr);
        System.out.println();
        
        //null test
        System.out.println("----------add null----------");
        set.add(null);
        System.out.println(set);
        System.out.println("set contains null?->" + set.contains(null));
        
        System.out.println("retain null");
        retainTarget = new HashSetWithChaining<>(6);
        retainTarget.add(null);
        
        set.retainAll(retainTarget);
        System.out.println(set);
        System.out.println("remove null");
        set.remove(null);
        System.out.println(set);
        
        
        
    }
    
    private static void printArray(Object[] ar) {
        System.out.print("[ ");
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] != null) {
                System.out.print(ar[i].toString());
            } else {
                System.out.print("null");
            }
            
            if (i != ar.length-1) {
                System.out.print(", ");
            }
            
        }
        System.out.println(" ]");
    }
}
