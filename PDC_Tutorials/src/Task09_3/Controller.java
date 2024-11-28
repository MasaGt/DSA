/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task09_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Masaomi
 */
public class Controller implements ActionListener {

    private Model m;
    private View v;

    public Controller(View view, Model model) {
        this.v = view;
        this.m = model;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Log in":
                System.out.println("You clicked the login button");
                String name = v.getName();
                String pass = v.getPassword();
                m.checkName(name, pass);
                break;
            case "Next":
                System.out.println("You clicked the next button");
                m.checkAnswer(v.getCalcSolution());
                break;
            case "Quit":
                System.out.println("You clicked the quit button");
                m.quitGame();
                break;
        }

    }

}
