package Task03_1;

public class PLQuiz {

    public static void main(String[] args) {
        ResultString strInMain = new ResultString();
        //str1 is Hi. aInt is 0
        System.out.println("str 1: " + strInMain.str + " aInt 1: " + strInMain.aInt);
        changeData(strInMain);
        //str is Hi,how are yoRu. aInt is 12.
        System.out.println("str 2: " + strInMain.str + " aInt 2: " + strInMain.aInt);
    }

    public static void changeData(ResultString result1) {
        result1.str = result1.str + " ,how are ysou";
        ResultString result2 = new ResultString();
        result2.str = "Hello";
        result2.aInt = 10;
        ResultString result3 = new ResultString();
        result3.str = "Good day.";
        result3.aInt++;
        result1 = result3;
        result2.aInt++;
    }
}
