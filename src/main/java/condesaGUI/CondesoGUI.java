package condesaGUI;

import javax.swing.*;
import java.awt.*;

public class CondesoGUI extends JPanel{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 1000;
    private JPanel condeso;
    private JTextField nombreField;
    private JComboBox cargoBox;
    private JRadioButton siRadioButton;
    private JRadioButton noRadioButton;
    private JComboBox cajaBox;
    private JRadioButton matutinoRadioButton;
    private JRadioButton vespertinoRadioButton;
    private JTextField contratacionField;
    private JComboBox tipoBox;
    private JTextField nivelField;
    private JButton anadirButton;
    private JLabel Nombre;
    private JLabel Cargo;
    private JLabel Nivel;
    private JLabel fijo;
    private JLabel caja;
    private JLabel tipo;
    private JLabel contratacion;
    private JLabel contrato;


    public CondesoGUI(){
        Nombre.setFont(new Font("Arial", Font.PLAIN, 40));
        Cargo.setFont(new Font("Arial", Font.PLAIN, 40));
        Nivel.setFont(new Font("Arial", Font.PLAIN, 40));
        anadirButton.setFont(new Font("Arial", Font.PLAIN, 40));
        fijo.setFont(new Font("Arial", Font.PLAIN, 40));
        caja.setFont(new Font("Arial", Font.PLAIN, 40));
        tipo.setFont(new Font("Arial", Font.PLAIN, 40));
        contratacion.setFont(new Font("Arial", Font.PLAIN, 40));
        contrato.setFont(new Font("Arial", Font.PLAIN, 40));
        nivelField.setFont(new Font("Arial", Font.PLAIN, 40));
        tipoBox.setFont(new Font("Arial", Font.PLAIN, 40));
        contratacionField.setFont(new Font("Arial", Font.PLAIN, 40));
        vespertinoRadioButton.setFont(new Font("Arial", Font.PLAIN, 40));
        matutinoRadioButton.setFont(new Font("Arial", Font.PLAIN, 40));
        cajaBox.setFont(new Font("Arial", Font.PLAIN, 40));
        noRadioButton.setFont(new Font("Arial", Font.PLAIN, 40));
        siRadioButton.setFont(new Font("Arial", Font.PLAIN, 40));
        cargoBox.setFont(new Font("Arial", Font.PLAIN, 40));
        nombreField.setFont(new Font("Arial", Font.PLAIN, 40));
    }

    public void start() {
        JFrame frame = new JFrame();
        frame.setContentPane(new CondesoGUI().condeso);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(CondesoGUI.WIDTH, CondesoGUI.HEIGHT);
    }
}
