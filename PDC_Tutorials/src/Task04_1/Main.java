/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task04_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Masaomi
 */
public class Main {

    private final String FILE_PATH = "./resources/T04_users.txt";
    private HashMap<String, Player> players;

    public Main() {
        this.players = new HashMap<>();
        roadUsers();
    }

    public static void main(String[] args) {
        String cwd = System.getProperty("user.dir");
        System.out.println(cwd);
        Main m = new Main();

        Scanner sc = new Scanner(System.in);
        System.out.println("inout your name.");
        String name = sc.nextLine().trim();
        Player p = m.checkPlayer(name);
        //game start
        Game g = new Game(p);
        g.startQuiz();
        //finish game
        m.updateScore(p);
        System.out.println(p.getName() + "'s score is " + p.getScore());
    }

    public Player checkPlayer(String name) {
        Player p;
        if (this.players.containsKey(name)) {
            p = this.players.get(name);
        } else {
            p = new Player(name);
            this.players.put(name, p);
        }

        return p;
    }

    public void roadUsers() {
        FileInputStream fs;

        try {
            fs = new FileInputStream(FILE_PATH);
            Scanner sc = new Scanner(fs);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                Player player = new Player(st.nextToken(), Integer.parseInt(st.nextToken()));
                this.players.put(player.getName(), player);
            }
            fs.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateScore(Player player) {
        this.players.put(player.getName(), player);
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            PrintWriter pw = new PrintWriter(fos);
            for (Player p : this.players.values()) {
                pw.println(p.getName() + " " + p.getScore());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
