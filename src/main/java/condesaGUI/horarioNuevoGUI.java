package condesaGUI;

import javax.swing.*;

public class horarioNuevoGUI {
    private JFrame frame = new JFrame();
    private JPanel main;

    public horarioNuevoGUI(){

    }
    public void start(){
        //createTableContents();
        frame.setTitle("Nuevo Horario");
        frame.setContentPane(new horarioNuevoGUI().main);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
