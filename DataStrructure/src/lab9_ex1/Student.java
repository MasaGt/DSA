/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9_ex1;

/**
 *
 * @author Masaomi
 */
public class Student {
    
    private String name;
    private int age;
    private String address;

    public Student(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int hash = getName().charAt(0);
//        int hash = name.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Student) {
            Student target = (Student) obj;
            return target.getName().equals(name);
        }
        return false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
}
