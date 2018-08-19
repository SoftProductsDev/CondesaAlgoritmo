package condesaGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage extends JFrame{
    public static final int WIDTH = 2000;
    public static final int HEIGHT = 1000;
    private JPanel main;
    private JButton crearCondesoButton;
    private JButton nuevoHorarioButton;
    private JButton crearTiendaButton;
    private JList list1;
    private JList list2;

    public FrontPage() {
        crearCondesoButton.setFont(new Font("Arial", Font.PLAIN, 40));
        crearCondesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CondesosCRUDGUI condesoGUI = new CondesosCRUDGUI();
                condesoGUI.start();
            }
        });
        crearTiendaButton.setFont(new Font("Arial", Font.PLAIN, 40));
        crearTiendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TiendasGUI tiendas = new TiendasGUI();
                tiendas.start();
            }
        });
        nuevoHorarioButton.setFont(new Font("Arial", Font.PLAIN, 40));
        nuevoHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("CONDESA");
        frame.setContentPane(new FrontPage().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //frame.setSize(FrontPage.WIDTH, FrontPage.HEIGHT);
    }
}
