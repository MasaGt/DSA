/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public class FileSorter implements Runnable{

    private int limit;
    private Queue<File> tasks;
    private Queue<File> files;
    private File input;
    private File output;
    private int inputLineNum;
    private int splittedLineNum;
    private int mergeComparisonNum;
    private boolean isTaskDone;
    
    public FileSorter(int limit, File input, File output) {
        this.limit = limit;
        tasks = new LinkedList<>();
        tasks.offer(input);
        files = new LinkedList<>();
        this.input = input;
        this.output = output;
        inputLineNum = 0;
        splittedLineNum = 0;
        mergeComparisonNum = 0;
        isTaskDone = false;
    }
    
    @Override
    public void run() {
        split();
        System.out.println("split step finishes");
        mergeSort(0);
        System.out.println("merge step finishes");
        File merged = files.poll();
        
        if (output.exists()) {
            //delete the output if it already exists
            output.delete();
        }
        //adjast name and directry of the final merged file to the output
        merged.renameTo(output);
        //reset line nums
        inputLineNum = 0;
        splittedLineNum = 0;
        mergeComparisonNum = 0;
        isTaskDone = true;
    }
    
    /**
     * split the input file into temp files
     * and do quick sort when creating a temp file
     */
    private synchronized void split() {
        File input = tasks.poll();
        if (!input.exists()) {
            System.err.println("There is no such a file");
            return;
        }
        
        try {
            //count the number of lines jn input file
            inputLineNum = (int) Files.lines(input.toPath()).count();
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = br.readLine();
            //a variabl for naming temp files
            int fileNum = 0;
            while (line != null) {
                //prepar temp file
                File tempFile = new File("Temp" + fileNum + input.getName());
                files.offer(tempFile);
                List<String> data = new ArrayList<>();
                tempFile.createNewFile();
                FileWriter filewriter = new FileWriter(tempFile);
                
                //read lines from the input
                for (int counter = 0; counter < limit; counter++) {
                    data.add(line);
                    if ((line = br.readLine()) == null) {
                        break;
                    }
                }
                
                //quick sort
                Collections.sort(data);
                //write the scaned lines to a temp file
                for (String entry : data) {
                    splittedLineNum++;
                    filewriter.write(entry+"\n");   
                }
                filewriter.flush();
                filewriter.close();
                fileNum++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * recursive merge sort
     * @param fileNum a variable for naming merged file
     */
    private synchronized void mergeSort(int fileNum) {
        if (files.size() <= 1) {
            return;
        }
        
        //the number of lines in a file
        int lineNum;
        try {
            //read strings from a temp file
            File file1 = files.poll();
            lineNum = (int) Files.lines(file1.toPath()).count();
            String[] data1;
            data1 = new String[lineNum];
            read(data1, file1);
            
            //read strings from a temp file
            File file2 = files.poll();
            lineNum = (int) Files.lines(file2.toPath()).count();
            String[] data2 = new String[lineNum];
            read(data2, file2);
        
            //integrate them into one
            merge(data1, data2, fileNum);
            file1.delete();
            file2.delete();
            
            mergeSort(++fileNum);
        } catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  integrate two data into one by following merge sort
     * @param data1
     * @param data2
     * @param counter 
     */
    private void merge(String[] data1, String[] data2, int counter) {
        int rLength = data1.length, lLength = data2.length;
        String[] mergedData = new String[rLength + lLength];
        File mergedFile = new File("Merged" + counter + input.getName());
        FileWriter filewriter = null;
        try {
            mergedFile.createNewFile();
            filewriter = new FileWriter(mergedFile);
            
            int indexR = 0, indexL = 0;
            while (indexR < rLength || indexL < lLength) {
                if (indexL >= lLength || (indexR < rLength && (data1[indexR].compareTo(data2[indexL]) <= 0))) {
                    //This code runs when the following conditions are true
                    //if the coursor of data2 has reached the end
                    //if the cousor of data1 has not reached the end and a string in data1 is smaller or equal to a string in data2
                    mergedData[indexR + indexL] = data1[indexR];
                    indexR++;
                } else {
                    //This code runs when the following conditions are true
                    //if the coursor of data1 has reached the end
                    //if the cousor of data2 has not reached the end and a string in data2 is smaller than a string in data1
                    mergedData[indexL + indexR] = data2[indexL];
                    indexL++;
                }
                filewriter.write(mergedData[(indexR + indexL) - 1] + "\n");
                mergeComparisonNum++;
            }
            filewriter.flush();
            files.add(mergedFile);
        } catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (filewriter != null) {
                    filewriter.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    /**
     * read lines of string from a file and put them into an array
     * @param data
     * @param target 
     */
    private void read(String[] data, File target) {
        BufferedReader br = null;
        String line;
        int counter = 0; 
        try {
            br = new BufferedReader(new FileReader(target));
            while ((line = br.readLine()) != null) {
                    data[counter++] = line;
                }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * return split progress
     * @return calculation : the number of processed line / total strings of the input file
     */
    public int getSplitProgress() {
        return inputLineNum == 0 ? 0 : (int)(100*((float)splittedLineNum/(float)inputLineNum));
    }
    
    /**
     * return merge progess
     * @return calculation : the number of comparisons in merge pahse / n log2 n (expected number of comparisons)
     */
    public int getMergeProgress() {
        double totalNumOfComparison =inputLineNum * (Math.log((double)inputLineNum) / Math.log(2.0));
        return inputLineNum == 0 ? 0 : (int)(100*((float)mergeComparisonNum/(float) totalNumOfComparison));
    }
    
    /**
     * return if a task is done or not
     * @return whether a task is done or not
     */
    public boolean isTaskDone() {
        return isTaskDone;
    }
    
    /**
     * return input file name
     * @return input file name
     */
    public String getInputFileName() {
        return this.input.getName();
    }
    
    /**
     * return output file name
     * @return output file name
     */
    public String getOutPutFileName() {
        return this.output.getName();
    }
    
    public static void main(String[] args) {
        String fileNm = "countries";
        File input = new File(fileNm + ".txt");
        File output = new File(fileNm + "Sorted.txt");
        
        FileSorter sorter = new FileSorter(20, input, output);
        
        Thread th = new Thread(sorter);
        th.start();
    }
    
}
