/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task09_3;

import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Masaomi
 */
public class Model extends Observable {

    private DBManager db;
    private String username;
    private Data data;

    public Model() {
        this.db = DBManager.getInstance();
        this.data = new Data();
    }

    public void checkName(String username, String password) {
        this.username = username;
        this.data = this.db.checkName(username, password);
        if (this.data.loginFlg) {
            this.newQuestion();
        }
        this.setChanged();
        this.notifyObservers(data);

    }

    public void newQuestion() {

        int num1 = getNumber();
        int num2 = getNumber();
        int answer = num1 + num2;
        this.data.num1 = num1;
        this.data.num2 = num2;
        this.data.answer = answer;
        setChanged();
        notifyObservers(data);
    }

    public int getNumber() {
        Random generator = new Random();
        int i = generator.nextInt(100);
        return i;
    }
    
    public void checkAnswer(String answer) {
        if (answer.equals(this.data.answer + "")) {
            this.data.currentScore += 10;
        } else {
            this.data.currentScore -= 10;
        }
        
        this.newQuestion();
        this.setChanged();
        this.notifyObservers(data);
    }

        public void quitGame() {
            db.quitGame(this.data.currentScore, username);
            this.data.quitFlg = true;
            setChanged();
            notifyObservers(data);
        }
}
