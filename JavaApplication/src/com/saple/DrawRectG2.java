
import java.awt.*;
import javax.swing.*;

public class DrawRectG2 extends JFrame {

    public DrawRectG2() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("グラフ");
        setLayout(new BorderLayout());
        MyPanel mypnl = new MyPanel();
        add(mypnl, BorderLayout.CENTER);
        mypnl.setBackground(Color.white);
        mypnl.setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        DrawRectG2 myframe = new DrawRectG2();
    }

    public class MyPanel extends JPanel {

        double[] data = {100, 40.5, 520, 360.4, 480.8, 250, 40, 82};
        double max = 600;
        Color c = new Color(0, 0, 0);
        Color cf = new Color(128, 128, 128);

        public void paintComponent(Graphics myg) {
            super.paintComponent(myg);

            int pw = 400;   //パネルの幅
            int ph = 300;   //パネルの高さ
            int yzero = ph - 50; //これだけマージンでなく位置
            int left = 50;    //leftマージン
            int top = 20;    //topマージン
            int right = 20;    //rightマージン
            int dx = (pw - left - right) / data.length; //1項目の幅
            double rate = (yzero - top) / max;   //値*rate→パネル上の値
            myg.setColor(c);
            myg.drawLine(left, yzero, left + dx * data.length, yzero); //x軸
            myg.drawLine(left, yzero, left, top); //y軸
            myg.setColor(cf);
            for (int i = 0; data.length > i; i++) {
                int w = (int) (dx * 0.8);
                int h = (int) (data[i] * rate);
                int x = (int) (left + dx * i + (dx - w) / 2);
                int y = yzero - h;
                myg.setColor(cf);
                myg.fillRect(x, y, w, h);
                myg.setColor(c);
                myg.drawRect(x, y, w, h);              //棒に輪郭をつける
                String text = Integer.toString(i);  //データに連番をふる
                int half = text.length() * 8 / 2;       //１桁が8ピクセルと推定
                myg.drawString(text, x + w / 2 - half, yzero + 14);
                //文字の高さ12ピクセルと推定しこれに+2
            }
            myg.setColor(c);
            int dy = 100;                             //目盛の間隔
            for (int y = 0; 600 >= y; y += dy) {           //0, 100, ... 600
                int ry = (int) (yzero - y * rate);      //目盛線のy位置
                myg.drawLine(left - 4, ry, left, ry);    //目盛線
                String text = Integer.toString(y);  //目盛の文字
                int w = text.length() * 8;            //１桁が8ピクセルと推定
                myg.drawString(text, left - w - 6, ry + 4); //目盛線より4ピクセル下げる
            }
        }
    }
}
