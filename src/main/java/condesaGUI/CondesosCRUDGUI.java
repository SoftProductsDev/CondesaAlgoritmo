package condesaGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CondesosCRUDGUI {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 1000;
    public JFrame frame = new JFrame();
    private JTable tablaCondesos;
    private JPanel editCondeso;
    private JPanel listaDeCondesos;
    private JFormattedTextField nombreText;
    private JRadioButton masculinoButton;
    private JLabel sexoLabel;
    private JRadioButton femeninoButton;
    private JComboBox cargoBox;
    private JLabel cargoLabel;
    private JComboBox contratoBox;
    private JLabel nombreLabel;
    private JLabel contratoLabel;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JLabel nivelLabel;
    private JComboBox nivelBox;
    private JRadioButton maturinoButton;
    private JLabel tipoLabel;
    private JRadioButton vespertinoButton;
    private JLabel cajaLabel;
    private JRadioButton siButton;
    private JRadioButton noButton;
    private JLabel contratacionLabel;

    public CondesosCRUDGUI(){
        tablaCondesos.setFont(new Font("Arial", Font.PLAIN, 40));
        editCondeso.setFont(new Font("Arial", Font.PLAIN, 40));
        listaDeCondesos.setFont(new Font("Arial", Font.PLAIN, 40));
        nivelLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        cajaLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        contratacionLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        contratoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        sexoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        tipoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        agregarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        actualizarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        borrarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        cargoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        siButton.setFont(new Font("Arial", Font.PLAIN, 30));
        noButton.setFont(new Font("Arial", Font.PLAIN, 30));
        vespertinoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        maturinoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        masculinoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        femeninoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        nombreText.setFont(new Font("Arial", Font.PLAIN, 30));
        contratoBox.setFont(new Font("Arial", Font.PLAIN, 30));
        cargoBox.setFont(new Font("Arial", Font.PLAIN, 30));
        nivelBox.setFont(new Font("Arial", Font.PLAIN, 30));
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void start(){
        frame.setTitle("Informacion de Condesos");
        frame.setContentPane(new CondesosCRUDGUI().listaDeCondesos);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(CondesosCRUDGUI.WIDTH, CondesosCRUDGUI.HEIGHT);
        frame.setVisible(true);

    }
}

