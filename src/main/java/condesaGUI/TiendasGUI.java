package condesaGUI;

import javax.swing.*;
import java.awt.*;

public class TiendasGUI {
    private JFrame frame = new JFrame();
    private JButton agregarButton;
    private JButton actualizarButton;
    private JTable tiendasTable;
    private JButton eliminarButton;
    private JTextField nombreText;
    private JLabel nombreLabel;
    private JPanel main;
    private JButton plantillasDeTurnosButton;
    private JLabel managerLabel;
    private JTextField managerText;

    public TiendasGUI(){
        actualizarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        agregarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        eliminarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        nombreText.setFont(new Font("Arial", Font.PLAIN, 30));
        tiendasTable.setFont(new Font("Arial", Font.PLAIN, 30));
        plantillasDeTurnosButton.setFont(new Font("Arial", Font.PLAIN, 30));
        managerLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        managerText.setFont(new Font("Arial", Font.PLAIN, 30));
    }
    public void start(){
        frame.setTitle("CONDESA");
        frame.setContentPane(new TiendasGUI().main);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
