/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task09_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Masaomi
 */
public class View extends JFrame implements Observer{

    private JPanel userPanel = new JPanel();
    private JPanel calcPanel = new JPanel();
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    private JTextField unInput = new JTextField(10);
    private JTextField pwInput = new JTextField(10);
    private JLabel wrongName = new JLabel("Wrong username or passwork!");

    private JLabel firstNumber = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel additionLabel = new JLabel("+");
    //private JTextField secondNumber = new JTextField(10);
    private JButton nextButton = new JButton("Next");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");

    private JTextField calcSolution = new JTextField(10);
    
    private boolean started = false;

    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        userPanel.add(uName);
        userPanel.add(unInput);
        userPanel.add(pWord);
        userPanel.add(pwInput);
        userPanel.add(loginButton);
        this.add(userPanel);
        this.setVisible(true);

    }
    
    void startQuiz() {

        calcPanel.add(firstNumber);
        calcPanel.add(additionLabel);
        calcPanel.add(secondNumber);

        calcPanel.add(calcSolution);
        calcPanel.add(nextButton);
        calcPanel.add(quitButton);
        this.getContentPane().removeAll();
        calcPanel.setVisible(true);
        this.add(calcPanel);
        this.revalidate();
        this.repaint();

    }

    /**
     * @return the uName
     */
    public String getName() {
        return unInput.getText();
    }

    /**
     * @return the pWord
     */
    public String getPassword() {
        return pwInput.getText();
    }

    public void addController(Controller controller) {
        loginButton.addActionListener(controller);
        nextButton.addActionListener(controller);
        quitButton.addActionListener(controller);
    }

    /**
     * @return the calcSolution
     */
    public String getCalcSolution() {
        return calcSolution.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        Data data = (Data) arg;
        if (!data.loginFlg) {
            this.unInput.setText("");
            this.pwInput.setText("");
        } else if (!this.started){
            this.started = true;
            this.setQuestion(data.num1, data.num2);
            this.startQuiz();
        } else if (data.quitFlg) {
            System.out.println(data.currentScore);
            JPanel quitPanel = new JPanel();
            JLabel scoreLabel = new JLabel("Your score: " + data.currentScore);
            quitPanel.add(scoreLabel);
            this.getContentPane().removeAll();
            calcPanel.setVisible(true);
            this.add(quitPanel);
            this.revalidate();
            this.repaint();
        } else {
            this.setQuestion(data.num1, data.num2);
        }
    }
    
    public void setQuestion(int num1, int num2) {
        firstNumber.setText(num1 + "");
        secondNumber.setText(num2 + "=");
        calcSolution.setText("");
        calcPanel.repaint();
    }

}
