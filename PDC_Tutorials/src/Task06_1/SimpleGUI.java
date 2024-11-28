package Task06_1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class SimpleGUI extends JFrame{
    
    /**
     * Construct Simple GUI class
     * Init JFrame and each compoents.
     */
    public SimpleGUI(){
        //prepare components
        JLabel lable = new JLabel("");
        JTextField textField = new JTextField("", 20);
        JButton button = new JButton("UPDATE");
        
         button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                lable.setText(input);
            }
        });
        
        //create panels
        BGPanel bgPanel = new BGPanel();
        JPanel inputPanel =  new JPanel();
        
        //sets components to panels
        bgPanel.add(lable);
        inputPanel.add(textField);
        inputPanel.add(button);
        
        //set panels to frame
        this.add(bgPanel, BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);
        
        //other configurations of this frame
        this.setSize(400, 400);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    //just new this class and set visibility "true
    public static void main(String[] args) {
        JFrame frame = new SimpleGUI();
        frame.setVisible(true);
    }
}
