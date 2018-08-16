package condesaGUI;

import javax.swing.*;

public class FrontPage {
    private JPanel main;
    private JList list1;
    private JButton agregarCondesoButton;

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setContentPane(new FrontPage().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
