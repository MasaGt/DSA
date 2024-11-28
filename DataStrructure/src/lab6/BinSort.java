package lab6;

/**
 * Class which demonstrates how bin sort can be used to sort distinct integer
 * numbers between 0 and MAX_VALUE
 */
public class BinSort {

    public static void sort(int[] target, int MAX_VALUE) {

        int[] bin = new int[MAX_VALUE + 1];

        //check which a bin to increment
        for (int i = 0; i < target.length; i++) {
            bin[target[i]]++;
        }

        int index = 0;

        //take numbers out from a bin.
        for (int i = 0; i < bin.length; i++) {
            while (bin[i] > 0) {
                target[index++] = i;
                bin[i]--;
            }
        }
    }

    public static void main(String[] args) {
        //--test1---
        int[] list1 = {17, 2, 23, 7, 41, 29, 19, 43, 31, 5, 11, 47, 13,
            3, 37, 2, 3}; // distinct integer values between 0 and MAX_VALUE
        final int MAX_VALUE_FOR_LIST1 = 50;

        sort(list1, MAX_VALUE_FOR_LIST1);

        // output the results
        for (int i = 0; i < list1.length; i++) {
            System.out.print(((i > 0) ? ", " : "") + list1[i]);
        }
        System.out.println();
        
        //--test2---
        int[] list2 = {0, 9, 6, 8, 3, 9, 3, 2};
        final int MAX_VALUE_FOR_LIST2 = 10;
        
        sort(list2, MAX_VALUE_FOR_LIST2);
        
        // output the results
        for (int i = 0; i < list2.length; i++) {
            System.out.print(((i > 0) ? ", " : "") + list2[i]);
        }
        System.out.println();
    }
}
