/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Masaomi
 */
public class MazeGUI extends JPanel implements ActionListener  {
    private final DrawPanel drawPanel;
    private Maze maze;
    private final JButton stratBtn;
    private List<Mouse> mice;
    private final JPanel btnPanel;
    private final Timer timer;
    private Random rand;
    private final JPanel extraPanel;

    public MazeGUI() {
        super(new BorderLayout());
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        
        stratBtn = new JButton("Start Game");
        stratBtn.addActionListener(this);
        btnPanel = new JPanel();
        btnPanel.add(stratBtn);
        add(btnPanel, BorderLayout.SOUTH);
        
        extraPanel = new JPanel();
        extraPanel.add(new JLabel("blue: Keyboard Mouse, yellow: Random Mouse, pink: Smart Mouse"));
        add(extraPanel, BorderLayout.NORTH);
        
        mice = new ArrayList<>();
        rand = new Random();
        timer = new Timer(15, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (stratBtn == src) {
            if (timer.isRunning()) {
                timer.stop();
            }
            
            if (!mice.isEmpty()) {
                //clear old mice
                for (Mouse m : mice) {
                    m.requestStop();
                }
                mice.clear();
            }
            //create a new maze
            String vals = JOptionPane.showInputDialog("Enter dimension of maze as comma seperated values");
            String[] data = vals.split(",");
            if (!isValid(data)) {
                showDialog("please input numbers like this: 10,10");
            } else {
                //create new mice
                maze = new Maze(Integer.parseInt(data[0].trim()), Integer.parseInt(data[1].trim()));
                MazeMaker.createMazePaths(maze);
                Mouse smartM = new SmartMouse(maze, 500, rand.nextInt(maze.getNumRows()), 0);
                mice.add(smartM);
                Mouse keyBoardM = new KeyboardMouse(maze, 500, rand.nextInt(maze.getNumRows()), 0);
                mice.add(keyBoardM);
                Mouse randM = new RandomMouse(maze, 500, rand.nextInt(maze.getNumRows()), 0);
                mice.add(randM);
                for (Mouse m : mice) {
                    Thread th = new Thread(m);
                    th.start();
                }
                
                this.setFocusable(true);
                requestFocus();
                this.addKeyListener((KeyListener) keyBoardM);
            }
            timer.start();
        }
        drawPanel.repaint();
    }
    
    /**
     * check if passed input is valid or not
     * @param data
     * @return 
     */
    private boolean isValid(String[] data) {
        
        boolean isDigit = true;
        //the number of data check
        if (data != null && data.length >= 2) {
            //digit check
            for (int i = 0; i < 2; i++) {
                if (isDigit) {
                    String input = data[i].trim();
                    for (int j = 0; j < input.length(); j++) {
                        isDigit = Character.isDigit(input.charAt(j));
                        if (!isDigit) {
                            break;
                        }
                    }
                }
            }
        } else {
            isDigit = false;
        }
        
        return isDigit;
    }
    
    /**
     * show messagae dialog.
     * @param msg message
     */
    private void showDialog(String msg) {
        JLabel label = new JLabel(msg);
        JOptionPane.showMessageDialog(this, label);
    }
    
    private class DrawPanel extends JPanel {

            public DrawPanel() {
                super();
                super.setBackground(Color.WHITE);
//                super.setPreferredSize(new Dimension(600, 600));
                super.setPreferredSize(new Dimension(400, 400));
            }

            //draws the maze and draws the different mouses in the maze
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (maze != null) {
                    maze.draw(g, getWidth() - 10, getHeight() - 10);
                }
                
                if (mice!=null) {
                    for (Mouse mouse : mice) {
                        mouse.drawMouse(g, (getWidth() - 10)/maze.getNumCols(), (getHeight() - 10)/maze.getNumRows());
                    }
                }
            }
        }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Question4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MazeGUI());
        frame.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2,
                (screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
    }
}
