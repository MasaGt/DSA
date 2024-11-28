/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author wjh2989
 */
public class BallGUI extends JPanel implements ActionListener{
    
    
    private JButton addBtn;
    private JButton addMoreBtn;
    private JButton killBtn;
    private DrawPanel dPanel;
//    private Ball ball;
    ArrayList<Ball> balls;
    private Timer timer;

    public BallGUI() {
        super(new BorderLayout());
        addBtn = new JButton("Add a ball");
        addBtn.addActionListener(this);
        
        killBtn = new JButton("Kill a ball");
        killBtn.addActionListener(this);
        
        addMoreBtn = new JButton("add 100 balls");
        addMoreBtn.addActionListener(this);
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(killBtn);
        btnPanel.add(addMoreBtn);
        
        dPanel = new DrawPanel();
        
        add(dPanel,BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        
        balls = new ArrayList<>();
        
        timer = new Timer(20, this);
        timer.start();
    }
    
    
    public static void main(String[] args) {
      JFrame frame = new JFrame("Main and Event Queue Thread Example");

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new BallGUI());
      frame.pack();
      // position the frame in the middle of the screen
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = frame.getSize();
      frame.setLocation((screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();
        if (src == addBtn) {
            System.out.println("add a ball");
            addBall();
            
        } else if (src == killBtn) {
            System.out.println("kill a ball");
            killBall();
            
        } else if (src == addMoreBtn) {
            System.out.println("add 10 balls");
            for (int i = 0; i < 100; i++) {
                addBall();
            }
        }
    }
    
    private void addBall() {
        Ball ball = new Ball();
        balls.add(ball);
            
        Thread th = new Thread(balls.get(balls.size()-1));
        th.start();
    }
    
    private void killBall() {
        if (!balls.isEmpty()) {
            Ball ball = balls.get(balls.size()-1);
            balls.remove(balls.size()-1);
            ball.die();
        }
    }

    private class DrawPanel extends JPanel{

        public DrawPanel() {
            setPreferredSize(new Dimension(500, 500));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs);
            Ball.WORLD_W = getWidth();
            Ball.WORLD_H = getHeight();
                    
            if (balls != null) {
                
                for (Ball b : balls) {
                    b.drawBall(grphcs);
                }
            }
            dPanel.repaint();
            
        }
    }
}
