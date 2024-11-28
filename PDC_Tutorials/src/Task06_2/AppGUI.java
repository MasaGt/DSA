/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task06_2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Masaomi
 */
public class AppGUI extends JFrame implements ActionListener{

    private JLabel bgLabel;
    private JComboBox<String> fonts;
    private JComboBox<String> sizes;
    private JButton button;
    private JTextField textField;
    
    public AppGUI(){

        //init selection panel
        JPanel selectionPanel = new JPanel();
        JLabel fontLabel = new JLabel("Font");
        String[] fontsData = {"Arial","Comic Sans MS","Haettenschweiler"};
        this.fonts = new JComboBox<>(fontsData);
        //set Event Listner
        this.fonts.addActionListener(this);
        
        JLabel sizeLabel = new JLabel("Font Size:");
        String[] sizeData = {"20","30","40","50"};
        this.sizes = new JComboBox<>(sizeData);
        //set Event Listner
        this.sizes.addActionListener(this);
        
        selectionPanel.add(fontLabel);
        selectionPanel.add(this.fonts);
        selectionPanel.add(sizeLabel);
        selectionPanel.add(this.sizes);
       
        //init BGpanle
        BGPanel bgPanel = new BGPanel();
        this.bgLabel = new JLabel();
        
        bgPanel.add(this.bgLabel);
        
        //init input panel
        JPanel inputPanel = new JPanel();
        textField = new JTextField("", 20);
        button = new JButton("UPDATE");
        //set Even Listner
        button.addActionListener(this);
        
        inputPanel.add(textField);
        inputPanel.add(button);
        
       //add panels to this frame.
       this.add(selectionPanel, BorderLayout.NORTH);
       this.add(bgPanel, BorderLayout.CENTER);
       this.add(inputPanel, BorderLayout.SOUTH);
       
       //other config of this frame.
       this.setSize(400, 400);
       this.setLocation(100,100);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    public static void main(String[] args) {
       JFrame frame = new AppGUI();
       frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.button) {
            this.bgLabel.setText(this.textField.getText());
        }
        this.bgLabel.setFont(new Font((String) this.fonts.getSelectedItem(), Font.PLAIN, Integer.parseInt((String) this.sizes.getSelectedItem())));
    }
    
}
