package condesaGUI;

import javax.swing.*;
import java.awt.*;

public class plantillasGUI {
    public JFrame frame = new JFrame();
    private JPanel plantillasNuevas;
    private JPanel plantillasAnteriores;
    private JPanel main;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton button1;

    public plantillasGUI(){
        plantillasNuevas.setFont(new Font("Arial", Font.PLAIN, 40));
        plantillasAnteriores.setFont(new Font("Arial", Font.PLAIN, 40));
    }
    public void start(){
        frame.setTitle("Plantillas");
        frame.setContentPane(new plantillasGUI().main);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        //frame.setSize(CondesosCRUDGUI.WIDTH, CondesosCRUDGUI.HEIGHT);
        frame.setVisible(true);
    }
}
