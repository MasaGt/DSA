/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task09_3;

/**
 *
 * @author Masaomi
 */
public class RunMVC {

    public static void main(String[] args) {
        RunMVC mainRunMVC = new RunMVC();
    }

    public RunMVC() {
        View view = new View();
        Model model = new Model();
        model.addObserver(view);
        
        Controller controller = new Controller(view, model);
        view.addController(controller);
    }
    
    
}
