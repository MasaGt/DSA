/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Masaomi
 */
public class FileSorterGUI extends JPanel implements ActionListener{
    
    private JPanel mainPanel;
    private JLabel tasksLabel;
    private JTextField maxString;
    private JTextField inputFile, outputFile;
    private JProgressBar mergePB;
    private JProgressBar splitPB;
    private JLabel mergePBLabel;
    private JLabel splitPBLabel;
    private JPanel buttonPanel;
    private JButton processTask;
    private JButton enqueueTask;
    private Timer timer;
    
    private int tasksNum;
    private Queue<FileSorter> tasks;
    private FileSorter currentTask;
    
    public FileSorterGUI() {
        super(new BorderLayout());
        
        tasks = new LinkedList<>();
        
        //init the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        tasksLabel = new JLabel("number of items in Queue: " + tasksNum);
        maxString = new JTextField("10");
        maxString.setBorder(BorderFactory.createTitledBorder("Max Strings in memory:"));
        mainPanel.add(maxString);
        inputFile = new JTextField("N/A");
        inputFile.setEditable(false);
        inputFile.setBorder(BorderFactory.createTitledBorder("Input File:"));
        mainPanel.add(inputFile);
        
        outputFile = new JTextField("N/A");
        outputFile.setEditable(false);
        outputFile.setBorder(BorderFactory.createTitledBorder("Output File:"));
        mainPanel.add(outputFile);
        
        mergePBLabel = new JLabel("Merge Progress:");
        mergePB = new JProgressBar(0, 100);
        mainPanel.add(mergePBLabel);
        mainPanel.add(mergePB);
        
        splitPBLabel = new JLabel("Split Progress:");
        splitPB = new JProgressBar(0, 100);
        mainPanel.add(splitPBLabel);
        mainPanel.add(splitPB);
        
        add(mainPanel, BorderLayout.CENTER);
        
        //init the button panel
        buttonPanel = new JPanel();
        processTask = new JButton("Process Task");
        processTask.addActionListener(this);
        enqueueTask = new JButton("Enqueue Task");
        enqueueTask.addActionListener(this);
        checkProcessBtn();

        buttonPanel.add(processTask);
        buttonPanel.add(enqueueTask);
        add(buttonPanel, BorderLayout.SOUTH);
        
        timer = new Timer(20, this);
        timer.addActionListener(this);
        timer.start();
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("question1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FileSorterGUI());
        frame.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2,
                (screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
    }
    
    /**
     * make process button enable if there is a task. 
     * otherwise, make it disable
     */
    private void checkProcessBtn() {
        if (tasks.isEmpty()) {
            processTask.setEnabled(false);
        } else {
            processTask.setEnabled(true);
        }
    }
    
    /**
     * show messagae dialog.
     * @param msg message
     */
    private void showDialog(String msg) {
        JLabel label = new JLabel(msg);
        JOptionPane.showMessageDialog(this, label);
    }
    
    /**
     * digit check.
     * @param input maxString
     * @return true if the string only contains number, otehrwise false
     */
    private boolean isDigits(String input) {
        input = input.trim();
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        boolean isDigit = true;
        for (int i = 0; i < input.length(); i++) {
            isDigit = Character.isDigit(input.charAt(i));
            if (!isDigit) {
                break;
            }
        }
        
        return isDigit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        
        if (src == processTask) {
            //start FileSorter
            currentTask = tasks.poll();
            Thread th = new Thread(currentTask);
            th.start();
            checkProcessBtn();
        } else if (src == enqueueTask) {
            
            // validation check
            if (!isDigits(maxString.getText())) {
                showDialog("Input numbers only to \"Max Strings in memory\"");
                return;
            }
            
            //show JFileChooser dialog
            JFileChooser inputChooser = new JFileChooser(new File("."));
            int result = inputChooser.showSaveDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File input = inputChooser.getSelectedFile();
                if (!input.exists()) {
                    showDialog("choose a existing file");
                    return;
                }
                
                JFileChooser outputChooser = new JFileChooser(new File("."));
//                result = outputChooser.showOpenDialog(this);
                result = outputChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    
                    File output = outputChooser.getSelectedFile();
                    int maxStr = Integer.parseInt(maxString.getText().trim());
                    tasks.add(new FileSorter(maxStr, input, output));
                    inputFile.setText(input.getName());
                    outputFile.setText(output.getName());
                    checkProcessBtn();
                }
            }
        } else if (src == timer) {
            if(currentTask != null) { 
                splitPB.setValue(currentTask.getSplitProgress());
                mergePB.setValue(currentTask.getMergeProgress());
                if (currentTask.isTaskDone()) {
                    showDialog("Merge Sort Complete");
                    currentTask = null;
                    if (tasks.isEmpty()) {
                        inputFile.setText("N/A");
                        outputFile.setText("N/A");    
                    }
//                    checkProcessBtn();
                }
            }   
        }
    }
}
