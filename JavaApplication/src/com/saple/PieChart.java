/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saple;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Masaomi
 */
public class PieChart extends JFrame {

    public PieChart() {
        add(new DrawPanel());
    }

    public static void main(String[] A00) {
        JFrame L00 = new PieChart();
        System.out.println("afetr instansiation of panel");
        L00.setTitle("円グラフ");
        L00.setDefaultCloseOperation(EXIT_ON_CLOSE);
        L00.setBackground(Color.white);
        L00.pack();
        System.out.println("afetr pack");
        L00.setResizable(false);
        System.out.println("after resize");
        L00.setResizable(true);
        L00.setVisible(true);
        System.out.println("afetr visible");
    }
}
